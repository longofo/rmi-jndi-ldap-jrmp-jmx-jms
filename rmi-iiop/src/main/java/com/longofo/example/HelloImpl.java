package com.longofo.example;

import javax.rmi.PortableRemoteObject;
import java.rmi.RemoteException;

public class HelloImpl extends PortableRemoteObject implements HelloInterface {
    /**
     * Initializes the object by calling <code>exportObject(this)</code>.
     *
     * @throws RemoteException if export fails.
     */
    protected HelloImpl() throws RemoteException {
    }

    @Override
    public void sayHello(Message msg) throws RemoteException {
        System.out.println("Hello from " + msg.getMsg() + "!!");
    }
}
