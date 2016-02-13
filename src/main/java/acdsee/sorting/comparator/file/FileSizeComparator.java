package acdsee.sorting.comparator.file;

import java.util.Comparator;
import acdsee.ui.components.thumbnail.FileThumbnail;
import org.apache.commons.io.comparator.SizeFileComparator;

/**
 *
 * @author Tommy Brettschneider
 */
public class FileSizeComparator implements Comparator<FileThumbnail> {

    @Override
    public int compare(FileThumbnail source, FileThumbnail target) {
        return SizeFileComparator.SIZE_COMPARATOR.compare(source.getSource(), target.getSource());
    }
}