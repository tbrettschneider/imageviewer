package acdsee.io.util;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileHelper {

    private static final DateFormat DATEFORMAT = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    public static final boolean isZIP(final File file) {
        return file.isFile() && file.getName().endsWith("zip");
    }
    
    public static String getSt_LastModified(File a_file) {
        return FileHelper.DATEFORMAT.format(new Date(a_file.lastModified()));
    }

    public static long getSizeInKiloByte(File a_file) {
        return a_file.length() / 1024;
    }

    public static long getSizeInMegaByte(File a_file) {
        return getSizeInKiloByte(a_file) / 1024;
    }

    public static String getSt_SizeInKiloByte(File a_file) {
        return String.valueOf(getSizeInKiloByte(a_file));
    }

    public static String getSt_SizeInMegaByte(File a_file) {
        return String.valueOf(getSizeInMegaByte(a_file));
    }

    public static long getSizeInKiloByte(long size) {
        return size / 1024;
    }

    public static long getSizeInMegaByte(long size) {
        return getSizeInKiloByte(size) / 1024;
    }

    /**
     * Returns the size in bytes of the given file or directory.
     *
     * @param file a File object representing a file or directory.
     * @return the size of the given file or directory as a long value. Returns
     * -1 if an I/O error occurs.
     */
    public static long getFileOrDirectorySize(File file) {
        long size = 0;
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File file1 : files) {
                    long tmpSize = getFileOrDirectorySize(file1);
                    if (tmpSize != -1) {
                        size += tmpSize;
                    }
                }
                return size;
            }
            return -1;
        }
        return file.length();
    }
}
