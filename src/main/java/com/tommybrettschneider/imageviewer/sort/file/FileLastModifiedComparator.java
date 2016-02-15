package com.tommybrettschneider.imageviewer.sort.file;

import java.util.Comparator;
import com.tommybrettschneider.imageviewer.ui.thumbnail.FileThumbnail;
import org.apache.commons.io.comparator.LastModifiedFileComparator;

/**
 *
 * @author Tommy Brettschneider
 */
public class FileLastModifiedComparator implements Comparator<FileThumbnail> {

    @Override
    public int compare(FileThumbnail source, FileThumbnail target) {
        return LastModifiedFileComparator.LASTMODIFIED_COMPARATOR.compare(source.getSource(), target.getSource());
    }
}
