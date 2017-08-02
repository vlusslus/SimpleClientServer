package sending;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created by vlusslus on 31.07.2017.
 */
public class AuthorizingPackage extends SendingPackage {

    private short id;
    private String nickname;

    public AuthorizingPackage() {
        //this.id = 1;
    }

    public AuthorizingPackage(String nickname) {
        //this.id = 1;
        this.nickname = nickname;
    }

    @Override
    public short getId() {
        return 1;
    }

    @Override
    public void write(DataOutputStream dos) throws IOException {
        dos.writeUTF(nickname);

    }

    @Override
    public void read(DataInputStream dis) throws IOException {

    }

    @Override
    public void handle() {

    }
}
