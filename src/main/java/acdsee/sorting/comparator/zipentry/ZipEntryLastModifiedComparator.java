package acdsee.sorting.comparator.zipentry;

import acdsee.ui.components.thumbnail.ZipEntryThumbnail;
import java.util.Comparator;

/**
 *
 * @author Tommy Brettschneider
 */
public class ZipEntryLastModifiedComparator implements Comparator<ZipEntryThumbnail> {

    @Override
    public int compare(ZipEntryThumbnail source, ZipEntryThumbnail target) {
        final Long lastModifiedFileA = source.getSource().getTime();
        final Long lastModifiedFileB = target.getSource().getTime();
        return lastModifiedFileA.compareTo(lastModifiedFileB);
    }
}