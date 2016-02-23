package com.tommybrettschneider.imageviewer.sort.zipentry;

import com.tommybrettschneider.imageviewer.ui.thumbnail.ZipEntryThumbnail;
import java.io.Serializable;
import java.util.Comparator;

/**
 *
 * @author Tommy Brettschneider
 */
public class ZipEntrySizeComparator implements Comparator<ZipEntryThumbnail>, Serializable {

    @Override
    public int compare(ZipEntryThumbnail source, ZipEntryThumbnail target) {
        final Long zipEntrySizeA = source.getSource().getSize();
        final Long zipEntrySizeB = target.getSource().getSize();
        return zipEntrySizeA.compareTo(zipEntrySizeB);
    }
}