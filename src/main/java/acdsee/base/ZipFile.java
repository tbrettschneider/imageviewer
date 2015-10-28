/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acdsee.base;

import java.util.stream.Stream;
import java.util.zip.ZipEntry;

/**
 *
 * @author tommy
 */
public class ZipFile extends Walkable<java.util.zip.ZipFile, ZipEntry> {

    public ZipFile(java.util.zip.ZipFile zipFile) {
        source = zipFile;
    }
    
    @Override
    public Stream<ZipEntry> getChildren() {
        java.util.zip.ZipFile zipFile = getSource();
        return (Stream<ZipEntry>)zipFile.stream();
    } 
}