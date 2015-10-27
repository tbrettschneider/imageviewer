/*
 * FileSizeComparator.java
 *
 * Created on 1. Dezember 2006, 00:21
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package acdsee.sorting.comparator.file;

import java.util.Comparator;
import acdsee.gui.components.thumbnail.FileThumbnail;
import org.apache.commons.io.comparator.SizeFileComparator;

/**
 *
 * @author Tommy Brettschneider
 */
public class FileSizeComparator implements Comparator<FileThumbnail> {

    @Override
    public int compare(FileThumbnail fileA, FileThumbnail fileB) {
        return SizeFileComparator.SIZE_COMPARATOR.compare(fileA.getFile(), fileB.getFile());
    }
}