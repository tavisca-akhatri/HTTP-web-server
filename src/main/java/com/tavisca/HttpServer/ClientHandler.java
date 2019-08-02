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
    LogManager lgmgr = LogManager.getLogManager();
    Logger log = lgmgr.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public ClientHandler(Socket s, BufferedInputStream in, BufferedOutputStream out) {
        this.socket = s;
        this.in = in;
        this.out = out;
    }
    //function classes objects nouns should be classnames verbs-methods
     public void run() {

        try {
            log.log(Level.INFO,"Assigning a new Thread :"+Thread.currentThread().getId());
            FileReader fileReader = new FileReader(in);
            String header = null;
            header = fileReader.readFile();
            log.log(Level.INFO,"#################");
            log.log(Level.INFO,header);
            log.log(Level.INFO,"#################");
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
            log.log(Level.INFO,"GET request found");
            String fileName = getfilename.getFileName(header);
            String contents ="";
            log.log(Level.INFO,"File Required is : " + fileName);
            if (new File(fileName).exists()) {
                log.log(Level.INFO,"File Found :" + fileName);
            }else{
                log.log(Level.INFO,"File Not Found : " + fileName);
                fileName = "Error.html";
            }
            contents = filecontents.getFileContents(fileName);
            log.log(Level.INFO,"Contents of file are: " + contents);
            Response response = new Response();
            String output = response.getResponse(contents,fileName);
            log.log(Level.INFO,output);
            responsewriter.writeResponse(output,out,contents);
        }else{
            log.log(Level.INFO,"GET request not found !");
        }
        in.close();
        out.close();
        socket.close();
    }
}
