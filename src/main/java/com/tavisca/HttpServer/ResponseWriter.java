package com.tavisca.HttpServer;

import java.io.BufferedOutputStream;
import java.io.IOException;

public class ResponseWriter {/*String fileName*/
    public void writeResponse(String httpResponse, BufferedOutputStream out ,String contents) throws IOException {
        httpResponse+=contents;
        out.write(httpResponse.getBytes());
        out.flush();
    }
}
