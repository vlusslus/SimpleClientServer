package receiving;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import server.SimpleServer;

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

    }

    @Override
    public void read(DataInputStream dis) throws IOException {
        nickname = dis.readUTF();
    }

    @Override
    public void handle() {
        SimpleServer.getClientHandler(getSocket()).setNickname(nickname);
        System.out.println("Received nickname is: " + nickname);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
