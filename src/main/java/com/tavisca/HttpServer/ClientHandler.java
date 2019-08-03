package com.tavisca.HttpServer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientHandler extends Thread {
    private Socket socket = null;
    private ServerSocket server = null;
    private BufferedInputStream in = null;
    private BufferedOutputStream out = null;
    static LogWriter log = null;

    static{
        try {
            log = new LogWriter("ClientHandler");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ClientHandler(Socket s, BufferedInputStream in, BufferedOutputStream out) throws IOException {
        this.socket = s;
        this.in = in;
        this.out = out;
    }
    //function classes objects nouns should be classnames verbs-methods
     public void run() {
        try {
            this.log.writeLog("Assigning a new Thread :"+Thread.currentThread().getId());
            String header = new FileReader(in).readFile();
            this.log.writeLog("#################");
            this.log.writeLog("Header Information : "+header);
            this.log.writeLog("#################");
            handleClient(header);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void handleClient(String header) throws IOException {
        if(header.contains("GET")) {
            FileName getfilename = new FileName();
            this.log.writeLog("<GET> request found");
            String resourceName = getfilename.getFileName(header);
            this.log.writeLog("File Required is : " +resourceName);
            String statuscode = "";
            if(getfilename.isFileAvailable()) {
                statuscode = "200 OK";
                this.log.writeLog("The request exist in server");
            }
            else {
                statuscode = "404 NOT OK";
                resourceName = "Error.html";
                this.log.writeLog("The request does not exist");
            }
            Response response = new Response("HTTP/1.1",statuscode,"My Java HTTP Server : 1.0",resourceName,out);
            response.sendResponse();
            this.log.writeLog("Response send to Client");
        }else{
            this.log.writeLog("<NOT GET> Request found !");
        }
        in.close();
        out.close();
        socket.close();
    }
}
