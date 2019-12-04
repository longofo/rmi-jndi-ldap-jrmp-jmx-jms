package com.longofo.javarmi;

import java.io.Serializable;

public class Message implements Serializable {
    private String msg;

    public Message() {
    }

    public String getMessage() {
        System.out.println("Processing message: " + msg);
        return msg;
    }

    public void setMessage(String msg) {
        this.msg = msg;
    }
}
