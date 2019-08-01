package com.tavisca.HttpServer;

import java.io.File;
import java.util.Date;

public class Response {
    public String getResponse(String contents,String fileName)
    {
        String response = "";
        if(new File(fileName).exists() && !fileName.equals("Error.html")){
            response+="HTTP/1.1 200 OK\r\n";
        }
        else{
            response+="HTTP/1.1 404 Error\r\n";
        }
        response+="Server: My Java HTTP Server: 1.0\r\n";
        response+=new Date().toString()+"\r\n";
        response+="Content-type: text/html\r\n";
        response+="Content-Length:"+contents.length() + "\r\n";
        response+="\r\n";
        return response;
    }
}
