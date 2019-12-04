package com.longofo.remoteclass;

import javax.naming.*;
import javax.naming.spi.ObjectFactory;
import java.io.Serializable;
import java.util.Hashtable;

public class ExportObject implements ObjectFactory, Serializable, Referenceable {

    private static final long serialVersionUID = 4474289574195395731L;

    @Override
    public Object getObjectInstance(Object obj, Name name, Context nameCtx, Hashtable<?, ?> environment) throws Exception {
        return null;
    }

    @Override
    public Reference getReference() throws NamingException {
        return new Reference("ExportObject", "com.longofo.remoteclass.ExportObject", "http://127.0.0.1:8000/");
    }
}
