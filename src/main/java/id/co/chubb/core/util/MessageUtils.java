package id.co.chubb.core.util;

import org.slf4j.Logger;

public class MessageUtils {

    public static void setLogMessage(String message, Logger logger) {
        StackTraceElement[] info = new Throwable().fillInStackTrace().getStackTrace();
        message = info[1].getMethodName() + "(" + info[1].getLineNumber() + ")==> ::" + message.toUpperCase() + "::";
        logger.info(message);
    }

    public static void setDebugMessage(String message, Logger logger) {
        StackTraceElement[] debug = new Throwable().fillInStackTrace().getStackTrace();
        message = debug[1].getMethodName() + "(" + debug[1].getLineNumber() + ")==> ::" + message.toUpperCase() + "::";
        logger.debug(message);
    }

    public static void setErrorLogMessage(String message, Logger logger) {
        StackTraceElement[] info = new Throwable().fillInStackTrace().getStackTrace();
        message = info[1].getMethodName() + "(" + info[1].getLineNumber() + ")==> ::" + message.toUpperCase() + "::";
        logger.error(message);
    }

    public static void setErrorLogMessage(String message, Throwable throwable, Logger logger) {
        StackTraceElement[] error = new Throwable().fillInStackTrace().getStackTrace();
        message = error[1].getMethodName() + "(" + error[1].getLineNumber() + ")==> ::" + message.toUpperCase() + "::";
        logger.error(message, throwable);
    }
}
