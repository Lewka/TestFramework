package auto.core.reporting;

import org.slf4j.LoggerFactory;

public class Logger {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger("Default-Logger");

    private Logger() {
    }

    public static void trace(String msg) {
        LOGGER.trace(msg);
    }

    public static void trace(String format, Object arg) {
        LOGGER.trace(format, arg);
    }

    public static void trace(String format, Object arg1, Object arg2) {
        LOGGER.trace(format, arg1, arg2);
    }

    public static void trace(String format, Object... arguments) {
        LOGGER.trace(format, arguments);
    }

    public static void trace(String msg, Throwable t) {
        LOGGER.trace(msg, t);
    }

    public static void debug(String msg) {
        LOGGER.debug(msg);
    }

    public static void debug(String format, Object arg) {
        LOGGER.debug(format, arg);
    }

    public static void debug(String format, Object arg1, Object arg2) {
        LOGGER.debug(format, arg1, arg2);
    }

    public static void debug(String format, Object... arguments) {
        LOGGER.debug(format, arguments);
    }

    public static void debug(String msg, Throwable t) {
        LOGGER.debug(msg, t);
    }

    public static void info(String msg) {
        LOGGER.info(msg);
    }

    public static void info(String format, Object arg) {
        LOGGER.info(format, arg);
    }

    public static void info(String format, Object arg1, Object arg2) {
        LOGGER.info(format, arg1, arg2);
    }

    public static void info(String format, Object... arguments) {
        LOGGER.info(format, arguments);
    }

    public static void info(String msg, Throwable t) {
        LOGGER.info(msg, t);
    }

    public static void warn(String msg) {
        LOGGER.warn(msg);
    }

    public static void warn(String format, Object arg) {
        LOGGER.warn(format, arg);
    }

    public static void warn(String format, Object... arguments) {
        LOGGER.warn(format, arguments);
    }

    public static void warn(String format, Object arg1, Object arg2) {
        LOGGER.warn(format, arg1, arg2);
    }

    public static void warn(String msg, Throwable t) {
        LOGGER.warn(msg, t);
    }

    public static void error(String msg) {
        LOGGER.error(msg);
    }

    public static void error(String format, Object arg) {
        LOGGER.error(format, arg);
    }

    public static void error(String format, Object arg1, Object arg2) {
        LOGGER.error(format, arg1, arg2);
    }

    public static void error(String format, Object... arguments) {
        LOGGER.error(format, arguments);
    }

    public static void error(String msg, Throwable t) {
        LOGGER.error(msg, t);
    }

}