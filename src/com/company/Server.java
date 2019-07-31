package com.company;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private Socket socket = null;
    private ServerSocket server  = null;
    private BufferedInputStream in  =  null;
    private BufferedOutputStream out = null;
    // constructor with port
    public Server(int port) throws IOException {
        server = new ServerSocket(port);
        System.out.println("Server started");
        // starts server and waits for a connection
        while (true) {
            System.out.println("Waiting for a client ...");
            socket = server.accept();
            System.out.println("Client accepted");
            in = new BufferedInputStream(socket.getInputStream());
            out = new BufferedOutputStream(socket.getOutputStream());
            Thread t = new ClientHandler(socket,in,out);
            t.start();
        }
    }
    public static void main(String args[]) throws IOException {
        Server server = new Server(8080);
    }
}
