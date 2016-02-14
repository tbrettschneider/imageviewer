package acdsee.ui.dialogs;

import acdsee.base.Walkable;
import acdsee.sorting.SortMenu;
import acdsee.ui.actions.FullscreenAction;
import acdsee.ui.components.directorybox.LimitedEntriesComboxBoxModel;
import acdsee.ui.components.explorer.FilesystemTreeNode;
import acdsee.ui.components.previewer.PreviewPane;
import acdsee.ui.components.thumbnail.DragThumbnailGlassPane;
import acdsee.ui.components.thumbnail.ScrollableThumbnailPane;
import acdsee.ui.util.UIUtils;
import java.awt.Dimension;
import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;
import javax.swing.tree.TreePath;

/**
 *
 * @author Tommy Brettschneider
 */
public class ApplicationWindow extends javax.swing.JFrame {

    private static final Logger LOGGER = Logger.getLogger(ApplicationWindow.class.getName());

    public static final JPanel GLASSPANE = new DragThumbnailGlassPane();
    private static ExecutorService executorService;
    
    /**
     * Creates new form AcdSeeFrame
     */
    public ApplicationWindow() {
        super("ImageViewer v0.1");
        final int threads = Runtime.getRuntime().availableProcessors()/2 + 1;
        executorService = Executors.newFixedThreadPool(threads);
        LOGGER.info("Running with " + threads + " threads...");
        initComponents();
        setGlassPane(GLASSPANE);
        previewpane.setExecutorService(executorService);
        ((ScrollableThumbnailPane) thumbnailPane).setExecutorService(executorService);
        ((ScrollableThumbnailPane) thumbnailPane).setPreviewpane(previewpane);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menubar = new javax.swing.JMenuBar();
        menuFile = new javax.swing.JMenu();
        menuNew = new javax.swing.JMenu();
        menuitemWindow = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JSeparator();
        menuitemFolder = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JSeparator();
        menuitemExit = new javax.swing.JMenuItem();
        menuEdit = new javax.swing.JMenu();
        menuitemCut = new javax.swing.JMenuItem();
        menuitemCopy = new javax.swing.JMenuItem();
        menuitemPaste = new javax.swing.JMenuItem();
        menuitemDelete = new javax.swing.JMenuItem();
        menuitemRename = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JSeparator();
        menuitemSearch = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JSeparator();
        menuitemSelectAll = new javax.swing.JMenuItem();
        menuitemSelectAllFiles = new javax.swing.JMenuItem();
        menuitemSelectAllImages = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JSeparator();
        menuitemRemoveSelection = new javax.swing.JMenuItem();
        menuitemInvertSelection = new javax.swing.JMenuItem();
        menuView = new javax.swing.JMenu();
        menuitemFullscreen = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JSeparator();
        menuitemSort = new SortMenu((ScrollableThumbnailPane)thumbnailPane);
        menuCreate = new javax.swing.JMenu();
        menuTools = new javax.swing.JMenu();
        menuitemOptions = new javax.swing.JMenuItem();
        menuDatabase = new javax.swing.JMenu();
        menuHelp = new javax.swing.JMenu();
        menuitemAbout = new javax.swing.JMenuItem();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        splitpaneH = new javax.swing.JSplitPane();
        panelRight = new javax.swing.JPanel();
        comboboxLocation = new acdsee.ui.components.directorybox.DirectoryBox();
        thumbnailPane = new ScrollableThumbnailPane();
        splitpaneV = new javax.swing.JSplitPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        treeFilesystem = new acdsee.ui.components.explorer.FilesystemTree();
        previewpane = PreviewPane.getPreviewPane();
        panelTop = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        toolbarMenu = new javax.swing.JToolBar();
        jPanel2 = new javax.swing.JPanel();
        toolbarButtons = new javax.swing.JToolBar();
        jXStatusBar1 = new org.jdesktop.swingx.JXStatusBar();
        jLabel1 = new javax.swing.JLabel();
        jSlider1 = new javax.swing.JSlider();

        menubar.setBorder(null);
        menubar.setAlignmentX(0.0F);

        menuFile.setMnemonic('F');
        menuFile.setText("File");

        menuNew.setText("New");

        menuitemWindow.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        menuitemWindow.setText("Window");
        menuNew.add(menuitemWindow);
        menuNew.add(jSeparator5);

        menuitemFolder.setText("Folder");
        menuNew.add(menuitemFolder);

        menuFile.add(menuNew);
        menuFile.add(jSeparator4);

        menuitemExit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.ALT_MASK));
        menuitemExit.setMnemonic('x');
        menuitemExit.setText("Exit");
        menuitemExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuitemExitActionPerformed(evt);
            }
        });
        menuFile.add(menuitemExit);

        menubar.add(menuFile);

        menuEdit.setMnemonic('E');
        menuEdit.setText("Edit");

        menuitemCut.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        menuitemCut.setText("Cut");
        menuEdit.add(menuitemCut);

        menuitemCopy.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        menuitemCopy.setText("Copy");
        menuEdit.add(menuitemCopy);

        menuitemPaste.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_MASK));
        menuitemPaste.setText("Paste");
        menuEdit.add(menuitemPaste);

        menuitemDelete.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_DELETE, 0));
        menuitemDelete.setText("Delete");
        menuEdit.add(menuitemDelete);

        menuitemRename.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, 0));
        menuitemRename.setText("Rename");
        menuEdit.add(menuitemRename);
        menuEdit.add(jSeparator3);

        menuitemSearch.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F3, 0));
        menuitemSearch.setText("Search");
        menuEdit.add(menuitemSearch);
        menuEdit.add(jSeparator2);

        menuitemSelectAll.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        menuitemSelectAll.setText("Select all");
        menuEdit.add(menuitemSelectAll);

        menuitemSelectAllFiles.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        menuitemSelectAllFiles.setText("Select all files");
        menuEdit.add(menuitemSelectAllFiles);

        menuitemSelectAllImages.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        menuitemSelectAllImages.setText("Select all images");
        menuEdit.add(menuitemSelectAllImages);
        menuEdit.add(jSeparator1);

        menuitemRemoveSelection.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        menuitemRemoveSelection.setText("Remove selection");
        menuEdit.add(menuitemRemoveSelection);

        menuitemInvertSelection.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        menuitemInvertSelection.setText("Invert selection");
        menuEdit.add(menuitemInvertSelection);

        menubar.add(menuEdit);

        menuView.setMnemonic('V');
        menuView.setText("View");

        menuitemFullscreen.setAction(new FullscreenAction("Full Screen", this));
        menuitemFullscreen.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        menuitemFullscreen.setMnemonic('u');
        menuitemFullscreen.setText("Full Screen");
        menuView.add(menuitemFullscreen);
        menuView.add(jSeparator6);

        menuitemSort.setMnemonic('S');
        menuitemSort.setText("Sort");
        ((SortMenu)menuitemSort).setSortableContainer(((ScrollableThumbnailPane)thumbnailPane).getPanel());
        menuView.add(menuitemSort);

        menubar.add(menuView);

        menuCreate.setMnemonic('C');
        menuCreate.setText("Create");
        menubar.add(menuCreate);

        menuTools.setMnemonic('T');
        menuTools.setText("Tools");

        menuitemOptions.setText("Options");
        menuitemOptions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuitemOptionsActionPerformed(evt);
            }
        });
        menuTools.add(menuitemOptions);

        menubar.add(menuTools);

        menuDatabase.setMnemonic('D');
        menuDatabase.setText("Database");
        menubar.add(menuDatabase);

        menuHelp.setMnemonic('H');
        menuHelp.setText("Help");

        menuitemAbout.setText("About...");
        menuHelp.add(menuitemAbout);

        menubar.add(menuHelp);

        jMenuItem1.setText("Item");
        jPopupMenu1.add(jMenuItem1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        splitpaneH.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        splitpaneH.setForeground(javax.swing.UIManager.getDefaults().getColor("Button.disabledForeground"));
        splitpaneH.setResizeWeight(0.33);
        splitpaneH.setOneTouchExpandable(true);

        panelRight.setBackground(new java.awt.Color(204, 204, 204));
        panelRight.setLayout(new java.awt.BorderLayout());
        panelRight.add(comboboxLocation, java.awt.BorderLayout.NORTH);

        thumbnailPane.setForeground(new java.awt.Color(255, 255, 255));
        panelRight.add(thumbnailPane, java.awt.BorderLayout.CENTER);

        splitpaneH.setRightComponent(panelRight);

        splitpaneV.setBorder(null);
        splitpaneV.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        splitpaneV.setResizeWeight(0.5);
        splitpaneV.setOneTouchExpandable(true);

        treeFilesystem.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                treeFilesystemValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(treeFilesystem);

        splitpaneV.setTopComponent(jScrollPane2);

        previewpane.setDnDEnabled(true);
        splitpaneV.setRightComponent(previewpane);

        splitpaneH.setLeftComponent(splitpaneV);

        getContentPane().add(splitpaneH, java.awt.BorderLayout.CENTER);

        panelTop.setLayout(new javax.swing.BoxLayout(panelTop, javax.swing.BoxLayout.Y_AXIS));

        jPanel1.setLayout(new java.awt.BorderLayout());

        toolbarMenu.setAlignmentX(0.0F);
        toolbarMenu.add(menubar);
        jPanel1.add(toolbarMenu, java.awt.BorderLayout.WEST);

        panelTop.add(jPanel1);

        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 0));

        toolbarButtons.setAlignmentX(0.0F);
        jPanel2.add(toolbarButtons);

        panelTop.add(jPanel2);

        getContentPane().add(panelTop, java.awt.BorderLayout.NORTH);

        jXStatusBar1.setBorder(javax.swing.BorderFactory.createEmptyBorder(3, 2, 2, 22));
        jXStatusBar1.setPreferredSize(new java.awt.Dimension(110, 22));
        jXStatusBar1.setLayout(new java.awt.BorderLayout());
        jXStatusBar1.add(jLabel1, java.awt.BorderLayout.CENTER);

        jSlider1.setMajorTickSpacing(50);
        jSlider1.setMaximum(500);
        jSlider1.setMinimum(100);
        jSlider1.setMinorTickSpacing(50);
        jSlider1.setPaintLabels(true);
        jSlider1.setPaintTicks(true);
        jSlider1.setSnapToTicks(true);
        jSlider1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider1StateChanged(evt);
            }
        });
        jXStatusBar1.add(jSlider1, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(jXStatusBar1, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void treeFilesystemValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_treeFilesystemValueChanged
        TreePath path = evt.getPath();
        final FilesystemTreeNode node = (FilesystemTreeNode) path.getLastPathComponent();
        final File fileObj = node.getDirectory();
        final Walkable walkable = Walkable.getInstance(fileObj);
        ((ScrollableThumbnailPane)thumbnailPane).setSource(walkable);
        /*new Thread() {
            @Override
            public void run() {
                if (!FileHelper.isZIP(fileObj)) {
                    final long size = FileUtils.sizeOfDirectory(node.getDirectory());
                    SwingUtilities.invokeLater(() -> {
                        jLabel1.setText(FileUtils.byteCountToDisplaySize(size));
                    });
                }
            }
        }.start();*/
        comboboxLocation.setSelectedItem(node.getDirectory());
        ((LimitedEntriesComboxBoxModel)comboboxLocation.getModel()).addElement(node.getDirectory());
    }//GEN-LAST:event_treeFilesystemValueChanged

    private void menuitemOptionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuitemOptionsActionPerformed
        // TODO add your handling code here:
        SetupProxyDialog connectionSettings = new SetupProxyDialog(this, true);
        UIUtils.centerOnScreen(connectionSettings);
        connectionSettings.setVisible(true);
        connectionSettings.requestFocusInWindow();
    }//GEN-LAST:event_menuitemOptionsActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        splitpaneV.setDividerLocation(0.5);
        splitpaneH.setDividerLocation(0.33);
    }//GEN-LAST:event_formWindowOpened

    private void menuitemExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuitemExitActionPerformed
        // TODO add your handling code here:
        int returnCode = JOptionPane.showInternalConfirmDialog(this.getContentPane(), "Really want to quit?", "Exit application", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (returnCode == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_menuitemExitActionPerformed

    private void jSlider1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSlider1StateChanged
        if (!jSlider1.getValueIsAdjusting()) {
            ScrollableThumbnailPane tp = (ScrollableThumbnailPane)thumbnailPane;
            tp.setThumbSize(jSlider1.getValue());
            int thumbSize = tp.getThumbSize();
            tp.getVerticalScrollBar().setBlockIncrement(thumbSize + 10);
            tp.getVerticalScrollBar().setUnitIncrement(thumbSize + 10);
            tp.getViewport().setMinimumSize(new Dimension(thumbSize + 10 + 10, thumbSize + 10 + 10));
        }
    }//GEN-LAST:event_jSlider1StateChanged

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String args[]) throws Exception {
        ToolTipManager.sharedInstance().setEnabled(true);
        ToolTipManager.sharedInstance().setInitialDelay(1500);
        ToolTipManager.sharedInstance().setDismissDelay(4000);
        ToolTipManager.sharedInstance().setReshowDelay(1500);
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new ApplicationWindow();
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setVisible(true);
            frame.toFront();
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private acdsee.ui.components.directorybox.DirectoryBox comboboxLocation;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSlider jSlider1;
    private org.jdesktop.swingx.JXStatusBar jXStatusBar1;
    private javax.swing.JMenu menuCreate;
    private javax.swing.JMenu menuDatabase;
    private javax.swing.JMenu menuEdit;
    private javax.swing.JMenu menuFile;
    private javax.swing.JMenu menuHelp;
    private javax.swing.JMenu menuNew;
    private javax.swing.JMenu menuTools;
    private javax.swing.JMenu menuView;
    private javax.swing.JMenuBar menubar;
    private javax.swing.JMenuItem menuitemAbout;
    private javax.swing.JMenuItem menuitemCopy;
    private javax.swing.JMenuItem menuitemCut;
    private javax.swing.JMenuItem menuitemDelete;
    private javax.swing.JMenuItem menuitemExit;
    private javax.swing.JMenuItem menuitemFolder;
    private javax.swing.JMenuItem menuitemFullscreen;
    private javax.swing.JMenuItem menuitemInvertSelection;
    private javax.swing.JMenuItem menuitemOptions;
    private javax.swing.JMenuItem menuitemPaste;
    private javax.swing.JMenuItem menuitemRemoveSelection;
    private javax.swing.JMenuItem menuitemRename;
    private javax.swing.JMenuItem menuitemSearch;
    private javax.swing.JMenuItem menuitemSelectAll;
    private javax.swing.JMenuItem menuitemSelectAllFiles;
    private javax.swing.JMenuItem menuitemSelectAllImages;
    private javax.swing.JMenu menuitemSort;
    private javax.swing.JMenuItem menuitemWindow;
    private javax.swing.JPanel panelRight;
    private javax.swing.JPanel panelTop;
    private acdsee.ui.components.previewer.PreviewPane previewpane;
    private javax.swing.JSplitPane splitpaneH;
    private javax.swing.JSplitPane splitpaneV;
    private javax.swing.JScrollPane thumbnailPane;
    private javax.swing.JToolBar toolbarButtons;
    private javax.swing.JToolBar toolbarMenu;
    private acdsee.ui.components.explorer.FilesystemTree treeFilesystem;
    // End of variables declaration//GEN-END:variables
}
