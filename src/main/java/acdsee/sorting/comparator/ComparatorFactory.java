package acdsee.sorting.comparator;

import acdsee.base.Walkable;
import acdsee.base.ZipFile;
import acdsee.sorting.comparator.file.FileLastModifiedComparator;
import acdsee.sorting.comparator.file.FileNameComparator;
import acdsee.sorting.comparator.file.FileSizeComparator;
import acdsee.sorting.comparator.zipentry.ZipEntryLastModifiedComparator;
import acdsee.sorting.comparator.zipentry.ZipEntryNameComparator;
import acdsee.sorting.comparator.zipentry.ZipEntrySizeComparator;
import java.util.Comparator;

/**
 *
 * @author Tommy Brettschneider
 */
public class ComparatorFactory {

    private static Walkable walkable;

    private static ComparatorFactory cf;

    /**
     * Creates a new instance of ComparatorFactory
     */
    private ComparatorFactory() {
    }

    public static final ComparatorFactory getInstance(Walkable f) {
        walkable = f;
        if (cf == null) {
            cf = new ComparatorFactory();
        }
        return cf;
    }

    public Comparator getSizeComparator() {
        if (walkable instanceof ZipFile) {
            return new ZipEntrySizeComparator();
        }
        return new FileSizeComparator();
    }

    public Comparator getLastModifiedComparator() {
        if (walkable instanceof ZipFile) {
            return new ZipEntryLastModifiedComparator();
        }
        return new FileLastModifiedComparator();
    }

    public Comparator getNameComparator() {
        if (walkable instanceof ZipFile) {
            return new ZipEntryNameComparator();
        }
        return new FileNameComparator();
    }
    
    public Comparator getImagePropertiesComparator() {
        return new ImagePropertiesComparator();
    }
}