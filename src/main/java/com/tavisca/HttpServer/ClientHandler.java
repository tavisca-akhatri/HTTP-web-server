package com.tavisca.HttpServer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.*;

public class ClientHandler extends Thread {
    private Socket socket = null;
    private ServerSocket server = null;
    private BufferedInputStream in = null;
    private BufferedOutputStream out = null;
    Logger log = Logger.getLogger("ClientHandler");

    public ClientHandler(Socket s, BufferedInputStream in, BufferedOutputStream out) {
        this.socket = s;
        this.in = in;
        this.out = out;
    }
    //function classes objects nouns should be classnames verbs-methods
     public void run() {
        try {
            FileHandler fh = new FileHandler("ClientHandler.log");
            log.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
            log.info("Assigning a new Thread :"+Thread.currentThread().getId());
            FileReader fileReader = new FileReader(in);
            String header = null;
            header = fileReader.readFile();
            log.info("#################");
            log.info(header);
            log.info("#################");
            handleClient(header);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void handleClient(String header) throws IOException {
        FileName getfilename = new FileName();
        FileContents filecontents = new FileContents();
        ResponseWriter responsewriter = new ResponseWriter();
        if(header.contains("GET")) {
            log.info("GET request found");
            String fileName = getfilename.getFileName(header);
            String contents ="";
            log.info("File Required is : " + fileName);
            if (new File(fileName).exists()) {
                log.info("File Found :" + fileName);
            }else{
                log.info("File Not Found : " + fileName);
                fileName = "Error.html";
            }
            contents = filecontents.getFileContents(fileName);
            log.info("Contents of file are: " + contents);
            Response response = new Response();
            String output = response.getResponse(contents,fileName);
            log.info(output);
            responsewriter.writeResponse(output,out,contents);
        }else{
            log.info("GET request not found !");
        }
        in.close();
        out.close();
        socket.close();
    }
}
