package com.cide.interactive.kuriolib;

import android.text.format.DateFormat;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by leehack on 6/3/14.
 */
public final class Log {
    private static final String TAG = "Log";
    private static File sLogFile;
    private static String sLogBuffer;
    private final static int BUFFER_SIZE = 1024;
    private final static long ZIP_START_SIZE = 10 * 1024 * BUFFER_SIZE; // BUFFER_SIZE * 10KB
    private final static String LOG_FILE_NAME = "log.txt";
    private final static String LOG_ZIP_FILE_NAME = "log.zip";
    static final int ZIP_BUFFER = 2048;

    private static boolean sFilelogEnabled = false;

    private static void writeLog(String tag, String level, String msg) {
        if (sFilelogEnabled) {
            Date d = new Date();
            CharSequence s = DateFormat.format("MM-dd hh:mm:ss", d.getTime());
            String pid = Integer.toString(android.os.Process.myPid());
            String tid = Integer.toString(android.os.Process.myTid());
            String printMsg = s + " " + pid + '-' + tid + ' ' + level + '/' + tag + ": " + msg + '\n';

            sLogBuffer = sLogBuffer + printMsg;
            if (sLogBuffer.length() > BUFFER_SIZE) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        flushBuffer(false);
                    }
                }).start();
            }
        }
    }

    private static void zipLog(ArrayList<File> fileList, String destPath) {
        try {
            BufferedInputStream origin = null;
            FileOutputStream destStream = new
                    FileOutputStream(destPath);
            ZipOutputStream out = new ZipOutputStream(new
                    BufferedOutputStream(destStream));
            byte data[] = new byte[ZIP_BUFFER];

            for (File file : fileList) {
                FileInputStream fi = new FileInputStream(file);
                origin = new BufferedInputStream(fi, ZIP_BUFFER);
                ZipEntry entry = new ZipEntry(file.getName());
                out.putNextEntry(entry);
                int count;
                while ((count = origin.read(data, 0, ZIP_BUFFER)) != -1) {
                    out.write(data, 0, count);
                }
                origin.close();
                file.delete();
            }
            out.close();
        } catch (Exception e) {
            android.util.Log.w(TAG, e);
        }
    }

    public static synchronized void flushBuffer(boolean forceZip) {
        try {
            FileOutputStream fo = new FileOutputStream(sLogFile, true);
            fo.write(sLogBuffer.getBytes());
            fo.close();
            sLogBuffer = "";
        } catch (IOException e) {
            android.util.Log.w(TAG, e);
        }

        long size = 0;
        ArrayList<File> fileList = new ArrayList<>();
        for (File f : sLogFile.getParentFile().listFiles()) {
            if (f.getName().endsWith(LOG_FILE_NAME)) {
                fileList.add(f);
                size += f.length();
            }
        }

        if (size >= ZIP_START_SIZE || forceZip) {
            Date d = new Date();
            CharSequence s = DateFormat.format("yyyy-MM-dd-hh-mm-ss", d.getTime());
            zipLog(fileList, sLogFile.getParent() + '/' + s + '.' + LOG_ZIP_FILE_NAME);
        }

    }

    public static void enableFileLogger(String path) {
        sFilelogEnabled = true;
        Date d = new Date();
        CharSequence s = DateFormat.format("yyyy-MM-dd-hh-mm-ss", d.getTime());
        String uid = Integer.toString(android.os.Process.myUid());
        sLogFile = new File(path, s + "." + uid + '.' + LOG_FILE_NAME);
        sLogBuffer = "";
    }

    public static void disableFileLogger() {
        sFilelogEnabled = false;
        flushBuffer(false);
    }

    /**
     * Send a {@link #VERBOSE} log message.
     *
     * @param tag Used to identify the source of a log message.  It usually identifies
     *            the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
    public static int v(String tag, String msg) {
        writeLog(tag, "V", msg);
        return android.util.Log.v(tag, msg);
    }

    /**
     * Send a {@link #VERBOSE} log message and log the exception.
     *
     * @param tag Used to identify the source of a log message.  It usually identifies
     *            the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     * @param tr  An exception to log
     */
    public static int v(String tag, String msg, Throwable tr) {
        writeLog(tag, "V", msg);
        writeLog(tag, "V", android.util.Log.getStackTraceString(tr));
        return android.util.Log.v(tag, msg, tr);
    }

    /**
     * Send a {@link #DEBUG} log message.
     *
     * @param tag Used to identify the source of a log message.  It usually identifies
     *            the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
    public static int d(String tag, String msg) {
        writeLog(tag, "D", msg);
        return android.util.Log.d(tag, msg);
    }

    /**
     * Send a {@link #DEBUG} log message and log the exception.
     *
     * @param tag Used to identify the source of a log message.  It usually identifies
     *            the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     * @param tr  An exception to log
     */
    public static int d(String tag, String msg, Throwable tr) {
        writeLog(tag, "D", msg);
        writeLog(tag, "D", android.util.Log.getStackTraceString(tr));
        return android.util.Log.d(tag, msg, tr);
    }

    /**
     * Send an {@link #INFO} log message.
     *
     * @param tag Used to identify the source of a log message.  It usually identifies
     *            the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
    public static int i(String tag, String msg) {
        writeLog(tag, "I", msg);
        return android.util.Log.i(tag, msg);
    }

    /**
     * Send a {@link #INFO} log message and log the exception.
     *
     * @param tag Used to identify the source of a log message.  It usually identifies
     *            the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     * @param tr  An exception to log
     */
    public static int i(String tag, String msg, Throwable tr) {
        writeLog(tag, "I", msg);
        writeLog(tag, "I", android.util.Log.getStackTraceString(tr));
        return android.util.Log.i(tag, msg, tr);
    }

    /**
     * Send a {@link #WARN} log message.
     *
     * @param tag Used to identify the source of a log message.  It usually identifies
     *            the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
    public static int w(String tag, String msg) {
        writeLog(tag, "W", msg);
        return android.util.Log.w(tag, msg);
    }

    /**
     * Send a {@link #WARN} log message and log the exception.
     *
     * @param tag Used to identify the source of a log message.  It usually identifies
     *            the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     * @param tr  An exception to log
     */
    public static int w(String tag, String msg, Throwable tr) {
        writeLog(tag, "W", msg);
        writeLog(tag, "W", android.util.Log.getStackTraceString(tr));
        return android.util.Log.w(tag, msg, tr);
    }

    /*
     * Send a {@link #WARN} log message and log the exception.
     * @param tag Used to identify the source of a log message.  It usually identifies
     *        the class or activity where the log call occurs.
     * @param tr An exception to log
     */
    public static int w(String tag, Throwable tr) {
        writeLog(tag, "W", android.util.Log.getStackTraceString(tr));
        return android.util.Log.w(tag, tr);
    }

    /**
     * Send an {@link #ERROR} log message.
     *
     * @param tag Used to identify the source of a log message.  It usually identifies
     *            the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
    public static int e(String tag, String msg) {
        writeLog(tag, "E", msg);
        return android.util.Log.e(tag, msg);
    }

    /**
     * Send a {@link #ERROR} log message and log the exception.
     *
     * @param tag Used to identify the source of a log message.  It usually identifies
     *            the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     * @param tr  An exception to log
     */
    public static int e(String tag, String msg, Throwable tr) {
        writeLog(tag, "E", msg);
        writeLog(tag, "E", android.util.Log.getStackTraceString(tr));
        return android.util.Log.e(tag, msg, tr);
    }
}
