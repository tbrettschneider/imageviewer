package com.tommybrettschneider.imageviewer.util;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.filechooser.FileSystemView;
import org.apache.commons.io.FilenameUtils;
import sun.awt.shell.ShellFolder;

public class Files {

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

    /**
     * @param file
     * @return
     */
    public static final ImageIcon getLargeFileIcon(final File file) {
        if (file == null) {
            throw new IllegalArgumentException("File is null!");
        }
        try {
            return new ImageIcon(ShellFolder.getShellFolder(file).getIcon(true));
        } catch (IOException e) {
            return null;
        }
    }

    public static final Icon getSmallFileIcon(File file) {
        if (file == null) {
            throw new IllegalArgumentException("File is null!");
        }
        return FileSystemView.getFileSystemView().getSystemIcon(file);
    }
}
