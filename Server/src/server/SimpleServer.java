package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by vlusslus on 31.07.2017.
 */
public class SimpleServer {

    private static ServerSocket server;
    private static Map<Socket, ClientHandler> handlers = new HashMap<>();

    public static void main(String[] args) {
        System.out.println("Simple server!");

        start();
        handle();
        stop();

    }

    private static void start() {
        try {
            server = new ServerSocket(8888);
        } catch (IOException e) {
            System.out.println("Unable to start server!");
            e.printStackTrace();
        }
    }

    public static void stop() {
        try {
            server.close();
        } catch (IOException e) {
            System.out.println("Unable to stop server!");
            e.printStackTrace();
        }
    }

    private static void handle() {
        while (true) {
            try {
                Socket client = server.accept();
                ClientHandler clientHandler = new ClientHandler(client);
                handlers.put(client, clientHandler);
                clientHandler.start();
                System.out.println(handlers);
            } catch (IOException e) {
                System.out.println("Unable to get client!");
                e.printStackTrace();
            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static ClientHandler getClientHandler(Socket client) {
        return handlers.get(client);
    }

    public static void invalidate(Socket client) {
        handlers.remove(client);
    }
}
