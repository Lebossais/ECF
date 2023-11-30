package utilitaire;

import java.util.Date;
import java.util.logging.*;
import java.io.*;

public class LoggerJava {

    static Logger logger = Logger.getLogger(LoggerJava.class.getName());

    public LoggerJava() {

        try {
            LogManager.getLogManager().readConfiguration(new FileInputStream("./ressource/myLogging.properties"));
        } catch (SecurityException | IOException e1) {
            e1.printStackTrace();
        }
        logger.setLevel(Level.FINE);
        logger.addHandler(new ConsoleHandler());
        logger.addHandler(new MyHandler());

        Handler fileHandler;
        try {
            fileHandler = new FileHandler("./logs/logger.log");
            fileHandler.setFormatter(new MyFormatter());
            fileHandler.setFilter(new MyFilter());
            logger.addHandler(fileHandler);
        } catch (SecurityException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

    public void setLoggerJava(String message) {
        logger.log(Level.INFO, "MSG : "+ message);
    }

    class MyFilter implements Filter {
        @Override
        public boolean isLoggable(LogRecord log) {
            // TODO Auto-generated method stub
            if(log.getLevel() == Level.CONFIG) return false;
            return true;
        }
    }

    class MyHandler extends StreamHandler {

        public void publishy(LogRecord record) {
            super.publish(record);
        }

        public void flush() {
            super.flush();
        }

        public void close() throws SecurityException{
            super.close();
        }
    }
    class MyFormatter extends Formatter{

        public String format(LogRecord record) {
            return record.getLongThreadID()+ "::"+record.getSourceClassName()+"::"+record.getSourceMethodName()+
                    "::"+new Date(record.getMillis())+"::"+record.getMessage();
        }
    }
}