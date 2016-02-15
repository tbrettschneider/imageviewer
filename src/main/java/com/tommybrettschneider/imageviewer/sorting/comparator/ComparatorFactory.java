package com.tommybrettschneider.imageviewer.sorting.comparator;

import com.tommybrettschneider.imageviewer.sorting.comparator.common.ImagePropertiesComparator;
import com.tommybrettschneider.imageviewer.base.Walkable;
import com.tommybrettschneider.imageviewer.base.ZipFile;
import com.tommybrettschneider.imageviewer.sorting.comparator.file.FileLastModifiedComparator;
import com.tommybrettschneider.imageviewer.sorting.comparator.file.FileNameComparator;
import com.tommybrettschneider.imageviewer.sorting.comparator.file.FileSizeComparator;
import com.tommybrettschneider.imageviewer.sorting.comparator.zipentry.ZipEntryLastModifiedComparator;
import com.tommybrettschneider.imageviewer.sorting.comparator.zipentry.ZipEntryNameComparator;
import com.tommybrettschneider.imageviewer.sorting.comparator.zipentry.ZipEntrySizeComparator;
import java.util.Comparator;

/**
 *
 * @author Tommy Brettschneider
 */
public class ComparatorFactory {

    private static Walkable walkable;

    private static ComparatorFactory factory;
    
    private static final Comparator ZIPENTRY_SIZE_COMPARATOR = new ZipEntrySizeComparator();
    private static final Comparator ZIPENTRY_LASTMODIFIED_COMPARATOR = new ZipEntryLastModifiedComparator();
    private static final Comparator ZIPENTRY_NAME_COMPARATOR = new ZipEntryNameComparator();
    private static final Comparator FILE_SIZE_COMPARATOR = new FileSizeComparator();
    private static final Comparator FILE_LASTMODIFIED_COMPARATOR = new FileLastModifiedComparator();
    private static final Comparator FILE_NAME_COMPARATOR = new FileNameComparator();
    private static final Comparator IMAGEPROPERTIES_COMPARATOR = new ImagePropertiesComparator();
    private static Comparator currentComparator;

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
        currentComparator = (walkable instanceof ZipFile) ? ZIPENTRY_SIZE_COMPARATOR : FILE_SIZE_COMPARATOR;
        return currentComparator;
    }

    public Comparator getLastModifiedComparator() {
        currentComparator = (walkable instanceof ZipFile) ? ZIPENTRY_LASTMODIFIED_COMPARATOR : FILE_LASTMODIFIED_COMPARATOR;
        return currentComparator;
    }

    public Comparator getNameComparator() {
        currentComparator = (walkable instanceof ZipFile) ? ZIPENTRY_NAME_COMPARATOR : FILE_NAME_COMPARATOR;
        return currentComparator;
    }
    
    public Comparator getImagePropertiesComparator() {
        currentComparator = IMAGEPROPERTIES_COMPARATOR;
        return currentComparator;
    }

    public Comparator getCurrentComparator() {
        return currentComparator;
    }

    public Comparator reverse() {
        if (getCurrentComparator() != null) {
            currentComparator = getCurrentComparator().reversed();
        }
        return currentComparator;
    }
}