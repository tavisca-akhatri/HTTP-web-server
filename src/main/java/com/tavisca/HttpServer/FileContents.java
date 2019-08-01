package com.tavisca.HttpServer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileContents {
    public String getFileContents(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String contents = "";
        String str = "";
        while((str = reader.readLine()) != null) {
            contents+=str;
        }
        return contents;
    }
}
