package com.tommybrettschneider.imageviewer.sort;

import com.tommybrettschneider.imageviewer.ui.thumbnail.Thumbnail;
import java.io.Serializable;
import java.util.Comparator;

/**
 *
 * @author Tommy Brettschneider
 */
public class FileNameComparator implements Comparator<Thumbnail>, Serializable {

    @Override
    public int compare(Thumbnail source, Thumbnail target) {
        return source.getSourceFilename().compareTo(target.getSourceFilename());
    }
}