package com.longofo.weblogicrmi;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;

public class Client1 {
    /**
     * 测试调用部署在Weblogic上的远程对象
     */
    public final static String JNDI_FACTORY = "weblogic.jndi.WLInitialContextFactory";

    public static void main(String[] args) {
        try {
            InitialContext ic = getInitialContext("t3://192.168.192.135:7001");
            IHello obj = (IHello) ic.lookup("HelloServer");
            System.out.println("Call remote method sayHello, Server return: " + obj.sayHello());
        } catch (Exception ex) {
            System.err.println("An exception occurred: " + ex.getMessage());
        }
    }

    private static InitialContext getInitialContext(String url)
            throws NamingException {
        Hashtable<String, String> env = new Hashtable<String, String>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, JNDI_FACTORY);
        env.put(Context.PROVIDER_URL, url);
        return new InitialContext(env);
    }
}
