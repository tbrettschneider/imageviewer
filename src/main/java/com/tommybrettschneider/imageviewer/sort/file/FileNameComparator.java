package com.tommybrettschneider.imageviewer.sort.file;

import java.util.Comparator;
import com.tommybrettschneider.imageviewer.ui.thumbnail.FileThumbnail;
import org.apache.commons.io.comparator.NameFileComparator;

/**
 *
 * @author Tommy Brettschneider
 */
public class FileNameComparator implements Comparator<FileThumbnail> {

    @Override
    public int compare(FileThumbnail source, FileThumbnail target) {
        return NameFileComparator.NAME_COMPARATOR.compare(source.getSource(), target.getSource());
    }
}