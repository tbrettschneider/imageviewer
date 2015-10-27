/*
 * ImagePropertiesComparator.java
 *
 * Created on 1. Dezember 2006, 21:15
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package acdsee.sorting.comparator;

import acdsee.gui.components.thumbnail.AbstractThumbnail;
import java.util.Comparator;

/**
 *
 * @author Tommy Brettschneider
 */
public class ImagePropertiesComparator implements Comparator<AbstractThumbnail> {

    @Override
    public int compare(AbstractThumbnail thumbA, AbstractThumbnail thumbB) {
        final Integer thumbAMaxPixel = thumbA.getImageWidth() * thumbA.getImageHeight();
        final Integer thumbBMaxPixel = thumbB.getImageWidth() * thumbB.getImageWidth();
        return thumbAMaxPixel.compareTo(thumbBMaxPixel);
    }
}