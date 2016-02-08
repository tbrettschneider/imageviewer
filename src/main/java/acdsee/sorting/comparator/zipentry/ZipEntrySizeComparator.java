package acdsee.sorting.comparator.zipentry;

import acdsee.ui.components.thumbnail.ZipEntryThumbnail;
import java.util.Comparator;

/**
 *
 * @author Tommy Brettschneider
 */
public class ZipEntrySizeComparator implements Comparator<ZipEntryThumbnail> {

    @Override
    public int compare(ZipEntryThumbnail zipEntryA, ZipEntryThumbnail zipEntryB) {
        final Long zipEntrySizeA = zipEntryA.getZipEntry().getSize();
        final Long zipEntrySizeB = zipEntryB.getZipEntry().getSize();
        return zipEntrySizeA.compareTo(zipEntrySizeB);
    }
}