/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acdsee.base;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;

/**
 *
 * @author tommy
 */
public class ZipFile extends Walkable<java.util.zip.ZipFile, ZipEntry> {

    @Override
    public List<ZipEntry> getChildren() {
        java.util.zip.ZipFile zipFile = getSource();
        Stream<ZipEntry> zipEntries = (Stream<ZipEntry>) zipFile.stream();
        return zipEntries.collect(Collectors.toList());
    } 
}
