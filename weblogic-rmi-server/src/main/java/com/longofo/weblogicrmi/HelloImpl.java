package com.longofo.weblogicrmi;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.rmi.RemoteException;

public class HelloImpl implements IHello, Serializable {
    @Override
    public String sayHello() throws RemoteException {
        return "Hello, Remote Call method sayHello";
    }

    @Override
    public void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        
    }


    public static void main(String[] args) {
        try {
            HelloImpl obj = new HelloImpl();
            Context ctx = new InitialContext();
            ctx.bind("HelloServer", obj);
            System.out.println("HelloImpl created and bound in the registry " +
                    "to the name HelloServer");

        } catch (Exception e) {
            System.err.println("HelloImpl.main: an exception occurred:");
            System.err.println(e.getMessage());
        }
    }
}
