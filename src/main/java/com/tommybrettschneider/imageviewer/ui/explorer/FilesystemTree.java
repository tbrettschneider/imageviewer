package com.tommybrettschneider.imageviewer.ui.explorer;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.Icon;
import javax.swing.JTree;
import javax.swing.ToolTipManager;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.filechooser.FileSystemView;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

/**
 * Diese Klasse bietet einen Dialog zur Auswahl eines Verzeichnisses. Sie
 * demonstriert einige Möglichkeiten der J2SE 1.4 zur Anbindung eines
 * Dateisystems an eine grafische Benutzeroberfläche.
 */
public class FilesystemTree extends JTree {

    private static final FileSystemView fileSystemView = FileSystemView.getFileSystemView();
    private static final TreeNode root = new DefaultMutableTreeNode(fileSystemView.getRoots()[0]);
    private File selection;

    /**
     * Konstruktor.
     */
    public FilesystemTree() {
        super(new FilesystemTreeNode("Roots", fileSystemView.getRoots(), fileSystemView));

        //setTransferHandler(new CopyImageHandler(this));
        ToolTipManager.sharedInstance().registerComponent(this);
        setRootVisible(false);
        setShowsRootHandles(true);
        getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        if (root.getChildCount() == 1) {
            expandRow(0);
        }

        // Setze einen JFileChooser-artigen Renderer.
        setCellRenderer(createDirectoryTreeRenderer());

        // Reagiere auf Änderungen der Baumselektion.
        addTreeSelectionListener((TreeSelectionEvent e) -> {
        });

        // Reagiere auf Doppelklicks; rufe #doOk() auf, gdw. der okButton enabled ist.
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

            }
        });

        // Versichere, dass die Kinder des FilesystemTreeNode vor dem Expandieren
        // geladen sind.
        addTreeWillExpandListener(new TreeWillExpandListener() {
            @Override
            public void treeWillExpand(TreeExpansionEvent e) {
                FilesystemTreeNode node = (FilesystemTreeNode) e.getPath().getLastPathComponent();
                try {
                    //setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    node.ensureChildrenAreLoaded();
                } finally {
                    //setCursor(Cursor.getDefaultCursor());
                }
            }

            @Override
            public void treeWillCollapse(TreeExpansionEvent e) {
            }
        });
    }

    /**
     * 
     * @param aTree
     * @param aUserObject
     * @return 
     */
    public static TreeNode findNode(JTree aTree, Object aUserObject) {
        if (aTree == null) {
            throw new IllegalArgumentException("Passed tree is null");
        }
        if (aUserObject == null) {
            throw new IllegalArgumentException("Passed user object is null");
        }
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) aTree.getModel().getRoot();
        return findNodeImpl(node, aUserObject);
    }

    private static DefaultMutableTreeNode findNodeImpl(DefaultMutableTreeNode aNode, Object aUserObject) {
        if (aNode.getUserObject().equals(aUserObject)) {
            return aNode;
        }
        int childCount = aNode.getChildCount();
        for (int i = 0; i < childCount; i++) {
            DefaultMutableTreeNode child = (DefaultMutableTreeNode) aNode.getChildAt(i);
            DefaultMutableTreeNode result = findNodeImpl(child, aUserObject);
            if (result != null) {
                return result;
            }
        }
        return null;
    }

    /**
     * Erzeuge und antworte den Zellen-Renderer des Verzeichnisbaums.
     */
    private TreeCellRenderer createDirectoryTreeRenderer() {
        return new DefaultTreeCellRenderer() {
            @Override
            public Component getTreeCellRendererComponent(JTree tree, Object value,
                    boolean isSelected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
                super.getTreeCellRendererComponent(tree, value, isSelected, expanded, false, row, hasFocus);
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
                Object userObject = node.getUserObject();
                if ((null == userObject) || !(userObject instanceof File)) {
                    return this;
                }

                File directory = (File) userObject;
                setText(getSystemDisplayName(directory));
                // change this to use custom icons
                setIcon(getSystemIcon(directory, isSelected));
                return this;
            }
        };
    }

    /**
     * Liefere den System-spezifischen Namen zum Verzeichnis. Vor der J2SE 1.4
     * hätte man hier den Dateinamen etwa mittels
     * <code>directory.getName()</code>angezeigt.
     */
    private String getSystemDisplayName(File directory) {
        return fileSystemView.getSystemDisplayName(directory);
    }

    /**
     * Liefere das System-spezifische Icon zum Verzeichnis. Vor der J2SE 1.4
     * hätte man hier auf die Datei-Icons des aktuellen Look&Feel zugegriffen.
     */
    private Icon getSystemIcon(File file, boolean isSelected) {
        return fileSystemView.getSystemIcon(file);
    }

    @Override
    public String getToolTipText(MouseEvent ev) {
        Point p = ev.getPoint();
        TreePath path = getClosestPathForLocation(p.x, p.y);
        return path.getLastPathComponent().toString();
    }
    
    private FileSystemView getFileSystemView() {
        return FilesystemTree.fileSystemView;
    }
}
