package com.longofo.jndi;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class LDAPClient2 {
    public static void main(String[] args) throws NamingException {
        Context ctx = new InitialContext();
        Object object = ctx.lookup("ldap://127.0.0.1:1389/uid=longofo,ou=employees,dc=example,dc=com");
    }
}
