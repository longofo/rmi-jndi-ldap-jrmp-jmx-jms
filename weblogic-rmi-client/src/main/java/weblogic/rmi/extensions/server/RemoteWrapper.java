//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package weblogic.rmi.extensions.server;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.Serializable;
import java.rmi.Remote;

public interface RemoteWrapper extends Serializable {
    Remote getRemoteDelegate();

    void readExternal(ObjectInput var1) throws IOException, ClassNotFoundException;
}
