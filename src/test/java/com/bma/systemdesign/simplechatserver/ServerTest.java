package com.bma.systemdesign.simplechatserver;

import com.bma.algorithms.sort.elementary.Util;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
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
        server.start();
        await().until(() -> server.isRunning());    // wait for server to start
    }

    @AfterEach
    void cleanup() {
        if (server.isRunning())
            server.stop();
    }

    @SneakyThrows
    @Test
    void shouldStartServerAndListenToConnectionsAndThenStopSuccessfully() {
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

    @SneakyThrows
    @Test
    void shouldBeCapableOfConnectingMultipleClients() {
        Socket s1 = new Socket();
        Socket s2 = new Socket();

        s1.connect(new InetSocketAddress(server.port()));
        TimeUnit.MILLISECONDS.sleep(300);
        s2.connect(new InetSocketAddress(server.port()));
        TimeUnit.MILLISECONDS.sleep(300);

        NameToIPMap.register("google.com", s1);
        NameToIPMap.register("facebook.com", s2);

        TimeUnit.MILLISECONDS.sleep(500);

        BufferedWriter writer1 = new BufferedWriter(new OutputStreamWriter(s1.getOutputStream()));
        writer1.write("facebook.com,This is a simple message!!!");
        writer1.newLine();
        writer1.flush();

        Util.println("Data written to the stream");

        await().until(() -> NameToIPMap.readOperations.intValue() > 0);
        assertEquals(1, NameToIPMap.readOperations.intValue());
//        assertTrue(s2.getInputStream().available() > 0);
    }
}