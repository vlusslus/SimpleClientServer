package sending;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created by vlusslus on 31.07.2017.
 */
public abstract class SendingPackage {

    public abstract short getId();

    public abstract void write(DataOutputStream dos) throws IOException;

    public abstract void read(DataInputStream dis) throws IOException;

    public abstract void handle();

}
