package com.tommybrettschneider.imageviewer.base;

import java.io.File;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * 
 * @author Tommy Brettschneider
 */
public class Directory extends Walkable<File, File> {
    
    public Directory(final File file) {
        if (file.isDirectory()) {
            this.source = file;
        } else {
            throw new IllegalArgumentException(file.getAbsolutePath() + " is not a directory.");
        }
    }
    
    @Override
    public Stream<File> getChildren() {
        return Arrays.stream(getSource().listFiles()).parallel();
    }    
}
