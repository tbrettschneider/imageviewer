package acdsee.io.util;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class FileHelper {

    private static final DateFormat DATEFORMAT = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    /**
     * Determines if the given file object defers a zip file on the filesystem.
     * @param file
     * @return 
     */
    public static final boolean isZIP(final File file) {
        return file.isFile() && file.getName().toLowerCase().endsWith("zip");
    }
    
    public static String getSt_LastModified(File file) {
        return FileHelper.DATEFORMAT.format(new Date(file.lastModified()));
    }

    public static long getSizeInKiloByte(File file) {
        return file.length() / 1024;
    }

    public static long getSizeInMegaByte(File file) {
        return getSizeInKiloByte(file) / 1024;
    }

    public static String getSt_SizeInKiloByte(File file) {
        return String.valueOf(getSizeInKiloByte(file));
    }

    public static String getSt_SizeInMegaByte(File file) {
        return String.valueOf(getSizeInMegaByte(file));
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
        if (file.isDirectory()) {
            return Arrays.stream(file.listFiles()).parallel().mapToLong(f -> f.length()).sum();
        }
        return file.length();
    }
}
