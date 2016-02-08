package acdsee.base;

import java.util.stream.Stream;
import java.util.zip.ZipEntry;

/**
 *
 * @author Tommy Brettschneider
 */
public class ZipFile extends Walkable<java.util.zip.ZipFile, ZipEntry> {

    public ZipFile(java.util.zip.ZipFile zipFile) {
        source = zipFile;
    }
    
    @Override
    public Stream<ZipEntry> getChildren() {
        java.util.zip.ZipFile zipFile = getSource();
        return (Stream<ZipEntry>)zipFile.stream().parallel();
    } 
}