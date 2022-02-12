package com.bma;

import java.io.IOException;

public class BMARuntimeException extends RuntimeException {
    public BMARuntimeException(String message) {
        super(message);
    }

    public BMARuntimeException(String msg, IOException e) {
        super(msg, e);
    }
}
