package com.longofo;

import com.unboundid.ldap.listener.InMemoryDirectoryServer;
import com.unboundid.ldap.listener.InMemoryDirectoryServerConfig;
import com.unboundid.ldap.listener.InMemoryListenerConfig;

import javax.net.ServerSocketFactory;
import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;
import java.net.InetAddress;


/**
 * LDAP server implementation returning JNDI references
 *
 * @author mbechler
 */
public class LDAPRefServer {

    private static final String LDAP_BASE = "dc=example,dc=com";


    public static void main(String[] args) throws IOException {
        int port = 1389;

        try {
            InMemoryDirectoryServerConfig config = new InMemoryDirectoryServerConfig(LDAP_BASE);
            config.setListenerConfigs(new InMemoryListenerConfig(
                    "listen", //$NON-NLS-1$
                    InetAddress.getByName("0.0.0.0"), //$NON-NLS-1$
                    port,
                    ServerSocketFactory.getDefault(),
                    SocketFactory.getDefault(),
                    (SSLSocketFactory) SSLSocketFactory.getDefault()));

            config.setSchema(null);
            config.setEnforceAttributeSyntaxCompliance(false);
            config.setEnforceSingleStructuralObjectClass(false);

            InMemoryDirectoryServer ds = new InMemoryDirectoryServer(config);
            ds.add("dn: " + "dc=example,dc=com", "objectClass: top", "objectclass: domain");
            ds.add("dn: " + "ou=employees,dc=example,dc=com", "objectClass: organizationalUnit", "objectClass: top");
            ds.add("dn: " + "uid=longofo,ou=employees,dc=example,dc=com", "objectClass: ExportObject");

            System.out.println("Listening on 0.0.0.0:" + port); //$NON-NLS-1$
            ds.startListening();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
