package com.tommybrettschneider.imageviewer.sort;

import com.tommybrettschneider.imageviewer.ui.thumbnail.Thumbnail;
import java.io.Serializable;
import java.util.Comparator;

/**
 *
 * @author Tommy Brettschneider
 */
public class ImagePropertiesComparator implements Comparator<Thumbnail>, Serializable {

    @Override
    public int compare(Thumbnail source, Thumbnail target) {
        final Integer maxPixelSource = source.getImageWidth() * source.getImageHeight();
        final Integer maxPixelTarget = target.getImageWidth() * target.getImageWidth();
        return maxPixelSource.compareTo(maxPixelTarget);
    }
}