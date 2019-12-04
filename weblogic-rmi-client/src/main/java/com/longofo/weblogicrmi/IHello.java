package com.longofo.weblogicrmi;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.rmi.RemoteException;

public interface IHello extends java.rmi.Remote {
    String sayHello() throws RemoteException;

    void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException;
}
