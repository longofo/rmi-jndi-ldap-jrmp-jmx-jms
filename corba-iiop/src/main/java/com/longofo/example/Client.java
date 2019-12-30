package com.longofo.example;

import com.longofo.example.EchoApp.Echo;
import com.longofo.example.EchoApp.EchoHelper;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

public class Client {
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
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");

            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
            Echo href = EchoHelper.narrow(ncRef.resolve_str("ECHO-SERVER"));
            String hello = href.echoString();

            System.out.println(hello);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
