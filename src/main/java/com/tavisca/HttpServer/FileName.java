package com.tavisca.HttpServer;

public class FileName {
    public String getFileName(String header)
    {
        String fileName = " ";
        if(header.split(" ")[1].length() == 1) {
            fileName = "Server.html";
        }
        else{
            fileName = header.split(" ")[1].split("/")[1];
        }
        return fileName;
    }
}
