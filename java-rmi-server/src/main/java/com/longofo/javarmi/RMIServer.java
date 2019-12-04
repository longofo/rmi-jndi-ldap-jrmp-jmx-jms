//RMIServer.java
package com.longofo.javarmi;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RMIServer {
    /**
     * Java RMI 服务端
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            // 实例化服务端远程对象
            ServicesImpl obj = new ServicesImpl();
            // 没有继承UnicastRemoteObject时需要使用静态方法exportObject处理
            Services services = (Services) UnicastRemoteObject.exportObject(obj, 0);
            Registry reg;
            try {
                // 创建Registry
                reg = LocateRegistry.createRegistry(9999);
                System.out.println("java RMI registry created. port on 9999...");
            } catch (Exception e) {
                System.out.println("Using existing registry");
                reg = LocateRegistry.getRegistry();
            }
            //绑定远程对象到Registry
            reg.bind("Services", services);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        }
    }
}