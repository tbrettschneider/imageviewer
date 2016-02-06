
package acdsee.io.util;

import java.io.File;
import java.util.Arrays;
import org.apache.commons.io.FilenameUtils;

public class FileHelper {

    private static final String[] FILE_EXTENSION_ZIP = {"zip", "ZIP"};
    
    /**
     * Determines if the given file object defers a zip file on the filesystem.
     * @param file
     * @return 
     */
    public static final boolean isZIP(final File file) {
        return file.isFile() && FilenameUtils.isExtension(file.getName(), FILE_EXTENSION_ZIP);
    }

    /**
     * Returns the size in bytes of the given file or directory.
     *
     * @param file a File object representing a file or directory.
     * @return the size of the given file or directory as a long value. Returns
     * -1 if an I/O error occurs.
     */
    public static long getFileSize(File file) {
        if (file.isDirectory()) {
            return Arrays.stream(file.listFiles()).parallel().mapToLong(f -> f.length()).sum();
        }
        return file.length();
    }
}
