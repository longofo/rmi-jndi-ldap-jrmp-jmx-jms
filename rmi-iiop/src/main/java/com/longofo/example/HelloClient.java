package com.longofo.example;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import java.util.Hashtable;

public class HelloClient {
    public final static String JNDI_FACTORY = "com.sun.jndi.cosnaming.CNCtxFactory";

    public static void main(String[] args) {
        try {
            InitialContext initialContext = getInitialContext("iiop://127.0.0.1:1050");

            //从命名服务获取引用
            Object objRef = initialContext.lookup("HelloService");
            //narrow引用为具体的对象
            HelloInterface hello = (HelloInterface) PortableRemoteObject.narrow(objRef, HelloInterface.class);
            EvilMessage message = new EvilMessage();
            message.setMsg("Client call method sayHello...");
            hello.sayHello(message);
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
