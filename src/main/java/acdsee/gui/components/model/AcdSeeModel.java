/*
 * AcdSeeModel.java
 *
 * Created on 4. Dezember 2006, 23:55
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package acdsee.gui.components.model;

import java.beans.PropertyChangeSupport;
import java.io.File;

/**
 *
 * @author Tommy Brettschneider
 */
public class AcdSeeModel extends PropertyChangeSupport {

    private static final String PROPERTY_CURRENT_DIRECTORY = "currentDirectory";
    private static final String PROPERTY_CURRENT_IMAGE = "currentImage";
    
    private File currentDirectory;
    private File currentImage;

    /**
     * Creates a new instance of AcdSeeModel
     * @param source
     */
    public AcdSeeModel(Object source) {
        super(source);
    }

    public File getCurrentDirectory() {
        return currentDirectory;
    }

    public File getCurrentImage() {
        return currentImage;
    }

    public void setCurrentDirectory(File currentDirectory) {
        File oldCurrentDirectory = this.currentDirectory;
        this.currentDirectory = currentDirectory;
        firePropertyChange(PROPERTY_CURRENT_DIRECTORY, oldCurrentDirectory, currentDirectory);
    }

    public void setCurrentImage(File currentImage) {
        File oldCurrentImage = this.currentImage;
        this.currentImage = currentImage;
        firePropertyChange(PROPERTY_CURRENT_IMAGE, oldCurrentImage, currentImage);
    }
}