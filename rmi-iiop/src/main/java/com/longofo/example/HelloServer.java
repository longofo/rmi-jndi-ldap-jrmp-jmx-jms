package com.longofo.example;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;

public class HelloServer {
    public final static String JNDI_FACTORY = "com.sun.jndi.cosnaming.CNCtxFactory";

    public static void main(String[] args) {
        try {
            System.setProperty("java.rmi.server.codebase", "http://127.0.0.1:8000/");
            //实例化Hello servant
            HelloImpl helloRef = new HelloImpl();

            //使用JNDI在命名服务中发布引用
            InitialContext initialContext = getInitialContext("iiop://127.0.0.1:1050");
            initialContext.rebind("HelloService", helloRef);

            System.out.println("Hello Server Ready...");

            Thread.currentThread().join();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static InitialContext getInitialContext(String url) throws NamingException {
        Hashtable env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY, JNDI_FACTORY);
        env.put(Context.PROVIDER_URL, url);
        return new InitialContext(env);
    }
}
