package com.tavisca.HttpServer;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.Date;

public class Response {
    private String responseHeader;
    private String httpVersion;
    private String statusCode;
    private String serverDescription;
    private String responseDate;
    private String contentType;
    private BufferedOutputStream output;
    private String fileName;

    public Response(String httpVersion, String statuscode, String serverDescription,
                    String fileName, BufferedOutputStream out)
    {
        this.httpVersion = httpVersion;
        this.statusCode = statuscode;
        this.serverDescription = serverDescription;
        this.responseDate = new Date().toString();
        this.contentType = (new FileContents()).getContentType(fileName);
        this.fileName = fileName;
        this.output = out;
    }

    public String generateHeader()
    {
        this.responseHeader = httpVersion+" "+statusCode+" "+"\n"+"Server: "+serverDescription
                +"\n"+"Date : "+responseDate+"\n"+"ContentType: "+contentType+"\r\n\r\n";
        return this.responseHeader;
    }

    public void sendResponse() throws IOException {
        String response = "";
        if(this.contentType == "text/html"){
            response = generateHeader();
            ResponseWriter writer = new ResponseWriter();
            writer.sendHTMLResponse(response,output,fileName);
        }
        else{
            response = generateHeader();
            ResponseWriter writer = new ResponseWriter();
            writer.sendImageResponse(response,output,fileName);
        }
    }
}
