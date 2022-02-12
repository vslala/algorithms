package com.bma.systemdesign.simplechatserver;

import com.bma.BMARuntimeException;
import com.bma.algorithms.sort.elementary.Util;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

/**
 * Creates a Socket on the specified port and starts listening for client connections
 *
 * @author varun.shrivastava
 */
class Server {

    private final ServerSocket serverSocket;
    private final Set<Socket> peers;
    private final Thread serverThread;

    private boolean running = false;

    public Server(int port) {
        try {
            serverSocket = new ServerSocket(port);
            serverThread = new Thread(new StartServer());
            peers = new HashSet<>();
        } catch (IOException e) {
            Util.println("Cannot create server socket at port " + port);
            e.printStackTrace();
            throw new BMARuntimeException("Cannot create server socket at port " + port, e);
        }
    }

    /**
     * Starts the Socket to accept connections in a separate thread
     */
    @SneakyThrows
    public void start() {
        serverThread.start();
    }

    /**
     *
     * @return all active connections
     */
    public Iterable<Socket> connectedPeers() {
        return peers;
    }

    /**
     * Close all the active peer connections and removes them from the memory
     * Close the Socket (server shuts down)
     */
    @SneakyThrows
    public void stop() {
        for (Socket peer : peers) {
            peer.close();
            peers.remove(peer);
        }
        serverSocket.close();
        running = false;
    }

    /**
     * Shows the current state of the server
     * @return whether server is running or not
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * @return local port of the server
     */
    public int port() {
        return serverSocket.getLocalPort();
    }

    /**
     * A runnable implementation that instructs the server to start listening for new client connections
     * and append them to the list to keep track of them
     */
    private class StartServer implements Runnable {

        @SneakyThrows
        @Override
        public void run() {
            Util.println("Server is running...");
            running = true;
            Socket socket = serverSocket.accept();
            peers.add(socket);
        }
    }
}
