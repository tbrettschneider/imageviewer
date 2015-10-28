/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acdsee.base;

import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author tommy
 */
public class Directory extends Walkable<File, File> {

    @Override
    public List<File> getChildren() {
        File directory = getSource();
        return Arrays.asList(directory.listFiles());
    }    
}
