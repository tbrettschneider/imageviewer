package com.tommybrettschneider.imageviewer.util;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileSystemView;
import sun.awt.shell.ShellFolder;

/**
 * 
 * @author Tommy Brettschneider
 */
public class UIUtils {

    private static final Logger LOGGER = Logger.getLogger(UIUtils.class.getName());
    
    /**
     *
     * @param frame
     */
    public static final void toggleFullScreenMode(final JFrame frame) {
        final GraphicsDevice graphicsDevice = GraphicsEnvironment.
                getLocalGraphicsEnvironment().getDefaultScreenDevice();

        if (graphicsDevice.isFullScreenSupported()) {
            SwingUtilities.invokeLater(() -> {
                frame.setVisible(false);
                frame.dispose();
                frame.setUndecorated(isFullScreenMode());
                graphicsDevice.setFullScreenWindow(isFullScreenMode() ? null : frame);
                frame.setResizable(!isFullScreenMode());               
                if (!isFullScreenMode()) {
                    frame.setVisible(true);
                }
            });
        } else {
            LOGGER.info("Full-Screen exclusive mode is not available!");
        }
    }

    public static boolean isFullScreenMode() {
        return GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getFullScreenWindow() != null;
    }

    /**
     * @param file
     * @return
     */
    public static final ImageIcon getLargeFileIcon(final File file) {
        if (file == null) {
            throw new IllegalArgumentException("File is null!");
        }
        try {
            return new ImageIcon(ShellFolder.getShellFolder(file).getIcon(true));
        } catch (IOException e) {
            return null;
        }
    }

    public static final Icon getSmallFileIcon(File file) {
        if (file == null) {
            throw new IllegalArgumentException("File is null!");
        }
        return FileSystemView.getFileSystemView().getSystemIcon(file);
    }

    /**
     * Centers component on screen.
     *
     * @param component
     */
    public static void centerOnScreen(Component component) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension componentSize = component.getPreferredSize();
        component.setLocation(
                (screenSize.width - componentSize.width) / 2,
                (screenSize.height - componentSize.height) / 2);
    }
    
    /**
     * Checks if <code>MouseEvent</code> is a double-click.
     * @param evt the <code>MouseEvent</code>
     * @return true if event represents a double-click, false if not
     */
    public static boolean isDoubleClick(MouseEvent evt) {
        return (SwingUtilities.isLeftMouseButton(evt) && evt.getClickCount() == 2);
    }
    
    /**
     * Checks if the escape key is pressed.
     * @param evt the <code>KeyEvent</code>
     * @return true if escape key is pressed, false if not
     */
    public static boolean isEscapePressed(KeyEvent evt) {
        return (evt.getKeyCode() == KeyEvent.VK_ESCAPE);
    }
    
    public static Dimension getImageSize(ImageInputStream iis) {
        try {
            BufferedImage readImage = ImageIO.read(iis);
            int h = readImage.getHeight();
            int w = readImage.getWidth();
            return new Dimension(w, h);
        } catch (Exception ex) {
            
        }
        return null;
    }
}