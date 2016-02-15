package com.tommybrettschneider.imageviewer.base;

import com.tommybrettschneider.imageviewer.util.Files;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;
import java.util.stream.Stream;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author Tommy Brettschneider
 * @param <ImageFolderType>
 * @param <ImageSourceType>
 */
public abstract class Walkable<ImageFolderType, ImageSourceType> {
    
    private final static Logger LOGGER = Logger.getLogger(Walkable.class.getName()); 
    
    protected ImageFolderType source;
    
    public Walkable() {}
    
    public Walkable(ImageFolderType source) {
        this.source = source;
    }
    
    public abstract Stream<ImageSourceType> getChildren();
    
    public ImageFolderType getSource() {
        return this.source;
    }
    
    public static Walkable getInstance(Object obj) {
        Walkable walkable = null;
        if (obj instanceof File) {
            File file = (File)obj;
            if (file.isDirectory()) {
                walkable = new Directory(file);
            } else if (Files.isZIP(file)) {
                try {
                    walkable = new ZipFile(new java.util.zip.ZipFile(file));
                } catch (IOException e) {
                    LOGGER.warning(e.getMessage());
                }
            }
        }
        return walkable;
    }
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}