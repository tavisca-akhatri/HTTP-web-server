package com.tavisca.HttpServer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileContents {
    public String getContentType(String fileName)
    {
        String type = " ";
        if(fileName.contains("html"))
            type = "text/html";
        else if(fileName.contains("JPG") || fileName.contains("ico")
                || fileName.contains("jpeg") || fileName.contains("png") || fileName.contains("jfif"))
            type = "image/jpeg";

        return type;
    }

    public String getFileContents(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String contents = "";
        String str = "";
        while(true)
        {
            if((str = reader.readLine()) != null)
                contents+=str;
            else
                break;
        }
        return contents;
    }
}
