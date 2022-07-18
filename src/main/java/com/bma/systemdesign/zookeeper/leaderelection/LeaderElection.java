package com.bma.systemdesign.zookeeper.leaderelection;

import com.bma.systemdesign.zookeeper.BmaZookeeper;
import lombok.SneakyThrows;
import org.apache.zookeeper.*;

import java.util.Collections;
import java.util.List;

class LeaderElection implements Watcher, BmaZookeeper {
    private static final String ZOOKEEPER_ADDRESS = "localhost:2181";
    private static final int SESSION_TIMEOUT = 3000;
    private static final String ELECTION_NAMESPACE = "/election";

    private ZooKeeper zooKeeper;
    private String currentZnodeName;

    @Override
    @SneakyThrows
    public void connectToZookeeper() {
        this.zooKeeper = new ZooKeeper(ZOOKEEPER_ADDRESS, SESSION_TIMEOUT, this);
        this.volunteerForElection();
        this.electLeader();
        this.run();
        System.out.println("Disconnected from Zookeeper. Exiting application.");
    }

    /**
     * will be called by zookeeper library on a separate thread whenever there is an event
     * @param event
     */
    @Override
    public void process(WatchedEvent event) {
        switch (event.getType()) {
            case None:
                if (event.getState() == Event.KeeperState.SyncConnected) {
                    System.out.println("Successfully connected to Zookeeper event.");
                } else {
                    synchronized (zooKeeper) {
                        System.out.println("Disconnected from Zookeeper event");
                        zooKeeper.notifyAll();
                    }
                }
        }
    }

    @SneakyThrows
    public void run() {
        synchronized (zooKeeper) {
            zooKeeper.wait();
        }
    }

    public static void main(String[] args) {
        LeaderElection leaderElection = new LeaderElection();
        leaderElection.connectToZookeeper();
    }

    @SneakyThrows
    public void close() {
        zooKeeper.close();
    }

    @SneakyThrows
    public void volunteerForElection() {
        String zNodePrefix = ELECTION_NAMESPACE + "/c_";
        String zNodeFullPath = this.zooKeeper.create(zNodePrefix, new byte[]{}, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);

        System.out.println("Znode Full Path: " + zNodeFullPath);

        this.currentZnodeName = zNodeFullPath.replace(ELECTION_NAMESPACE + "/", "");
    }

    @SneakyThrows
    public void electLeader() {
        List<String> children = zooKeeper.getChildren(ELECTION_NAMESPACE, false);
        Collections.sort(children);
        String smallestChild = children.get(0);

        if (smallestChild.equalsIgnoreCase(currentZnodeName)) {
            System.out.println(currentZnodeName + ": I'm the leader.");
        } else {
            System.out.println(currentZnodeName + ": I'm not the leader.");
        }
    }
}
