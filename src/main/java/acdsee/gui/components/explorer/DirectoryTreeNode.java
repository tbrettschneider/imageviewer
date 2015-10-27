package acdsee.gui.components.explorer;

import acdsee.io.util.FileHelper;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.filechooser.FileSystemView;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

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
public class DirectoryTreeNode extends DefaultMutableTreeNode implements Transferable {

    private final FileSystemView fileSystemView;

    private boolean childrenAreLoaded;

    /**
     * Erzeuge ein Verzeichnisbaumknoten zu einem Verzeichnis und einer
     * Dateisystemsicht.
     */
    private DirectoryTreeNode(File directory, FileSystemView fileSystemView) {
        super(directory);
        this.fileSystemView = fileSystemView;
        childrenAreLoaded = false;
    }

    /**
     * Erzeuge einen Verzeichnisbaumknoten zu einem Namen, einem Feld gegebener
     * Kinder und einer Dateisystemsicht.
     * @param name
     * @param children
     * @param fileSystemView
     */
    public DirectoryTreeNode(String name, File[] children, FileSystemView fileSystemView) {
        super(name);
        this.fileSystemView = fileSystemView;
        addChildren(children);
    }

    /**
     * Füge diesem Knoten Kinder zu.
     */
    private void addChildren(File[] children) {
        childrenAreLoaded = true;
        for (File child : children) {
            add(new DirectoryTreeNode(child, fileSystemView));
        }
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
        if (!childrenAreLoaded) {
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
        return childrenAreLoaded && super.isLeaf();
    }

    /**
     * Ermittle die von der Dateisystemsicht gelieferten Kinder dieses Ordners.
     * Unter diesen filtere die traversierbaren Ordner heraus, sortiere sie mit
     * dem Standard-Comparator, erzeuge die Kinderknoten und füge sie diesem
     * Knoten zu.
     */
    private void loadChildren() {
        childrenAreLoaded = true;

        File[] fileList = fileSystemView.getFiles(getDirectory(), false);

        if (null == fileList) {
            return;
        }

        // Sammle die traversierbaren Unterordner.
        List<File> childDirectories = new ArrayList<>();
        for (File file : fileList) {
            // ATWORK!
            if (fileSystemView.isTraversable(file) || FileHelper.isZIP(file)) {
                childDirectories.add(file);
            }
            //ADDED 
            add(new DirectoryTreeNode(file, fileSystemView));
        } // Sortiere die Unterordner.
//		Collections.sort(childDirectories);
        // Erzeuge die Kinderknoten und füge sie diesem Knoten zu.
//		Iterator i = childDirectories.iterator();
//		while (i.hasNext()) {
//			File aDirectory = (File) i.next();
//			add(new DirectoryTreeNode(aDirectory, fileSystemView));
//		}
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