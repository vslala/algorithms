package com.bma.systemdesign.zookeeper.leaderelection;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class LeaderElectionTest {

    @Test
    void shouldConnectToZookeeper() {
        var leaderElection = new LeaderElection();

        leaderElection.connectToZookeeper();
        leaderElection.close();

        assertNotNull(leaderElection);
    }

    @SneakyThrows
    @Test
    void shouldVolunteerForElection() {
        var node1 = new LeaderElection();
        var node2 = new LeaderElection();
        var node3 = new LeaderElection();

        var t1 = new Thread(node1::connectToZookeeper);
        var t2 = new Thread(node2::connectToZookeeper);
        var t3 = new Thread(node3::connectToZookeeper);


        t1.start();
        t2.start();
        t3.start();

        Thread.sleep(5000);
//        await().atLeast(Duration.ofMillis(10000));
    }

}