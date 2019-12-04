package com.longofo.javarmi;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class PublicKnown extends Message implements Serializable {
    private static final long serialVersionUID = 7439581476576889858L;
    private String param;

    public void setParam(String param) {
        this.param = param;
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        Runtime.getRuntime().exec(this.param);
    }
}
