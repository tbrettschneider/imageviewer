/*
 * ZipEntryLastModifiedComparator.java
 *
 * Created on 26. März 2007, 21:09
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package acdsee.sorting.comparator.zipentry;

import acdsee.ui.components.thumbnail.ZipEntryThumbnail;
import java.util.Comparator;

/**
 *
 * @author Tommy Brettschneider
 */
public class ZipEntryLastModifiedComparator implements Comparator<ZipEntryThumbnail> {

    @Override
    public int compare(ZipEntryThumbnail zipEntryA, ZipEntryThumbnail zipEntryB) {
        final Long lastModifiedFileA = zipEntryA.getZipEntry().getTime();
        final Long lastModifiedFileB = zipEntryB.getZipEntry().getTime();
        return lastModifiedFileA.compareTo(lastModifiedFileB);
    }
}