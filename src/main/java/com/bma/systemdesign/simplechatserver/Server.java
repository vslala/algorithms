package com.bma.systemdesign.simplechatserver;

import com.bma.BMARuntimeException;
import com.bma.algorithms.sort.elementary.Util;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Creates a Socket on the specified port and starts listening for client connections
 *
 * @author varun.shrivastava
 */
class Server {
    /**
     * A runnable implementation that instructs the server to start listening for new client connections
     * and append them to the list to keep track of them
     */
    private final ExecutorService threads = Executors.newFixedThreadPool(5);

    private final ServerSocket serverSocket;
    private final Set<Socket> peers;

    private boolean running = false;


    public Server(int port) {
        try {
            serverSocket = new ServerSocket(port);
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
        running = true;
        threads.submit(new StartServer());
    }

    /**
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
        running = false;
        for (Socket peer : peers) {
            peer.close();
        }
        peers.clear();
        serverSocket.close();
        threads.shutdown();
    }

    /**
     * Shows the current state of the server
     *
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

    private class StartServer implements Runnable {

        @SneakyThrows
        @Override
        public void run() {
            Util.println("Server is running...");
            while (running) {
                Socket socket = serverSocket.accept();
                peers.add(socket);
                Util.println("Connection accepted!!!\n" + socket);
                threads.submit(new SocketReaderAndWriter(socket));
            }
        }
    }

    public static void main(String[] args) {
        Server server = new Server(4444);
        server.start();
    }
}
