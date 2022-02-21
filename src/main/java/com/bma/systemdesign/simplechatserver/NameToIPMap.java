package com.bma.systemdesign.simplechatserver;

import com.bma.BMARuntimeException;
import lombok.experimental.UtilityClass;

import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@UtilityClass
public class NameToIPMap {
    public static final AtomicInteger readOperations = new AtomicInteger(0);
    public static final AtomicInteger writeOperations = new AtomicInteger(0);
    private static final Map<String, Socket> m = new HashMap<>();

    public static Socket get(String name) {
        if (!m.containsKey(name)) {
            throw new BMARuntimeException("No IP registered with the given name: " + name);
        }
        readOperations.getAndIncrement();
        return m.get(name);
    }

    public static void register(String name, Socket socket) {
        m.put(name, socket);
        writeOperations.getAndIncrement();
    }

}
