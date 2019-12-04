package com.longofo.jndi;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class LDAPClient1 {
    public static void main(String[] args) throws NamingException {
        System.setProperty("com.sun.jndi.ldap.object.trustURLCodebase","true");
        Context ctx = new InitialContext();
        Object object =  ctx.lookup("ldap://127.0.0.1:1389/uid=longofo,ou=employees,dc=example,dc=com");
    }
}
