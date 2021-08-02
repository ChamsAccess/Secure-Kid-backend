package com.cas.veritasapp.util;

import android.util.Log;

/**
 * Created by funmiayinde on 2019-09-09.
 */
public final class LogUtil {

    private static final String TAG = LogUtil.class.getSimpleName();

    /**
     * Don't instantiate this class
     */
    private LogUtil() {
        throw new Error("Do not instantiate!");
    }

    private static final int DEBUG_LEVEL = 0;
    private static final boolean DEBUG_SYS_OUT = false;

    public static void verbrose(Object obj) {
        if (Log.VERBOSE > DEBUG_LEVEL) {
            String tag = getClassName();
            String message = obj != null ? obj.toString() : "obj = null";
            Log.v(tag, message);
        }
    }

    public static void debug(Object obj) {
        if (Log.DEBUG > DEBUG_LEVEL) {
            String tag = getClassName();
            String message = obj != null ? obj.toString() : "obj = null";
            Log.d(tag, message);
        }
    }

    public static void warn(Object obj) {
        if (Log.WARN > DEBUG_LEVEL) {
            String tag = getClassName();
            String message = obj != null ? obj.toString() : "obj =  null";
            Log.w(tag, message);
        }
    }

    public static void info(Object obj) {
        if (Log.INFO > DEBUG_LEVEL) {
            String tag = getClassName();
            String message = obj != null ? obj.toString() : "obj =  null";
            Log.i(tag, message);
        }
    }

    public static void error(Object obj) {
        if (Log.ERROR > DEBUG_LEVEL) {
            String tag = getClassName();
            String message = obj != null ? obj.toString() : "obj =  null";
            Log.e(tag, message);
        }
    }


    public static void verbrose(String tag, String message) {
        if (Log.VERBOSE > DEBUG_LEVEL) {
            Log.v(tag, message);
        }
    }

    public static void error(String tag, String message) {
        if (Log.VERBOSE > DEBUG_LEVEL) {
            Log.e(tag, message);
        }
    }

    public static void warn(String tag, String message) {
        if (Log.VERBOSE > DEBUG_LEVEL) {
            Log.w(tag, message);
        }
    }

    public static void info(String tag, String message) {
        if (Log.VERBOSE > DEBUG_LEVEL) {
            Log.i(tag, message);
        }
    }

    public static void debug(String tag, String message) {
        if (Log.VERBOSE > DEBUG_LEVEL) {
            Log.d(tag, message);
        }
    }

    public static void write(String message) {
        Log.e("Custom Error:::", message);
    }

    public static void write(String message, Throwable throwable) {
        Log.e("Custom Error:::", message, throwable);
    }

    public static void print() {
        if (Log.DEBUG > DEBUG_LEVEL) {
            String tag = getClassName();
            String method = methodAndLine();
            Log.v(tag, method);
            if (DEBUG_SYS_OUT) {
                System.out.println(tag + " " + method);
            }
        }
    }

    private static String methodAndLine() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("at:->>> ");
        StackTraceElement traceElement = (new Exception()).getStackTrace()[2];
        stringBuilder.append(traceElement.getClassName()).append(" ");
        stringBuilder.append(traceElement.getMethodName());
        stringBuilder.append("(").append(traceElement.getFileName());
        stringBuilder.append(":").append(traceElement.getLineNumber()).append(")");
        return stringBuilder.toString();
    }

    private static String getClassName() {
        StackTraceElement traceElement = (new Exception()).getStackTrace()[2];
        return traceElement.getClassName();
    }
}
