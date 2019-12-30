package com.longofo.example;

import java.io.ObjectInputStream;
import java.io.Serializable;

public class Message implements Serializable {
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private void readObject(ObjectInputStream s) {
        try {
            s.defaultReadObject();
            System.out.println("In Message readObject");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
