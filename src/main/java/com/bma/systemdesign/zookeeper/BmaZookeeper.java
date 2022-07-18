package com.bma.systemdesign.zookeeper;

public interface BmaZookeeper {
    String ZOOKEEPER_ADDRESS = "localhost:2181";
    int SESSION_TIMEOUT = 3000;

    void connectToZookeeper();
    public void run();
    public void close();
}
