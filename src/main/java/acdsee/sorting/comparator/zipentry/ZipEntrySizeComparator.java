/*
 * ZipEntrySizeComparator.java
 *
 * Created on 26. März 2007, 21:05
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package acdsee.sorting.comparator.zipentry;

import acdsee.gui.components.thumbnail.ZipEntryThumbnail;
import java.util.Comparator;

/**
 *
 * @author Tommy Brettschneider
 */
public class ZipEntrySizeComparator implements Comparator<ZipEntryThumbnail> {

    @Override
    public int compare(ZipEntryThumbnail zipEntryA, ZipEntryThumbnail zipEntryB) {
        final Long zipEntrySizeA = zipEntryA.getZipEntry().getSize();
        final Long zipEntrySizeB = zipEntryB.getZipEntry().getSize();
        return zipEntrySizeA.compareTo(zipEntrySizeB);
    }
}