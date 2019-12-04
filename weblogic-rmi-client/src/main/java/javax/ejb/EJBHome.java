//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package javax.ejb;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface EJBHome extends Remote {
    void remove(Handle var1) throws RemoteException, RemoveException;

    void remove(Object var1) throws RemoteException, RemoveException;

    EJBMetaData getEJBMetaData() throws RemoteException;

    HomeHandle getHomeHandle() throws RemoteException;
}
