/*
 * ComparatorFactory.java
 *
 * Created on 26. März 2007, 21:15
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package acdsee.sorting.comparator;

import acdsee.io.util.FileHelper;
import acdsee.sorting.comparator.file.FileLastModifiedComparator;
import acdsee.sorting.comparator.file.FileNameComparator;
import acdsee.sorting.comparator.file.FileSizeComparator;
import acdsee.sorting.comparator.zipentry.ZipEntryLastModifiedComparator;
import acdsee.sorting.comparator.zipentry.ZipEntryNameComparator;
import acdsee.sorting.comparator.zipentry.ZipEntrySizeComparator;
import java.io.File;
import java.util.Comparator;

/**
 *
 * @author Tommy Brettschneider
 */
public class ComparatorFactory {

    private static File source;

    private static ComparatorFactory cf;

    /**
     * Creates a new instance of ComparatorFactory
     */
    private ComparatorFactory() {
    }

    public static final ComparatorFactory getInstance(File f) {
        source = f;
        if (cf == null) {
            cf = new ComparatorFactory();
        }
        return cf;
    }

    public Comparator getSizeComparator() {
        if (FileHelper.isZIP(source)) {
            return new ZipEntrySizeComparator();
        }
        return new FileSizeComparator();
    }

    public Comparator getLastModifiedComparator() {
        if (FileHelper.isZIP(source)) {
            return new ZipEntryLastModifiedComparator();
        }
        return new FileLastModifiedComparator();
    }

    public Comparator getNameComparator() {
        if (FileHelper.isZIP(source)) {
            return new ZipEntryNameComparator();
        }
        return new FileNameComparator();
    }
}