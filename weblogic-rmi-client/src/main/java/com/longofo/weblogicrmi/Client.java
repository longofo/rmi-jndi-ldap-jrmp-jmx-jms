package com.longofo.weblogicrmi;

import com.alibaba.fastjson.JSON;
import weblogic.rmi.extensions.server.RemoteWrapper;

import javax.naming.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class Client {
    /**
     * 列出Weblogic有哪些可以远程调用的对象
     */
    public final static String JNDI_FACTORY = "weblogic.jndi.WLInitialContextFactory";

    public static void main(String[] args) throws NamingException, IOException, ClassNotFoundException {
        //Weblogic RMI和Web服务共用7001端口
        //可直接传入t3://或者rmi://或者ldap://等，JNDI会自动根据协议创建上下文环境
        InitialContext initialContext = getInitialContext("t3://127.0.0.1:7001");
        System.out.println(JSON.toJSONString(listAllEntries(initialContext), true));

        //尝试调用ejb上绑定的对象的方法getRemoteDelegate
        //weblogic.jndi.internal.WLContextImpl类继承的远程接口为RemoteWrapper，可以自己在jar包中看下，我们客户端只需要写一个包名和类名与服务器上的一样即可
        RemoteWrapper remoteWrapper = (RemoteWrapper) initialContext.lookup("ejb");
        System.out.println(remoteWrapper.getRemoteDelegate());
    }

    private static Map listAllEntries(Context initialContext) throws NamingException {
        String namespace = initialContext instanceof InitialContext ? initialContext.getNameInNamespace() : "";
        HashMap<String, Object> map = new HashMap<String, Object>();
        System.out.println("> Listing namespace: " + namespace);
        NamingEnumeration<NameClassPair> list = initialContext.list(namespace);
        while (list.hasMoreElements()) {
            NameClassPair next = list.next();
            String name = next.getName();
            String jndiPath = namespace + name;
            HashMap<String, Object> lookup = new HashMap<String, Object>();
            try {
                System.out.println("> Looking up name: " + jndiPath);
                Object tmp = initialContext.lookup(jndiPath);
                if (tmp instanceof Context) {
                    lookup.put("class", tmp.getClass());
                    lookup.put("interfaces", tmp.getClass().getInterfaces());
                    Map<String, Object> entries = listAllEntries((Context) tmp);
                    for (Map.Entry<String, Object> entry : entries.entrySet()) {
                        String key = entry.getKey();
                        if (key != null) {
                            lookup.put(key, entries.get(key));
                            break;
                        }
                    }
                } else {
                    lookup.put("class", tmp.getClass());
                    lookup.put("interfaces", tmp.getClass().getInterfaces());
                }
            } catch (Throwable t) {
                lookup.put("error msg", t.getMessage());
                Object tmp = initialContext.lookup(jndiPath);
                lookup.put("class", tmp.getClass());
                lookup.put("interfaces", tmp.getClass().getInterfaces());
            }
            map.put(name, lookup);

        }
        return map;
    }

    private static InitialContext getInitialContext(String url) throws NamingException {
        Hashtable env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY, JNDI_FACTORY);
        env.put(Context.PROVIDER_URL, url);
        return new InitialContext(env);
    }
}
