package com.longofo.jndi;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class RMIClient1 {
    public static void main(String[] args) throws RemoteException, NotBoundException, NamingException {
//        Properties env = new Properties();
//        env.put(Context.INITIAL_CONTEXT_FACTORY,
//                "com.sun.jndi.rmi.registry.RegistryContextFactory");
//        env.put(Context.PROVIDER_URL,
//                "rmi://localhost:9999");
        Context ctx = new InitialContext();
        DirContext dirc = new InitialDirContext();
        ctx.lookup("rmi://localhost:9999/refObj");
    }
}
