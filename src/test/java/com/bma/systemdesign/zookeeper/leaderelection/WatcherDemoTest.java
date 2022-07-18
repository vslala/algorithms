package com.bma.systemdesign.zookeeper.leaderelection;

import lombok.SneakyThrows;
import org.apache.zookeeper.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WatcherDemoTest {

    private Thread test;

    private static final String ZOOKEEPER_ADDRESS = "localhost:2181";
    private static final int SESSION_TIMEOUT = 3000;
    private final TestWatcher testWatcher = new TestWatcher();
    private final ZooKeeper zooKeeper = new ZooKeeper(ZOOKEEPER_ADDRESS, SESSION_TIMEOUT, testWatcher);

    WatcherDemoTest() throws IOException {
    }

    @BeforeEach
    void setUp() {
        test = new Thread(() -> {
            testWatcher.setZookeeper(zooKeeper);
        });
        test.start();
    }

    @SneakyThrows
    @Test
    void shouldWatchTheTargetZNode() {
        CountDownLatch countDownLatch = new CountDownLatch(4);
        new Thread(() -> {
            WatcherDemo watcher = new WatcherDemo(countDownLatch);
            watcher.connectToZookeeper();
            watcher.watchTargetZnode("/target_znode");
            watcher.run();
        }).start();

        // fire event create node
        TimeUnit.SECONDS.sleep(5);
        testWatcher.createNode("/target_znode", "some data");

        // fire event node data change
        TimeUnit.SECONDS.sleep(1);
        testWatcher.changeNodeData("/target_znode", "some new data");

        // fire event children node deleted
        TimeUnit.SECONDS.sleep(1);
        testWatcher.deleteNode("/target_znode");

        await().untilAsserted(() -> {
            System.out.println(countDownLatch.getCount());
            assertTrue(countDownLatch.await(10, TimeUnit.SECONDS));
        });
    }

    @AfterEach
    void tearDown() {
        test.interrupt();
    }

    class TestWatcher implements Watcher {

        private ZooKeeper zookeeper;

        public void setZookeeper(ZooKeeper zooKeeper) {
            this.zookeeper = zooKeeper;
            synchronized (this.zookeeper) {
                try {
                    this.zookeeper.wait();
                } catch (InterruptedException e) {

                }
            }
        }

        public void createNode(String nodePath, String data) {
            String nodeCreated = null;
            try {
                nodeCreated = zookeeper.create(nodePath, data.getBytes(StandardCharsets.UTF_8), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
                System.out.println(nodeCreated);
            } catch (KeeperException e) {
                System.out.println(e.getMessage());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        @SneakyThrows
        public void deleteNode(String nodePath) {
            zookeeper.delete(nodePath, -1);
        }

        @SneakyThrows
        public void changeNodeData(String targetNode, String data) {
            zookeeper.setData(targetNode, data.getBytes(StandardCharsets.UTF_8), -1);
        }

        @Override
        public void process(WatchedEvent event) {

        }
    }
}