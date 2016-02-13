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
    public int compare(ZipEntryThumbnail source, ZipEntryThumbnail target) {
        final ZipEntry f1 = source.getSource();
        final ZipEntry f2 = target.getSource();
        return f1.getName().compareTo(f2.getName());
    }
}