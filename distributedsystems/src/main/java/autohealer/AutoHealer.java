package autohealer;

import lombok.SneakyThrows;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.List;

/**
 * This class acts like a nodes monitor.
 * The objective of this class is to keep a steady number of nodes working at all times.
 * If any node goes down, that would trigger the NodeChildrenChange event that would
 * result a creation of a new worker node.
 *
 * Ref exercise link: https://klarna.udemy.com/course/distributed-systems-cloud-computing-with-java/learn/lecture/16911406#overview
 *
 * Steps to run:
 * 1. Make sure ZooKeeper is up and running on the desired address {@link AutoHealer#ZOOKEEPER_ADDRESS}
 * 2. Run the main method to see it in action
 *
 * @author varun.shrivastava
 */
class AutoHealer implements Watcher {
    private static final String ZOOKEEPER_ADDRESS = "localhost:2181";
    private static final int SESSION_TIMEOUT = 3000;

    // Parent Znode where each worker stores an ephemeral child to indicate it is alive
    private static final String AUTOHEALER_ZNODES_PATH = "/workers";

    private int numberOfWorkers = 5; // default
    private String pathToProgram;
    private ZooKeeper zookeeper;

    public AutoHealer() {
    }

    public AutoHealer(int numberOfWorkers, String pathToProgram) {
        this.numberOfWorkers = numberOfWorkers;
        this.pathToProgram = pathToProgram;
    }

    /**
     * connects to the ZooKeeper or else throws IOException
     */
    @SneakyThrows
    void connectToZookeeper() {
        zookeeper = new ZooKeeper(ZOOKEEPER_ADDRESS, SESSION_TIMEOUT, this);
    }

    /**
     * This method should be called right after connecting to the ZooKeeper
     * This method watches the {@link #AUTOHEALER_ZNODES_PATH} and if not present already
     * creates a new {@link CreateMode#PERSISTENT} node in the ZooKeeper.
     * Also, it tries to launch any missing nodes.
     */
    @SneakyThrows
    public void startWatchingWorkers() {
        Stat stat = zookeeper.exists(AUTOHEALER_ZNODES_PATH, false);
        if (stat == null) {
            zookeeper.create(AUTOHEALER_ZNODES_PATH, new byte[]{}, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }
        launchWorkersIfNecessary();
    }

    /**
     * This method is called whenever there is a change made to the children.
     * It has a simple logic to check if the number of working nodes is less than the
     * expected number, then spawn a new worker node.
     */
    @SneakyThrows
    private void launchWorkersIfNecessary() {
        List<String> children = zookeeper.getChildren(AUTOHEALER_ZNODES_PATH, this);
        System.out.println(String.format("Currently there are %d workers", children.size()));
        if (children.size() < numberOfWorkers) {
            startNewWorker();
        }
    }

    @SneakyThrows
    void run() {
        synchronized (zookeeper) {
            zookeeper.wait();
        }
    }

    @SneakyThrows
    void close() {
        zookeeper.close();
    }

    @Override
    public void process(WatchedEvent event) {
        switch (event.getType()) {
            case None:
                if (event.getState() == Event.KeeperState.SyncConnected) {
                    System.out.println("Successfully connected to the Zookeeper...");
                } else {
                    synchronized (zookeeper) {
                        System.out.println("Disconnected from Zookeeper...");
                        zookeeper.notifyAll();
                    }
                }
                break;
            case NodeChildrenChanged:
                // this is where the node went down
                System.out.println("Node children changed event received...");
                launchWorkersIfNecessary();
                break;
        }
    }

    /**
     * Helper method to start a single worker
     */
    @SneakyThrows
    private Thread startNewWorker() {
        var workerThread = new Thread(() -> {
            try {
            var flakyWorker = new FlakyWorker();
                flakyWorker.connectToZookeeper();
                flakyWorker.work();
            } catch (Exception e) {
                System.exit(0);
            }
        });

        workerThread.start();
        return workerThread;
    }

    public static void main(String[] args) {
        var autoHealer = new AutoHealer();
        autoHealer.connectToZookeeper();
        autoHealer.startWatchingWorkers();
        autoHealer.run();
    }
}
