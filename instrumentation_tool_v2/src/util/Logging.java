package util;

import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class Logging {

    private static final Logging logger = new Logging();

    private Logging(){}

    public static Logging getInstance(){
        return logger;
    }

    public void formatter(Logger logger){
        logger.setUseParentHandlers(false);
        Handler conHdlr = new ConsoleHandler();
        conHdlr.setFormatter(new Formatter() {
            public String format(LogRecord record) {
                return "\n" + record.getLevel() + "-:- "
                        + record.getMessage() + "\n";
            }
        });
        logger.addHandler(conHdlr);
    }

    public void printLog(String error, Object arg) {
        final Logger logger = Logger.getLogger("test");
        formatter(logger);

        //get stacktrace

        //StringBuilder stackTraceArray = buildStackTraceString(stacktrace);
        logger.warning("*******************************************\n" + error + "\n\nHere's the StackTrace...\n");
        // for (int i=0; i <stackTraceArray.length; i++) {
        // 	logger.info("\tat " + i +"\n");
        // }
        for (StackTraceElement element : Thread.currentThread().getStackTrace()) {
            logger.info("\tat " + element.toString() +"\n");
        }

    }

}

