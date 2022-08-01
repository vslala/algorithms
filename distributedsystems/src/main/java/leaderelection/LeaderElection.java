package leaderelection;

import lombok.SneakyThrows;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.Collections;
import java.util.List;

import static java.util.Objects.isNull;

class LeaderElection implements Watcher {
    private static final String ZOOKEEPER_ADDRESS = "localhost:2181";
    private static final int SESSION_TIMEOUT = 3000;
    private static final String ELECTION_NAMESPACE = "/election";
    private ZooKeeper zooKeeper;
    private String currentZnodeName;
    @SneakyThrows
    void connectToZookeeper() {
        zooKeeper = new ZooKeeper(ZOOKEEPER_ADDRESS, SESSION_TIMEOUT, this);
        this.volunteerForLeadership();
        this.electLeader();
        this.run();
    }

    @SneakyThrows
    void volunteerForLeadership() {
        String nodePrefix = ELECTION_NAMESPACE + "/c_";
        String currentZnodePath = zooKeeper.create(nodePrefix, new byte[]{}, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        this.currentZnodeName = currentZnodePath.replace(ELECTION_NAMESPACE + "/", "");
        System.out.println("Volunteering for leadership: " + currentZnodeName);
    }

    @SneakyThrows
    void electLeader() {
        Stat predecessorZNodeStat = null;
        String predecessorZNodeName = null;

        while (isNull(predecessorZNodeStat)) {
            List<String> children = zooKeeper.getChildren(ELECTION_NAMESPACE, false);
            Collections.sort(children);
            String smallestChild = children.get(0);

            if (currentZnodeName.equalsIgnoreCase(smallestChild)) {
                System.out.println("I'm the leader.");
                return;
            } else {
                System.out.println("I'm not the leader.");
                int predecessorZNodeIndex = Collections.binarySearch(children, currentZnodeName) - 1;
                predecessorZNodeName = children.get(predecessorZNodeIndex);
                predecessorZNodeStat = zooKeeper.exists(ELECTION_NAMESPACE + "/" + predecessorZNodeName, this);
            }
        }

        System.out.println("Watching ZNode: " + predecessorZNodeName);
        System.out.println();
    }

    @SneakyThrows
    private void run() {
        synchronized (zooKeeper) {
            zooKeeper.wait();
        }
    }

    @Override
    public void process(WatchedEvent event) {
        switch (event.getType()) {
            case None:
                if (event.getState() == Event.KeeperState.SyncConnected) {
                    System.out.println("Connected to the Zookeeper");
                } else {
                    synchronized (zooKeeper) {
                        zooKeeper.notifyAll();
                        System.out.println("Disconnected from Zookeeper.");
                    }
                }
                break;
            case NodeDeleted:
                electLeader();
                break;
        }
    }

    public static void main(String[] args) {
        var leaderElection = new LeaderElection();
        leaderElection.connectToZookeeper();
    }


}
