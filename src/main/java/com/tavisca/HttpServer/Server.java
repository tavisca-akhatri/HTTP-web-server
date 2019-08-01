package com.tavisca.HttpServer;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.*;

public class Server
{
    private Socket socket = null;
    private ServerSocket server  = null;
    private BufferedInputStream input  =  null;
    private BufferedOutputStream output = null;
    // constructor with port
    public Server(int port) throws IOException {
        server = new ServerSocket(port);
    }
    public void beginProcess() throws IOException {
        Logger log = Logger.getLogger("Server");
        FileHandler fh = new FileHandler("Server.log");
        log.addHandler(fh);
        SimpleFormatter formatter = new SimpleFormatter();
        fh.setFormatter(formatter);
        log.info("Server started");
        // starts server and waits for a connection
        while(true) {
            log.info("Waiting for a client ...");
            socket = server.accept();
            log.log(Level.INFO,"Client accepted");
            input = new BufferedInputStream(socket.getInputStream());
            output = new BufferedOutputStream(socket.getOutputStream());
            Thread t = new ClientHandler(socket,input,output);
            t.start();
        }
    }
    public static void main(String args[]) throws IOException {
        Server server = new Server(80);
        server.beginProcess();
    }
}
