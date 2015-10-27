/*
 * FileNameComparator.java
 *
 * Created on 1. Dezember 2006, 13:32
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package acdsee.sorting.comparator.file;

import java.util.Comparator;
import acdsee.gui.components.thumbnail.FileThumbnail;
import org.apache.commons.io.comparator.NameFileComparator;

/**
 *
 * @author Tommy Brettschneider
 */
public class FileNameComparator implements Comparator<FileThumbnail> {

    @Override
    public int compare(FileThumbnail fileA, FileThumbnail fileB) {
        return NameFileComparator.NAME_COMPARATOR.compare(fileA.getFile(), fileB.getFile());
    }
}
