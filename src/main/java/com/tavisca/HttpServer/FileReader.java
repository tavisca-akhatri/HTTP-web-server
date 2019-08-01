package com.tavisca.HttpServer;

import java.io.BufferedInputStream;
import java.io.IOException;

public class FileReader {

    private BufferedInputStream in = null;
    public FileReader(BufferedInputStream in){
        this.in = in;
    }

    public String readFile() throws IOException {
        byte[] data = new byte[in.available()];
        in.read(data);
        String header = new String(data);
        return header;
    }
}
