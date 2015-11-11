package acdsee.gui.components;

import acdsee.base.Directory;
import acdsee.base.Walkable;
import acdsee.base.ZipFile;
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
import java.util.zip.ZipEntry;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import org.apache.commons.io.FileUtils;

public class ThumbnailPane extends JScrollPane {

    private static final Point UPPERLEFTCORNER = new Point(0, 0);

    public static final int THUMB_MARGIN_LEFT = 10;
    public static final int THUMB_MARGIN_RIGHT = 10;
    public static final int THUMB_MARGIN_BOTTOM = 10;
    public static final int THUMB_MARGIN_TOP = 10;

    
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
            thumbnailPane.getVerticalScrollBar().setBlockIncrement(thumbSize + THUMB_MARGIN_BOTTOM);
            thumbnailPane.getVerticalScrollBar().setUnitIncrement(thumbSize + THUMB_MARGIN_BOTTOM);
            thumbnailPane.getViewport().setMinimumSize(new Dimension(thumbSize + THUMB_MARGIN_LEFT + THUMB_MARGIN_RIGHT, thumbSize + THUMB_MARGIN_TOP + THUMB_MARGIN_BOTTOM));
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
        panel = new JPanel() {
            private final Dimension MINSIZE = new Dimension(getThumbSize() + THUMB_MARGIN_LEFT + THUMB_MARGIN_RIGHT, getThumbSize() + THUMB_MARGIN_TOP + THUMB_MARGIN_BOTTOM);

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
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, THUMB_MARGIN_RIGHT, THUMB_MARGIN_BOTTOM));
        sc.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    FileThumbnail at = (FileThumbnail) evt.getSource();
                    setSource(Walkable.getInstance(at.getFile()));
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
                if ((getThumbSize() + THUMB_MARGIN_LEFT + THUMB_MARGIN_RIGHT + getVerticalScrollBar().getWidth()) > getWidth()) {
                    setThumbSize(getWidth() - THUMB_MARGIN_LEFT - THUMB_MARGIN_RIGHT - getVerticalScrollBar().getWidth());
                }
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

    private void refresh() {
        setSource(getSource());
    }

    public Walkable getSource() {
        return this.walkable;
    }

    public void setSource(Walkable walkable) {
        if (walkable!=null) {
            this.walkable = walkable;
            getViewport().setViewPosition(UPPERLEFTCORNER);
            getPanel().removeAll();
            getPanel().revalidate();
            getPanel().repaint();

            if (walkable instanceof ZipFile) {
                walkable.getChildren().forEach(zipEntry -> {
                    AbstractThumbnail t = new ZipEntryThumbnail((ZipEntry)zipEntry, (java.util.zip.ZipFile)walkable.getSource(), executorService);
                    t.addMouseListener(mouseListener);
                    getVerticalScrollBar().addAdjustmentListener(t);
                    getPanel().add(t);
                });
            } else if (walkable instanceof Directory) {
                walkable.getChildren().forEach(file -> {
                    AbstractThumbnail t = new FileThumbnail((File)file, executorService);
                    t.addMouseListener(mouseListener);
                    getVerticalScrollBar().addAdjustmentListener(t);
                    getPanel().add(t);
                });
            }
        }
    }

    public void setPreviewpane(PreviewPane previewpane) {
        this.previewpane = previewpane;
    }

    public int getThumbSize() {
        return this.thumbSize;
    }

    public void setThumbSize(int thumbSize) {
        this.thumbSize = thumbSize;
        AbstractThumbnail.setThumbnailHeight(thumbSize);
        AbstractThumbnail.setThumbnailWidth(thumbSize);
        refresh();
    }
}