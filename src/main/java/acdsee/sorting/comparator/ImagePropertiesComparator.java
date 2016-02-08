package acdsee.sorting.comparator;

import acdsee.ui.components.thumbnail.Thumbnail;
import java.util.Comparator;

/**
 *
 * @author Tommy Brettschneider
 */
public class ImagePropertiesComparator implements Comparator<Thumbnail> {

    @Override
    public int compare(Thumbnail thumbA, Thumbnail thumbB) {
        final Integer thumbAMaxPixel = thumbA.getImageWidth() * thumbA.getImageHeight();
        final Integer thumbBMaxPixel = thumbB.getImageWidth() * thumbB.getImageWidth();
        return thumbAMaxPixel.compareTo(thumbBMaxPixel);
    }
}