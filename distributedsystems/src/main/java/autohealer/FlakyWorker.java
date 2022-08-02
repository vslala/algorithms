package autohealer;

import lombok.SneakyThrows;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.locks.LockSupport;

/**
 * This worker behaves as a Flaky Worker.
 * It connects to the zookeeper and crashes after certain random amount of time.
 * This provides a flaky behaviour to test the {@link AutoHealer} functionality
 */
class FlakyWorker {
    private static final String ZOOKEEPER_ADDRESS = "localhost:2181";
    private static final int SESSION_TIMEOUT = 3000;

    // Parent Znode where each worker stores an ephemeral child to indicate it is alive
    private static final String AUTOHEALER_ZNODES_PATH = "/workers";

    private static final float CHANCE_TO_FAIL = 0.1F;

    private final Random random = new Random();
    private ZooKeeper zooKeeper;

    public void connectToZookeeper() throws IOException {
        this.zooKeeper = new ZooKeeper(ZOOKEEPER_ADDRESS, SESSION_TIMEOUT, event -> {
        });
    }

    /**
     * This holds the logic to keep alive for certain time and than fail.
     * The active of deleting nodes indicate that the node has crashed.
     * This would trigger the {@link org.apache.zookeeper.Watcher.Event.EventType#NodeChildrenChanged}
     * that would be acted upon by {@link AutoHealer} to create a new worker node.
     */
    @SneakyThrows
    public void work() {
        String name = addChildWorker();
        Thread.sleep(5000);

        while (true) {
            System.out.println("Working...");
            LockSupport.parkNanos(1000);
            if (random.nextFloat() < CHANCE_TO_FAIL) {
                System.out.println("Critical error happened... ooops...");
                var stat = zooKeeper.exists(name, false);
                if (stat != null) {
                    zooKeeper.delete(name, -1); // this will imitate as the node has crashed
                    break;
                }
            }
        }
    }

    /**
     * Creates a new EPHEMERAL_SEQUENTIAL node in ZooKeeper
     * @return path of the newly created node
     */
    @SneakyThrows
    private String addChildWorker() {
        return zooKeeper.create(AUTOHEALER_ZNODES_PATH + "/worker_", new byte[]{}, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
    }

    @SneakyThrows
    public static void main(String[] args) {
        var flakyWorker = new FlakyWorker();
        flakyWorker.connectToZookeeper();
        flakyWorker.work();
    }
}
