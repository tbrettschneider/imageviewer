package acdsee.sorting.comparator.file;

import java.util.Comparator;
import acdsee.ui.components.thumbnail.FileThumbnail;
import org.apache.commons.io.comparator.LastModifiedFileComparator;

/**
 *
 * @author Tommy Brettschneider
 */
public class FileLastModifiedComparator implements Comparator<FileThumbnail> {

    @Override
    public int compare(FileThumbnail source, FileThumbnail target) {
        return LastModifiedFileComparator.LASTMODIFIED_COMPARATOR.compare(source.getFile(), target.getFile());
    }
}
