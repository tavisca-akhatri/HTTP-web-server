package com.tavisca.HttpServer;

import java.io.File;

public class FileName {
    private String fileName;

    public String getFileName(String header)
    {
        if(header.split(" ")[1].length() == 1) {
            this.fileName = "Server.html";
        }
        else{
           this.fileName = header.split(" ")[1].split("/")[1];
        }
        return fileName;
    }

    public boolean isFileAvailable()
    {
        return (new File(this.fileName).exists());
    }
}
