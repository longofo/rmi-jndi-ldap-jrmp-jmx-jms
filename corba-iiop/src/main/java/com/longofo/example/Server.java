package com.longofo.example;

import com.longofo.example.EchoApp.Echo;
import com.longofo.example.EchoApp.EchoHelper;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

public class Server {
    public static void main(String[] args) {
        if (args.length == 0) {
            args = new String[4];
            args[0] = "-ORBInitialPort";
            args[1] = "1050";
            args[2] = "-ORBInitialHost";
            args[3] = "localhost";
        }

        try {
            //创建并初始化ORB
            ORB orb = ORB.init(args, null);

            //获取根POA的引用并激活POAManager
            POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootpoa.the_POAManager().activate();

            //创建servant
            EchoImpl echoImpl = new EchoImpl();

            //获取与servant关联的对象引用
            org.omg.CORBA.Object ref = rootpoa.servant_to_reference(echoImpl);
            Echo echoRef = EchoHelper.narrow(ref);

            //为所有CORBA ORB定义字符串"NameService"。当传递该字符串时，ORB返回一个命名上下文对象，该对象是名称服务的对象引用
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            NameComponent path[] = ncRef.to_name("ECHO-SERVER");
            ncRef.rebind(path, echoRef);

            System.out.println("Server ready and waiting...");

            //等待客户端调用
            orb.run();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
