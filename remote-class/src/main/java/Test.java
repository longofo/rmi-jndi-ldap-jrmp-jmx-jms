import com.longofo.remoteclass.ExportObject;

import java.io.*;

public class Test {
    public static void main(String[] args) throws IOException {
        ExportObject exportObject = new ExportObject();
        File f = new File("serObj.ser");
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f));
        out.writeObject(exportObject);
        out.flush();
        out.close();
    }
}
