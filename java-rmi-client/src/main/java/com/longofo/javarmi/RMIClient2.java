package com.longofo.javarmi;

import com.longofo.remoteclass.ExportObject1;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIClient2 {
    public static void main(String[] args) throws Exception {
        System.setProperty("java.rmi.server.codebase", "http://127.0.0.1:8000/");
        Registry registry = LocateRegistry.getRegistry("127.0.0.1",9999);
        // 获取远程对象的引用
        Services services = (Services) registry.lookup("Services");
        ExportObject1 exportObject1 = new ExportObject1();
        exportObject1.setMessage("hahaha");

        services.sendMessage(exportObject1);
    }
}
