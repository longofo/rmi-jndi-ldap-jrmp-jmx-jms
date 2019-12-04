package com.longofo.javarmi;

import java.rmi.RemoteException;

public class ServicesImpl implements Services {
    public ServicesImpl() throws RemoteException {
    }

    @Override
    public Object sendMessage(Message msg) throws RemoteException {
        return msg.getMessage();
    }
}
