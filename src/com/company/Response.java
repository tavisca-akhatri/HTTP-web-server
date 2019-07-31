package com.company;

import java.io.BufferedOutputStream;
import java.io.IOException;

public class Response
{
    public void getResponse(String contents, BufferedOutputStream out) throws IOException {
        if(contents.contains("HTTP/1.1 200 OK\r\n\r\n"))
        {
            String httpResponse = "HTTP/1.1 200 OK\r\n\r\n" + contents; //ok response
            out.write(httpResponse.getBytes("UTF-8"));
            out.flush();
        }
        else
        {
            String httpResponse = "HTTP/1.1 404 Error\r\n\r\n" + contents; //notok response
            out.write(httpResponse.getBytes("UTF-8"));
            out.flush();
        }
    }
}
