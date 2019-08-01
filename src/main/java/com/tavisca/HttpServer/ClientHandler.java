package com.tavisca.HttpServer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class ClientHandler extends Thread {
    private Socket socket = null;
    private ServerSocket server = null;
    private BufferedInputStream in = null;
    private BufferedOutputStream out = null;


    public ClientHandler(Socket s, BufferedInputStream in, BufferedOutputStream out) {
        this.socket = s;
        this.in = in;
        this.out = out;
    }
    //function classes objects nouns should be classnames verbs-methods
    public void run() {
        LogManager lgmngr = LogManager.getLogManager();
        Logger log = lgmngr.getLogger(Logger.GLOBAL_LOGGER_NAME);
        log.log(Level.INFO,"Assigning a new Thread :"+Thread.currentThread().getId());
        try {
            FileReader fileReader = new FileReader(in);
            FileName getfilename = new FileName();
            FileContents filecontents = new FileContents();
            ResponseWriter responsewriter = new ResponseWriter();
            String header = fileReader.readFile();
            log.log(Level.INFO,"#################");
            log.log(Level.INFO,header);
            log.log(Level.INFO,"#################");
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
        }catch(FileNotFoundException e) {
            e.printStackTrace();
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
