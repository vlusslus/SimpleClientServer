import sending.AuthorizingPackage;
import sending.SendingPackage;
import sun.awt.windows.ThemeReader;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by vlusslus on 31.07.2017.
 */
public class SimpleClient {

    private static Socket client;

    public static void main(String[] args) {
        System.out.println("Simple client!");
        connect();
        send();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e){};
        disconnect();
    }

    private static void sendPackage(SendingPackage sendingPackage) {
        try {
            DataOutputStream dos = new DataOutputStream(client.getOutputStream());
            dos.writeShort(sendingPackage.getId());
            sendingPackage.write(dos);
            dos.flush();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void connect() {
        try {
            client = new Socket("localhost",8888);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void send() {
        sendPackage(new AuthorizingPackage("vlusslus"));
    }

    private static void disconnect(){
        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
