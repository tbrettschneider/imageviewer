/*
 * FileLastModifiedComparator.java
 *
 * Created on 1. Dezember 2006, 00:19
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package acdsee.sorting.comparator.file;

import java.util.Comparator;
import acdsee.ui.components.thumbnail.FileThumbnail;
import org.apache.commons.io.comparator.LastModifiedFileComparator;

/**
 *
 * @author Tommy Brettschneider
 */
public class FileLastModifiedComparator implements Comparator<FileThumbnail> {

    @Override
    public int compare(FileThumbnail fileA, FileThumbnail fileB) {
        return LastModifiedFileComparator.LASTMODIFIED_COMPARATOR.compare(fileA.getFile(), fileB.getFile());
    }
}
