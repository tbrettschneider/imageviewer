/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acdsee.base;

import java.io.File;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 *
 * @author tommy
 */
public class Directory extends Walkable<File, File> {
    
    public Directory(File directory) {
        this.source = directory;
    }
    
    @Override
    public Stream<File> getChildren() {
        File directory = getSource();
        return Arrays.stream(directory.listFiles()).parallel();
    }    
}
