package com.tavisca.HttpServer;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LogWriter
{
    private static Logger logger;

     public LogWriter(String className) throws IOException {
         this.logger = Logger.getLogger(className);
         FileHandler fh = new FileHandler(className+".log");
         this.logger.addHandler(fh);
         SimpleFormatter formatter = new SimpleFormatter();
         fh.setFormatter(formatter);
    }
    public void writeLog(String message)
    {
        this.logger.info(message);
    }
}
