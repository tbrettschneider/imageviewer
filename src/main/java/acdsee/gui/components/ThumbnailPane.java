package acdsee.gui.components;

import acdsee.base.Directory;
import acdsee.base.Walkable;
import acdsee.gui.components.thumbnail.ZipEntryThumbnail;
import acdsee.gui.components.thumbnail.FileThumbnail;
import acdsee.gui.components.thumbnail.AbstractThumbnail;
import acdsee.sorting.ui.SortMenu;
import java.awt.Color;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import org.apache.commons.io.FileUtils;

public class ThumbnailPane extends JScrollPane {

    private static ThumbnailPane thumbnailPane;
    private JPanel panel;
    private PreviewPane previewpane;
    private ExecutorService executorService;
    private Walkable walkable;
    private int thumbSize = 135;
    private final PropertyChangeSupport pcs;
    private MouseAdapter mouseListener;

    public ExecutorService getExecutorService() {
        return executorService;
    }

    public void setExecutorService(ExecutorService executorService) {
        this.executorService = executorService;
    }
    
    public static final ThumbnailPane getThumbnailPane() {
        if (thumbnailPane == null) {
            thumbnailPane = new ThumbnailPane();
            int thumbSize = thumbnailPane.getThumbSize();
            thumbnailPane.getVerticalScrollBar().setBlockIncrement(thumbSize + 10);
            thumbnailPane.getVerticalScrollBar().setUnitIncrement(thumbSize + 10);
            thumbnailPane.getViewport().setMinimumSize(new Dimension(thumbSize + 10 + 10, thumbSize + 10 + 10));
        }
        return thumbnailPane;
    }
    
