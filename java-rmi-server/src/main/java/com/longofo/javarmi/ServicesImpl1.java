package com.longofo.javarmi;

import com.longofo.remoteclass.ExportObject;

import java.rmi.RemoteException;

public class ServicesImpl1 implements Services {
    @Override
    public ExportObject sendMessage(Message msg) throws RemoteException {
        return new ExportObject();
    }
}
