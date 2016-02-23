package com.tommybrettschneider.imageviewer.sort;

import com.tommybrettschneider.imageviewer.base.Walkable;
import com.tommybrettschneider.imageviewer.base.ZipFile;
import com.tommybrettschneider.imageviewer.sort.file.FileLastModifiedComparator;
import com.tommybrettschneider.imageviewer.sort.file.FileSizeComparator;
import com.tommybrettschneider.imageviewer.sort.zipentry.ZipEntryLastModifiedComparator;
import com.tommybrettschneider.imageviewer.sort.zipentry.ZipEntrySizeComparator;
import java.util.Comparator;

/**
 *
 * @author Tommy Brettschneider
 */
public class ComparatorFactory {

    private static Walkable walkable;

    private static ComparatorFactory factory;
    
    private Comparator ZIPENTRY_SIZE_COMPARATOR = new ZipEntrySizeComparator();
    private Comparator ZIPENTRY_LASTMODIFIED_COMPARATOR = new ZipEntryLastModifiedComparator();
    private Comparator FILE_SIZE_COMPARATOR = new FileSizeComparator();
    private Comparator FILE_LASTMODIFIED_COMPARATOR = new FileLastModifiedComparator();
    private Comparator FILENAME_COMPARATOR = new FileNameComparator();
    private Comparator IMAGEPROPERTIES_COMPARATOR = new ImagePropertiesComparator();
    private Comparator comparator;

    /**
     * Creates a new instance of ComparatorFactory
     */
    private ComparatorFactory() {
    }

    public static final ComparatorFactory getInstance(final Walkable w) {
        walkable = w;
        if (factory == null) {
            factory = new ComparatorFactory();
        }
        return factory;
    }

    public Comparator getSizeComparator() {
        comparator = (walkable instanceof ZipFile) ? ZIPENTRY_SIZE_COMPARATOR : FILE_SIZE_COMPARATOR;
        return comparator;
    }

    public Comparator getLastModifiedComparator() {
        comparator = (walkable instanceof ZipFile) ? ZIPENTRY_LASTMODIFIED_COMPARATOR : FILE_LASTMODIFIED_COMPARATOR;
        return comparator;
    }

    public Comparator getNameComparator() {
        comparator = FILENAME_COMPARATOR;
        return comparator;
    }
    
    public Comparator getImagePropertiesComparator() {
        comparator = IMAGEPROPERTIES_COMPARATOR;
        return comparator;
    }

    public Comparator getComparator() {
        return comparator;
    }
    
    public void setComparator(Comparator comparator) {
        this.comparator = comparator;
    }
}