    /**
     * Creates a new instance of ThumbnailPane
     */
    public ThumbnailPane() {
        super();
        this.mouseListener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                getPanel().requestFocusInWindow();
                
                final AbstractThumbnail proxy = (AbstractThumbnail)evt.getSource();
                
                if (SwingUtilities.isLeftMouseButton(evt) && evt.getClickCount() > 1) {
                    if (proxy instanceof FileThumbnail) {
                        File f = ((FileThumbnail) proxy).getFile();
                        Walkable walkable = Walkable.getInstance(f);
                        setSource(walkable);
                    }
                }
                
                if (SwingUtilities.isRightMouseButton(evt)) {
                    JPopupMenu popup = new JPopupMenu();
                    
                    // Desktop integration...
                    JMenu nativeCommands = new JMenu("Native Cmd");
                    if (proxy instanceof FileThumbnail && Desktop.isDesktopSupported()) {
                        final File selectedFile = ((FileThumbnail) proxy).getFile();
                        if (selectedFile.isFile()) {
                            JMenuItem nativeCmd = new JMenuItem("Open...");
                            nativeCmd.addActionListener((ActionEvent arg0) -> {
                                try {
                                    Desktop.getDesktop().open(selectedFile);
                                } catch (IOException ex) {
                                    Logger.getLogger(ThumbnailPane.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            });
                            nativeCommands.add(nativeCmd);
                            
                            nativeCmd = new JMenuItem("Edit...");
                            nativeCmd.addActionListener((ActionEvent arg0) -> {
                                try {
                                    Desktop.getDesktop().edit(selectedFile);
                                } catch (IOException ex) {
                                    Logger.getLogger(ThumbnailPane.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            });
                            nativeCommands.add(nativeCmd);
                        }
                        popup.add(nativeCommands);
                        popup.addSeparator();
                    }
                    
                    //JMenuItem mi = new JMenuItem(new DeleteAction("Delete"));
                    JMenuItem mi = new JMenuItem("Delete");
                    mi.addActionListener((ActionEvent arg0) -> {
                        final File selectedFile = ((FileThumbnail) proxy).getFile();
                        FileUtils.deleteQuietly(selectedFile);
                        getPanel().remove(proxy);
                        revalidate();
                    });
                    popup.add(mi);
                    mi = new JMenuItem("Refresh...");
                    mi.addActionListener((ActionEvent arg0) -> {
                        refresh();
                    });
                    popup.add(mi);
                    popup.show(proxy, evt.getX(), evt.getY());
                }
                
                try {
//                statusbarLabelTotalObjects.setText("Total " + proxy.getFile().getParentFile().listFiles().length + " objects (" + FileHelper.getSizeInMegaByte(FileHelper.getFileOrDirectorySize(proxy.getFile().getParentFile())) + " MB)");
//                statusbarLabelFileProps.setText(FileHelper.getSt_SizeInKiloByte(proxy.getFile()) + " KB, Modified Date: " + FileHelper.getSt_LastModified(proxy.getFile()));
//                statusbarLabelFileName.setText(proxy.getFile().getName());
//                statusbarLabelFileIcon.setIcon(FileSystemView.getFileSystemView().getSystemIcon(proxy.getFile()));
//                statusbarLabelImgProps.setText(proxy.getImageWidth() + "x" + proxy.getImageHeight() + "x" + proxy.getImageDepth());
                    
                    if (evt.getClickCount() > 1) {
//                        Desktop.getDesktop().open(((FileThumbnail)proxy).getFile());
                        final JFrame w = new JFrame();
                        w.setExtendedState(JFrame.MAXIMIZED_BOTH);
                        
                        w.setUndecorated(true);
                        w.setBounds(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds());
                        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
                        gd.setFullScreenWindow(w);
                        
                        final Container parent = previewpane.getParent();
                        
                        w.getContentPane().setBackground(Color.BLACK);
                        w.getContentPane().add(previewpane);
                        w.setVisible(true);
                        previewpane.setFocusable(true);
                        previewpane.requestFocusInWindow();
                        previewpane.addKeyListener(new KeyAdapter() {
                            @Override
                            public void keyPressed(KeyEvent evt) {
                                int keyCode = evt.getKeyCode();
                                if (keyCode == KeyEvent.VK_ESCAPE) {
                                    try {
                                        w.setVisible(false);
                                        previewpane.removeKeyListener(this);
                                        parent.add(previewpane);
                                        w.dispose();
                                    } catch (Exception ex) {
                                        //TODO
                                    }
                                }
                            }
                        });
                    }
                    
                    if (proxy instanceof FileThumbnail) {
                        previewpane.setSource(/*proxy.loadOriginalImage()); */((FileThumbnail) proxy).getFile());
                    } else if (proxy instanceof ZipEntryThumbnail) {
                        previewpane.setSource(((ZipEntryThumbnail) proxy).getInputStream());
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        };

        setBorder(null);
        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        setRequestFocusEnabled(true);
        setAutoscrolls(true);
        /*getVerticalScrollBar().setBlockIncrement(getThumbSize() + 10);
        getVerticalScrollBar().setUnitIncrement(getThumbSize() + 10);
        getViewport().setMinimumSize(new Dimension(getThumbSize() + 10 + 10, getThumbSize() + 10 + 10));*/
        panel = new JPanel() {
            private final Dimension MINSIZE = new Dimension(getThumbSize() + 10 + 10, getThumbSize() + 10 + 10);

            @Override
            public java.awt.Dimension getMinimumSize() {
                return MINSIZE;
            }

            @Override
            public java.awt.Dimension getPreferredSize() {
                final java.awt.Insets insets = getInsets();
                final int count = getComponentCount();
                final int hgap = ((java.awt.FlowLayout) getLayout()).getHgap();
                final int vgap = ((java.awt.FlowLayout) getLayout()).getVgap();
                final int cols = getVisibleRect().width / (getThumbSize() + 10);

                if (cols == 0) {
                    return getMinimumSize();
                }

                final java.awt.Dimension d = new java.awt.Dimension(
                        cols * (getThumbSize() + hgap) + hgap,
                        (count / cols) * (getThumbSize() + vgap) + vgap);

                // Dont forget the frame's insets
                d.width += insets.left + insets.right;
                d.height += insets.top + insets.bottom;

                return d;
            }
        };
        panel.setBorder(null);
        panel.setBackground(Color.WHITE);
        SelectionContainer sc = new SelectionContainer(panel);
        getViewport().add(sc);
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        sc.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    FileThumbnail at = (FileThumbnail) evt.getSource();
                    setSource(at.getFile());
                } catch (Exception ex) {
                    //TODO                    
                }

                if (ThumbnailPane.this.isVisible()) {
                    ThumbnailPane.this.requestFocusInWindow();
                }
            }

            @Override
            public void mousePressed(MouseEvent evt) {
                if (SwingUtilities.isRightMouseButton(evt)) {
                    final JPopupMenu sortPopupMenu = new JPopupMenu();
                    final SortMenu sort = new SortMenu(ThumbnailPane.this);
                    sort.setSortableContainer(panel);
                    sortPopupMenu.add(sort);
                    sortPopupMenu.show(panel, evt.getX(), evt.getY());
                }
            }
        });
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent evt) {
                panel.invalidate();
                panel.validate();
                panel.repaint();
            }
        });
        pcs = new PropertyChangeSupport(this);
    }

    public JPanel getPanel() {
        return panel;
    }

    public void refresh() {
        setSource(getSource());
    }

    public Walkable getSource() {
        return this.walkable;
    }

    public void setSource(Walkable walkable) {
        this.walkable = walkable;
        getViewport().setViewPosition(new Point(0, 0));
        getPanel().removeAll();
        getPanel().revalidate();
        getPanel().repaint();
        
        walkable.getChildren().forEach(System.out::println);
        
        /*
        if (FileHelper.isZIP(walkable)) {
            try {
                ZipFile zipFile = new ZipFile(walkable);
                Enumeration<? extends ZipEntry> entries = zipFile.entries();
                ZipEntry ze;
                ZipEntryThumbnail proxy;
                while (entries.hasMoreElements()) {
                    ze = entries.nextElement();
                    proxy = new ZipEntryThumbnail(ze, zipFile, executorService);
                    proxy.addMouseListener(mouseListener);
                    getVerticalScrollBar().addAdjustmentListener(proxy);
                    getPanel().add(proxy);
                }
                return;
            } catch (Exception e) {
                //TODO
            }          
        }

        FileFilter filter = HiddenFileFilter.VISIBLE;
        Arrays.asList(walkable.listFiles(filter)).parallelStream().forEach(f -> {
            AbstractThumbnail proxy = new FileThumbnail(f, executorService);
            proxy.addMouseListener(mouseListener);
            getVerticalScrollBar().addAdjustmentListener(proxy);
            getPanel().add(proxy);
        });      
        */
    }

    public void setPreviewpane(PreviewPane previewpane) {
        this.previewpane = previewpane;
    }

    public int getThumbSize() {
        return thumbSize;
    }

    public void setThumbSize(int thumbSize) {
        this.thumbSize = thumbSize;
        AbstractThumbnail.setThumbnailHeight(thumbSize);
        AbstractThumbnail.setThumbnailWidth(thumbSize);
        if (getSource() != null) {
            refresh();
        }
    }
}