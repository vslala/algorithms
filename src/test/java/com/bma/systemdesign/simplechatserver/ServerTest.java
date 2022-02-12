package com.bma.systemdesign.simplechatserver;

import com.bma.algorithms.sort.elementary.Util;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.*;

class ServerTest {
    private Server server;

    @BeforeEach
    void setup() {
        server = new Server(4444);
    }

    @SneakyThrows
    @Test
    void shouldStartServerAndListenToConnectionsAndThenStopSuccessfully() {
        server.start();
        await().until(() -> server.isRunning());    // wait for server to start

        Socket client = new Socket();
        assertEquals(0, client.getPort());      // this is when the client is not connected to server
        client.connect(new InetSocketAddress(server.port()));  // connect to the server
        TimeUnit.MILLISECONDS.sleep(200);       // wait while the connection is established
        assertTrue(client.isConnected());               // connection has been established
        assertEquals(server.port(), client.getPort());  // now this value should show the port connected to

        Iterable<Socket> peers = server.connectedPeers();
        assertNotNull(peers);
        Util.println(peers.iterator().next());

        server.stop();
        assertFalse(server.isRunning());

        /*
            Note: Closing a socket doesn't clear its connection state,
            which means this method will return true for a closed socket (see isClosed())
            if it was successfully connected prior to being closed.
         */
        assertTrue(client.isConnected());
    }
}