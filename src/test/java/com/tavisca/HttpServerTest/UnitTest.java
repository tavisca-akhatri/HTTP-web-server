package com.tavisca.HttpServerTest;

import com.tavisca.HttpServer.FileContents;
import com.tavisca.HttpServer.FileName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class UnitTest
{
    @Test
    public void unitTest1()
    {
        FileName name = new FileName();
        assertEquals("welcome.html",name.getFileName("GET /welcome.html HTTP/1.1"));
        assertEquals("Error.html",name.getFileName("GET /h HTTP/1.1"));
        assertEquals("Server.html",name.getFileName("GET / HTTP/1.1"));
    }

//    @Test
//    public void unitTest2() throws IOException {
//        FileContents contents = new FileContents();
//        System.out.println(contents.getFileContents("Server.html"));
//        assertEquals("<!DOCTYPE html><html lang="en><head>    <meta charset=UTF-8>" +
//                "<title>Server</title></head><body><h2>Connected to the Server!</h2></body></html>"
//                ,contents.getFileContents("Server.html"));
//
//    }

}
