package server;

import receiving.PackageManager;
import receiving.SendingPackage;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by vlusslus on 31.07.2017.
 */
public class ClientHandler extends  Thread{

    private final Socket client;
    private String nickname = "unknown";

    public ClientHandler(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        while (true) {

            receivePackage();

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void receivePackage() {
        try {
            DataInputStream dis = new DataInputStream(client.getInputStream());
            if(dis.available() > 0) {
                short id = dis.readShort();
                SendingPackage sendingPackage = PackageManager.getPackage(id, dis);
                sendingPackage.setSocket(client);
                sendingPackage.read(dis);
                sendingPackage.handle();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void invalidate() {
        SimpleServer.invalidate(client);
    }
}
