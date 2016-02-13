package acdsee.sorting.comparator.common;

import acdsee.ui.components.thumbnail.Thumbnail;
import java.util.Comparator;

/**
 *
 * @author Tommy Brettschneider
 */
public class ImagePropertiesComparator implements Comparator<Thumbnail> {

    @Override
    public int compare(Thumbnail source, Thumbnail target) {
        final Integer maxPixelSource = source.getImageWidth() * source.getImageHeight();
        final Integer maxPixelTarget = target.getImageWidth() * target.getImageWidth();
        return maxPixelSource.compareTo(maxPixelTarget);
    }
}