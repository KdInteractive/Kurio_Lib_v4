package com.cide.interactive.kuriolib;

import android.content.Context;
import android.content.res.Resources;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Locale;
import java.util.zip.CRC32;

/**
 * Filename : FileUtils.java Creation date : 2006-02-23
 *
 * @author josgiu
 * @version 1.0 description : this class contains static methods to manipulate
 *          file, retrieve file size ...
 */
public class FileUtils {

    // We define a buffer-size of 512 KB of data
    protected static final int BUFFERSIZE = 512 * 1024;
    private static final String TAG = "FileUtils";

    public static boolean renameDirectory(String fromDir, String toDir) {

        File from = new File(fromDir);

        if (!from.exists() || !from.isDirectory()) {

            System.out.println("Directory does not exist: " + fromDir);
            return false;
        }

        File to = new File(toDir);

        // Rename
        return from.renameTo(to);

    }

    /**
     * This method copies source to destination. If destination already exists
     * the file is overwritten.
     *
     * @param source
     * @param destination
     * @return true if there is no error on copy
     */
    public static boolean copyFile(String source, String destination) {
        boolean bRes = false;
        long written = 0;
        FileChannel input = null;
        FileChannel output = null;

        try {
            if (source != null && exists(source) && !source.equals(destination)) {
                input = (new FileInputStream(source)).getChannel();
                output = (new FileOutputStream(destination)).getChannel();
                // We use nio library
                written = input.transferTo(0, input.size(), output);
                bRes = (written > 0);
            }
        } catch (Exception e) {
            bRes = false;
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException ignored) {
                }
            }
            if (output != null) {
                try {
                    output.close();
                } catch (IOException ignored) {
                }
            }
        }
        return (bRes);
    }

    public static void copyFile(InputStream in, OutputStream out)
            throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while ((read = in.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }
        out.flush();
        out.close();
    }

    /**
     * This method delete a file
     *
     * @param FileName
     */
    public static void deleteFile(String FileName) {
        if (exists(FileName)) {
            File file = new File(FileName);
            deleteFile(file);
        }
    }

    public static boolean deleteFile(File file) {
        try {
            return file.delete();
        } catch (Exception e) {
            return false;
        }
    }

    public static void deleteDirectory(String dirname) {
        try {
            File pathToProcess = null;

            if (directoryExists(dirname)) {
                pathToProcess = new File(dirname);
                deleteDirectory(pathToProcess);
            }
        } catch (Exception ignored) {
        }
    }

    /**
     * Delete all contents of a directory, including its files and subdir
     *
     * @param dir
     */
    public static void deleteDirectory(File dir) {
        File[] files = null;

        if (dir.exists() && dir.isDirectory()) {
            files = dir.listFiles();

            for (File f : files) {
                if (f.isDirectory()) {
                    deleteDirectory(f);
                } else {
                    deleteFile(f);
                }
            }
            dir.delete();
        }
    }

    /**
     * This method move a file from source place to destination
     *
     * @param source
     * @param destination
     * @return true is there is no error
     */
    public static boolean moveFile(String source, String destination) {
        boolean bRes = false;
        try {
            if (exists(source) && !source.equals(destination)) {
                // Method File::renameTo seems to bug on some systems...
                if (copyFile(source, destination)) {
                    deleteFile(source);
                    bRes = true;
                }
            }
        } catch (Exception e) {
            System.out.println("Exception while moving file " + source + " to "
                    + destination + " --> " + e.getMessage());
            bRes = false;
        }
        return (bRes);
    }

    /**
     * Check for the existance of a file
     *
     * @param FileName
     * @return true if the file exists
     */
    public static boolean exists(String FileName) {
        boolean bRes = false;
        try {
            File fichier = new File(FileName);
            bRes = fichier.exists();
        } catch (Exception e) {
            bRes = false;
        }
        return (bRes);
    }

    public static boolean directoryExists(String DirectoryName) {
        boolean bres = false;
        try {
            File dir = new File(DirectoryName);
            bres = (dir.exists() && dir.isDirectory());
        } catch (Exception e) {
            bres = false;
        }
        return (bres);
    }

    public static void createDirectory(String DirectoryName) {
        try {
            File dir = new File(DirectoryName);
            dir.mkdirs();
        } catch (Exception ignored) {
        }
    }

    /**
     * Compute the length of a file.
     *
     * @param FileName
     * @return The size of the file in bytes
     */
    public static long size(String FileName) {
        long iTaille = 0;

        try {
            if (exists(FileName)) {
                File fichier = new File(FileName);
                iTaille = fichier.length();
            }
        } catch (Exception e) {
            iTaille = 0;
        }
        return (iTaille);
    }

    /**
     * Compute the crc32 of a file
     *
     * @param FileName
     * @return
     */
    public static long computeChecksum(String FileName) {
        long lcrc = 0;
        FileInputStream input = null;
        CRC32 crc = new CRC32();
        int iRead = 0;

        try {
            byte[] Buffer = new byte[BUFFERSIZE];
            input = new FileInputStream(FileName);

            crc.reset();
            while ((iRead = input.read(Buffer)) > 0) {
                crc.update(Buffer, 0, iRead);
            }
        } catch (Exception e) {
            lcrc = 0;
        } finally {
            if (input != null) {
                try {
                    input.close();
                    input = null;
                } catch (Exception ignored) {
                }
            }
            lcrc = crc.getValue();
        }

        return (lcrc);
    }

    public static ArrayList<String> getSubFoldersAbsolutePath(
            String folderParent) {
        ArrayList<String> results = new ArrayList<String>();
        File dir = new File(folderParent);

        // The list of files can also be retrieved as File objects
        File[] files = dir.listFiles();

        // This filter only returns directories
        FileFilter fileFilter = new FileFilter() {
            public boolean accept(File file) {
                return file.isDirectory();
            }
        };
        files = dir.listFiles(fileFilter);
        if (files == null)
            return results;
        for (File f : files) {
            results.add(f.getAbsolutePath());
        }
        return results;
    }

    public static ArrayList<String> getSubFoldersName(String folderParent) {
        ArrayList<String> results = new ArrayList<String>();
        File dir = new File(folderParent);

        // The list of files can also be retrieved as File objects
        File[] files = dir.listFiles();

        // This filter only returns directories
        FileFilter fileFilter = new FileFilter() {
            public boolean accept(File file) {
                return file.isDirectory();
            }
        };
        files = dir.listFiles(fileFilter);

        for (File f : files) {
            results.add(f.getName());
        }
        return results;
    }

    public static ArrayList<String> getFiles(String folderParent) {
        ArrayList<String> results = new ArrayList<String>();
        File dir = new File(folderParent);

        // The list of files can also be retrieved as File objects
        File[] files = dir.listFiles();

        // This filter only returns directories
        FileFilter fileFilter = new FileFilter() {
            public boolean accept(File file) {
                return !file.isDirectory();
            }
        };
        files = dir.listFiles(fileFilter);
        if (files != null) {
            for (File f : files) {
                results.add(f.getAbsolutePath());
            }
        }
        return results;
    }

    public static ArrayList<String> getFilesName(String folderParent) {
        ArrayList<String> results = new ArrayList<String>();
        File dir = new File(folderParent);

        // The list of files can also be retrieved as File objects
        File[] files = dir.listFiles();

        // This filter only returns directories
        FileFilter fileFilter = new FileFilter() {
            public boolean accept(File file) {
                return !file.isDirectory();
            }
        };
        files = dir.listFiles(fileFilter);

        for (File f : files) {
            results.add(f.getName());
        }
        return results;
    }

    public static String getMIMEType(String name) {
        String type = "";
        String end = name.substring(name.lastIndexOf('.') + 1, name.length())
                .toLowerCase();
        if (end.equals("apk")) {
            return "application/vnd.android.package-archive";
        } else if (end.equals("mp4") || end.equals("avi") || end.equals("3gp")
                || end.equals("rmvb")) {
            type = "video";
        } else if (end.equals("m4a") || end.equals("mp3") || end.equals("mid")
                || end.equals("xmf") || end.equals("ogg") || end.equals("wav")) {
            type = "audio";
        } else if (end.equals("jpg") || end.equals("gif") || end.equals("png")
                || end.equals("jpeg") || end.equals("bmp")) {
            type = "image";
        } else if (end.equals("txt") || end.equals("log")) {
            type = "text";
        } else {
            type = "*";
        }
        type += "/*";
        return type;
    }

    public static int getExternalResourceId(Resources res, String packageName, String resId, String resType) {
        return res.getIdentifier(resId, resType, packageName);
    }

    public static ArrayList<String> getTextFileAsArray(Context context, String packageName,
                                                       String resType, String resId) {
        int id = 0;
        ArrayList<String> textAsArray = new ArrayList<String>();
        try {
            Resources res = context.getPackageManager().getResourcesForApplication(packageName);
            id = getExternalResourceId(res, packageName, resId, resType);

            InputStream in = res.openRawResource(id);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line = null;

            try {
                line = reader.readLine();

                while (line != null) {
                    textAsArray.add(line);
                    line = reader.readLine();
                }

            } catch (IOException e) {
                Log.w(TAG, e);

            } finally {
                try {

                    if (reader != null) {
                        reader.close();
                    }

                    if (in != null) {
                        in.close();
                    }

                } catch (IOException e) {
                    Log.w(TAG, e);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return textAsArray;
    }

    public static ArrayList<String> getTextFileAsArray(Context context, int resId) {

        ArrayList<String> textAsArray = new ArrayList<String>();


        InputStream in = context.getResources().openRawResource(resId);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String line = null;

        try {
            line = reader.readLine();

            while (line != null) {
                textAsArray.add(line);
                line = reader.readLine();
            }

        } catch (IOException e) {
            Log.w(TAG, e);
        } finally {
            try {

                if (reader != null) {
                    reader.close();
                }

                if (in != null) {
                    in.close();
                }

            } catch (IOException e) {
                Log.w(TAG, e);
            }
        }

        return textAsArray;
    }

    public static ArrayList<String> getTextFileAsArrayWihtoutComment(Resources res, int resId) {

        ArrayList<String> textAsArray = new ArrayList<String>();


        InputStream in = res.openRawResource(resId);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String line = null;

        try {
            line = reader.readLine();

            while (line != null) {
                if (!line.startsWith("#")) {
                    textAsArray.add(line);
                }
                line = reader.readLine();
            }

        } catch (IOException e) {
            Log.w(TAG, e);
        } finally {
            try {

                if (reader != null) {
                    reader.close();
                }

                if (in != null) {
                    in.close();
                }

            } catch (IOException e) {
                Log.w(TAG, e);
            }
        }

        return textAsArray;
    }

    public static int getFileVersionCode(Context context, int resId) {


        InputStream in = context.getResources().openRawResource(resId);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String line = null;
        String versionCode = "-1";
        try {
            line = reader.readLine();

            versionCode = line.substring(line.lastIndexOf(':') + 1);

        } catch (IOException e) {
            Log.w(TAG, e);
        } finally {
            try {

                if (reader != null) {
                    reader.close();
                }

                if (in != null) {
                    in.close();
                }

            } catch (IOException e) {
                Log.w(TAG, e);
            }
        }

        return Integer.valueOf(versionCode);
    }

    public static String readTextFromResource(Context context, int resourceID) {
        InputStream raw = context.getResources().openRawResource(resourceID);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        int i;
        try {
            i = raw.read();
            while (i != -1) {
                stream.write(i);
                i = raw.read();
            }
            raw.close();
        } catch (IOException e) {
            Log.w(TAG, e);
        }
        return stream.toString();
    }

    public static int getDefaultFileForRessource(Context context, int fileResId) {

        String ressourceName = context.getResources().getResourceEntryName(fileResId);
        String fileNameRomID = ressourceName + '_' + Utils.getRomId();
        String type = context.getResources().getResourceTypeName(fileResId);

        int romResFileId = context.getResources().getIdentifier(fileNameRomID, type, context.getPackageName());

        if (romResFileId != 0) {
            return romResFileId;
        } else {
            return fileResId;
        }
    }

    public static void copyFileWithCreateDir(String fromPath, String toPath) throws IOException {
        File fromFile = new File(fromPath);
        File toFile = new File(toPath);

        File destDir = new File(toPath.substring(0, toPath.lastIndexOf('/')));
        destDir.mkdirs();

        copyFile(new FileInputStream(fromFile), new FileOutputStream(toFile));
    }

    public static void moveFileWithCreateDir(String fromPath, String toPath) {
        File fromFile = new File(fromPath);
        File destDir = new File(toPath.substring(0, toPath.lastIndexOf('/')));
        destDir.mkdirs();
        fromFile.renameTo(new File(toPath));
    }


    public static String MD5sum(String path) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            File file = new File(path);
            FileInputStream fileInputStream = new FileInputStream(file);
            DigestInputStream dis = new DigestInputStream(fileInputStream, messageDigest);
            int nbrReaded;
            byte[] buffer = new byte[8192];
            while ((nbrReaded = fileInputStream.read(buffer)) > 0) {
                messageDigest.update(buffer, 0, nbrReaded);
            }
            return new BigInteger(1, messageDigest.digest()).toString(16);
        } catch (Exception e) {
            Log.w(TAG, e);
        }
        return "-1";
    }

    public static boolean downloadFile(String url, File outputFile) {
        try {
            URL u = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) u.openConnection();
            conn.connect();
            InputStream stream = conn.getInputStream();
            if (stream == null) {
            }
            FileOutputStream fos = new FileOutputStream(outputFile);

            byte data[] = new byte[2048];
            int count;
            while ((count = stream.read(data, 0, 2048)) != -1) {
                fos.write(data, 0, count);
            }

            if (stream != null)
                stream.close();

            String str = new String(data, "UTF-8");
            str = str.trim();
            if ((str != null) && (str.startsWith("Not Found")||str.startsWith("Error"))) {
                fos.close();
                return false;
            }

            if (fos != null)
                fos.close();

            if (outputFile.length() <= 0L) {
                return false;
            }

        } catch (FileNotFoundException e) {
            Log.w(TAG, e);
            return false; // swallow a 404
        } catch (IOException e) {
            Log.w(TAG, e);
            return false; // swallow a 404
        }
        return true;
    }

    public static String copyFromRawToObb(Context context, int id) {
        Resources resources = context.getResources();

        String resName = resources.getResourceEntryName(id);
        String locale = Locale.getDefault().getISO3Language();
        String newName = context.getObbDir().getAbsolutePath() + File.separator + locale + '-' + resName;
        File newFile = new File(newName);
        try {
            InputStream in = resources.openRawResource(id);
            FileOutputStream out;
            out = new FileOutputStream(newName);

            byte[] buff = new byte[1024];
            int read = 0;

            try {
                while ((read = in.read(buff)) > 0) {
                    out.write(buff, 0, read);
                }
            } finally {
                in.close();
                out.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newName;
    }

    public static void deleteFileFromObb(Context context, int id) {
        Resources resources = context.getResources();

        String resName = resources.getResourceEntryName(id);
        String locale = Locale.getDefault().getISO3Language();
        String newName = context.getObbDir().getAbsolutePath() + File.separator + locale + '-' + resName;
        File f = new File(newName);
        f.delete();
    }
}
