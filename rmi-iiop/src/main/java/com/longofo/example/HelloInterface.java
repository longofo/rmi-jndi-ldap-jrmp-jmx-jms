package com.longofo.example;

public interface HelloInterface extends java.rmi.Remote {
    public void sayHello(Message msg) throws java.rmi.RemoteException;
}
