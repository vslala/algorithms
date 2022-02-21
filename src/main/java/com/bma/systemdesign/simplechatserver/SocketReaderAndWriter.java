package com.bma.systemdesign.simplechatserver;

import com.bma.algorithms.sort.elementary.Util;
import lombok.RequiredArgsConstructor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
class SocketReaderAndWriter implements Runnable {
    final Socket socket;

    @Override
    public void run() {
        try {
//            Util.println("Trying to read message on the stream for socket: " + socket);
            var is = new InputStreamReader(socket.getInputStream());
            var br = new BufferedReader(is);
            String line;
            while ((line = br.readLine()) != null) {
//                Util.println("Message Received by socket %s: %s", socket, line);
                String[] parts = line.split(",");
                String name = parts[0];
                Socket s = NameToIPMap.get(name);
                if (s == socket) break;
                // send the message
//                Util.println("Sending Message to: " + s);
                s.getOutputStream().write(parts[1].getBytes(StandardCharsets.UTF_8));
                s.getOutputStream().write("\n".getBytes(StandardCharsets.UTF_8));
                s.getOutputStream().flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
