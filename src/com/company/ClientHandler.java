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
            in.read(data);
            String header = new String(data);
            System.out.println("########");
            System.out.println(header);
            System.out.println("########");
            System.out.println("Closing connection");
            if (header.contains("GET")) {
                System.out.println("GET request found");
                String fileName = " ";
                if(header.split(" ")[1].length() == 1)
                {
                    fileName = "Server.html";
                }
                else {
                    fileName = header.split(" ")[1].split("/")[1];
                }
                System.out.println("File Required is : " + fileName);
                if (new File(fileName).exists()) {
                    System.out.println("File Found :" + fileName);
                    BufferedReader reader = new BufferedReader(new FileReader(fileName));
                    String contents = "";
                    String str = "";
                    while ((str = reader.readLine()) != null) {
                        contents += str;
                    }
                    System.out.println("Contents of file are: " + contents);

                    String httpResponse = "HTTP/1.1 200 OK\r\n\r\n" + contents; //ok response
                    out.write(httpResponse.getBytes("UTF-8"));
                    out.flush();
                } else {
                    System.out.println("File Not Found : " + fileName);
                    fileName = "Error.html";
                    BufferedReader reader = new BufferedReader(new FileReader(fileName));
                    String contents = "";
                    String str = "";
                    while ((str = reader.readLine()) != null) {
                        contents += str;
                    }
                    System.out.println("Contents of file are: " + contents);
                    String httpResponse = "HTTP/1.1 200 OK\r\n\r\n" + contents; //ok response
                    out.write(httpResponse.getBytes("UTF-8"));
                    out.flush();
                }

            } else {
                System.out.println("GET request not found !");

            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
