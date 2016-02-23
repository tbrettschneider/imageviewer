package com.tommybrettschneider.imageviewer.sort.zipentry;

import com.tommybrettschneider.imageviewer.ui.thumbnail.ZipEntryThumbnail;
import java.io.Serializable;
import java.util.Comparator;

/**
 *
 * @author Tommy Brettschneider
 */
public class ZipEntryLastModifiedComparator implements Comparator<ZipEntryThumbnail>, Serializable {

    @Override
    public int compare(ZipEntryThumbnail source, ZipEntryThumbnail target) {
        final Long lastModifiedFileA = source.getSource().getTime();
        final Long lastModifiedFileB = target.getSource().getTime();
        return lastModifiedFileA.compareTo(lastModifiedFileB);
    }
}