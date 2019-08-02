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
            FileReader fileReader = new FileReader(in);
            String header = "";
            header = fileReader.readFile();
            this.log.writeLog("#################");
            this.log.writeLog(header);
            this.log.writeLog("#################");
            handleClient(header);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void handleClient(String header) throws IOException {
        FileName getfilename = new FileName();
        FileContents filecontents = new FileContents();
        ResponseWriter responsewriter = new ResponseWriter();
        if(header.contains("GET")) {
            this.log.writeLog("GET request found");
            String fileName = getfilename.getFileName(header);
            String contents ="";
            this.log.writeLog("File Required is : " + fileName);
            if (new File(fileName).exists()) {
                this.log.writeLog("File Found :" + fileName);
            }else{
                this.log.writeLog("File Not Found : " + fileName);
                fileName = "Error.html";
            }
            contents = filecontents.getFileContents(fileName);
            this.log.writeLog("Contents of file are: " + contents);
            Response response = new Response();
            String output = response.getResponse(contents,fileName);
            this.log.writeLog(output);
            responsewriter.writeResponse(output,out,contents);
        }else{
            this.log.writeLog("GET request not found !");
        }
        in.close();
        out.close();
        socket.close();
    }
}
