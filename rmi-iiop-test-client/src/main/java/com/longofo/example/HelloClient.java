package com.longofo.example;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.rmi.RMISecurityManager;
import java.util.Hashtable;

public class HelloClient {
    public final static String JNDI_FACTORY = "com.sun.jndi.cosnaming.CNCtxFactory";

    public static void main(String[] args) {
        try {
            System.setProperty("java.security.policy", HelloClient.class.getClassLoader().getResource("java.policy").getFile());
            RMISecurityManager securityManager = new RMISecurityManager();
            System.setSecurityManager(securityManager);
            InitialContext initialContext = getInitialContext("iiop://127.0.0.1:1050");

            //从命名服务获取引用
            initialContext.lookup("HelloService");
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
