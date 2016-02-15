package com.tommybrettschneider.imageviewer.ui.explorer;

import com.tommybrettschneider.imageviewer.util.FileHelper;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;

import javax.swing.filechooser.FileSystemView;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import org.apache.commons.io.comparator.NameFileComparator;

/**
 * Eine TreeNode-Implementierung zur Abbildung der Baumstruktur eines
 * Dateisystems. Im Unterschied zur Oberklasse wird bei allen Zugriffen auf die
 * Kinder eines Knoten sichergestellt, dass die Kinder "geladen" sind. Laden
 * bedeutet hier, dass diejenigen Verzeichnisse, die die verwendete
 * Dateisystemsicht zu einem Ordner liefert, ermittelt worden sind und als
 * Baumkinder zugefügt sind.
 *
 * Werden die Fähigkeiten der Oberklasse DefaultMutableTreeNode nicht benötigt,
 * kann TreeNode auch direkt implementiert werden.
 *
 * @author Karsten Lentzsch
 */
public class FilesystemTreeNode extends DefaultMutableTreeNode implements Transferable {

    private final FileSystemView fileSystemView;

    private boolean childrenLoaded;

    /**
     * Erzeuge ein Verzeichnisbaumknoten zu einem Verzeichnis und einer
     * Dateisystemsicht.
     */
    private FilesystemTreeNode(File directory, FileSystemView fileSystemView) {
        super(directory);
        this.fileSystemView = fileSystemView;
        childrenLoaded = false;
    }

    /**
     * Erzeuge einen Verzeichnisbaumknoten zu einem Namen, einem Feld gegebener
     * Kinder und einer Dateisystemsicht.
     * @param name
     * @param children
     * @param fileSystemView
     */
    public FilesystemTreeNode(String name, File[] children, FileSystemView fileSystemView) {
        super(name);
        this.fileSystemView = fileSystemView;
        addChildren(children);
    }

    /**
     * Füge diesem Knoten Kinder zu.
     */
    private void addChildren(File[] children) {
        childrenLoaded = true;
        Arrays.stream(children).forEach(child->add(new FilesystemTreeNode(child, fileSystemView)));
    }

    /**
     * Liefere eine Aufzählung der Kinder. Stelle sicher, dass die Kinder
     * geladen sind.
     * @return 
     */
    @Override
    public Enumeration children() {
        ensureChildrenAreLoaded();
        return super.children();
    }

    /**
     * Versichere, dass die Kinder geladen sind.
     */
    public void ensureChildrenAreLoaded() {
        if (!childrenLoaded) {
            loadChildren();
        }
    }

    /**
     * Liefere ein Kind zu einem Index. Stelle sicher, dass die Kinder geladen
     * sind.
     * @return 
     */
    @Override
    public TreeNode getChildAt(int index) {
        ensureChildrenAreLoaded();
        return super.getChildAt(index);
    }

    /**
     * Liefere die Anzahl vorhandener Kinder. Stelle sicher, dass die Kinder
     * geladen sind.
     * @return 
     */
    @Override
    public int getChildCount() {
        ensureChildrenAreLoaded();
        return super.getChildCount();
    }

    /**
     * Liefere das diesem Knoten zugeordnete Verzeichnis.
     * @return 
     */
    public File getDirectory() {
        return (File) getUserObject();
    }

    @Override
    public boolean isLeaf() {
        return childrenLoaded && super.isLeaf();
    }

    /**
     * Ermittle die von der Dateisystemsicht gelieferten Kinder dieses Ordners.
     * Unter diesen filtere die traversierbaren Ordner heraus, sortiere sie mit
     * dem Standard-Comparator, erzeuge die Kinderknoten und füge sie diesem
     * Knoten zu.
     */
    private void loadChildren() {
        childrenLoaded = true;
        Arrays.stream(fileSystemView.getFiles(getDirectory(), true))
            .sorted(NameFileComparator.NAME_SYSTEM_COMPARATOR)
            .filter(file->fileSystemView.isTraversable(file) || FileHelper.isZIP(file))
            .forEach(file->add(new FilesystemTreeNode(file, fileSystemView)));
        }
    
    @Override
    public Object getTransferData(DataFlavor arg0) throws UnsupportedFlavorException, IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public DataFlavor[] getTransferDataFlavors() {
        return new DataFlavor[]{DataFlavor.javaFileListFlavor};
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor arg0) {
        return arg0 == DataFlavor.javaFileListFlavor;
    }
}