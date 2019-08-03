package com.tavisca.HttpServer;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

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
        LogWriter log = new LogWriter("Server");
        log.writeLog("Server started");                              // starts server and waits for a connection
        log.writeLog("Server is listening on port: "+server.getLocalPort());
        while(true) {
            log.writeLog("Waiting for a client ...");
            socket = server.accept();
            log.writeLog("Client accepted");
            input = new BufferedInputStream(socket.getInputStream());
            output = new BufferedOutputStream(socket.getOutputStream());
            Thread client = new ClientHandler(socket,input,output);
            log.writeLog("A new Client Thread started ");
            client.start();
        }
    }
    public static void main(String args[]) throws IOException {
        Server server = new Server(80);
        server.beginProcess();
    }
}
