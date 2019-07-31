package com.company;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

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
    public void run() {
        System.out.println("Assigning a new Thread : "+Thread.currentThread().getId());
        try {
            byte[] data = new byte[in.available()];
            FileName getfilename = new FileName();
            FileContents filecontents = new FileContents();
            Response response = new Response();
            in.read(data);
            String header = new String(data);
            System.out.println("#################");
            System.out.println(header);
            System.out.println("#################");
            if (header.contains("GET")) {
                System.out.println("GET request found");
                String fileName = getfilename.getFileName(header);
                System.out.println("File Required is : " + fileName);
                if (new File(fileName).exists()) {
                    System.out.println("File Found :" + fileName);
                    String contents = filecontents.getFileContents(fileName);
                    System.out.println("Contents of file are: " + contents);
                    response.getResponse(contents,out);
                } else{
                    System.out.println("File Not Found : " + fileName);
                    fileName = "Error.html";
                    String contents = filecontents.getFileContents(fileName);
                    System.out.println("Contents of file are: " + contents);
                    response.getResponse(contents,out);
                }
            }else{
                System.out.println("GET request not found !");
            }
        }catch(FileNotFoundException e) {
            e.printStackTrace();
        }catch(IOException e) {
            e.printStackTrace();
        }
        try{
            in.close();
            out.close();
            socket.close();
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
