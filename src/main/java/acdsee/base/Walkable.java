/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acdsee.base;

import acdsee.io.util.FileHelper;
import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author Tommy Brettschneider
 * @param <Source>
 * @param <ChildType>
 */
public abstract class Walkable<Source, ChildType> {
    
    protected Source source;
    
    public Walkable() {}
    
    public Walkable(Source source) {
        this.source = source;
    }
    
    public abstract Stream<ChildType> getChildren();
    
    public Source getSource() {
        return this.source;
    }
    
    public static Walkable getInstance(Object obj) {
        Walkable walkable = null;
        if (obj instanceof File) {
            File file = (File)obj;
            if (file.isDirectory()) {
                walkable = new Directory(file);
            } else if (FileHelper.isZIP(file)) {
                try {
                    walkable = new ZipFile(new java.util.zip.ZipFile(file));
                } catch (IOException e) {
                    //TODO 
                    e.printStackTrace();
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