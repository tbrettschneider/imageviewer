package acdsee.sorting.comparator.zipentry;

import acdsee.ui.components.thumbnail.ZipEntryThumbnail;
import java.util.Comparator;
import java.util.zip.ZipEntry;

/**
 *
 * @author Tommy Brettschneider
 */
public class ZipEntryNameComparator implements Comparator<ZipEntryThumbnail> {

    @Override
    public int compare(ZipEntryThumbnail zipEntryA, ZipEntryThumbnail zipEntryB) {
        final ZipEntry f1 = zipEntryA.getZipEntry();
        final ZipEntry f2 = zipEntryB.getZipEntry();
        return f1.getName().compareTo(f2.getName());
    }
}