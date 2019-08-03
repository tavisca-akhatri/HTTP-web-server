package com.tavisca.HttpServer;

import java.io.*;

public class ResponseWriter {/*String fileName*/
    public void sendHTMLResponse(String httpResponse, BufferedOutputStream out ,String fileName) throws IOException {
        String contents = new FileContents().getFileContents(fileName);
        httpResponse+=contents;
        out.write(httpResponse.getBytes());
        out.flush();
    }

    public void sendImageResponse(String httpResponse, BufferedOutputStream out ,String fileName)
    {
        try {
            BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(fileName));
            out.write(httpResponse.getBytes());
            out.flush();
            byte[] buffer = new byte[inputStream.available()];
            int bytesRead = 0;
            while ((bytesRead = inputStream.read(buffer))!=-1) {
                out.write(buffer,0,bytesRead);
            }
            out.flush();
        }catch (IOException e) {
            e.printStackTrace();
        }


    }
}
