package com.bma.systemdesign.zookeeper.leaderelection;

import com.bma.systemdesign.zookeeper.BmaZookeeper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.util.List;

import static java.util.Objects.isNull;

@NoArgsConstructor
@AllArgsConstructor
class WatcherDemo implements Watcher, BmaZookeeper {

    private ZooKeeper zooKeeper;

    @Override
    @SneakyThrows
    public void connectToZookeeper() {
        zooKeeper = new ZooKeeper(ZOOKEEPER_ADDRESS, SESSION_TIMEOUT, this);
    }

    @Override
    @SneakyThrows
    public void run() {
        synchronized (zooKeeper) {
            zooKeeper.wait();
        }
    }

    @Override
    @SneakyThrows
    public void close() {
        zooKeeper.close();
    }

    @SneakyThrows
    public void watchTargetZnode(String targetNodePath) {
        Stat stat = zooKeeper.exists(targetNodePath, this);
        if (isNull(stat)) return;

        byte[] data = zooKeeper.getData(targetNodePath, this, stat);
        List<String> children = zooKeeper.getChildren(targetNodePath, this);
        System.out.println("Data: " + new String(data) + ", children: " + children);
    }

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
                break;
            case NodeCreated:
                System.out.println(event.getPath() + ": was created");
                break;
            case NodeDataChanged:
                System.out.println(event.getPath() + ": data changed");
                break;
            case NodeChildrenChanged:
                System.out.println(event.getPath() + ": children change");
                break;
            case NodeDeleted:
                System.out.println(event.getPath() + ": was deleted");
                break;
        }

        watchTargetZnode("/target_znode");
    }

    public static void main(String[] args) {
        var watcher = new WatcherDemo();
        watcher.connectToZookeeper();
        watcher.watchTargetZnode("/target_znode");
        watcher.run();
        watcher.close();
    }
}
