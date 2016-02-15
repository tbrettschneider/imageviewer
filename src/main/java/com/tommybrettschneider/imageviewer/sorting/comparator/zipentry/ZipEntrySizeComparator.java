package com.tommybrettschneider.imageviewer.sorting.comparator.zipentry;

import com.tommybrettschneider.imageviewer.ui.components.thumbnail.ZipEntryThumbnail;
import java.util.Comparator;

/**
 *
 * @author Tommy Brettschneider
 */
public class ZipEntrySizeComparator implements Comparator<ZipEntryThumbnail> {

    @Override
    public int compare(ZipEntryThumbnail source, ZipEntryThumbnail target) {
        final Long zipEntrySizeA = source.getSource().getSize();
        final Long zipEntrySizeB = target.getSource().getSize();
        return zipEntrySizeA.compareTo(zipEntrySizeB);
    }
}