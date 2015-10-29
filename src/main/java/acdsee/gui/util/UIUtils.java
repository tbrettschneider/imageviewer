package acdsee.gui.util;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.Window;
import java.io.File;
import java.io.IOException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileSystemView;

import sun.awt.shell.ShellFolder;

/**
 * @author A310335
 *
 */
public class UIUtils {

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
            System.out.println("Sorry! Full-Screen Exclusive Mode is not available...");
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
     * Centers window on screen.
     *
     * @param window
     */
    public static void centerWindow(Window window) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension windowSize = window.getSize();
        window.setLocation(
                (screenSize.width - windowSize.width) / 2,
                (screenSize.height - windowSize.height) / 2);
    }

    /**
     * Centers component on screen.
     *
     * @param component
     */
    public static void centerComponent(Component component) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension componentSize = component.getPreferredSize();
        component.setLocation(
                (screenSize.width - componentSize.width) / 2,
                (screenSize.height - componentSize.height) / 2);
    }
}
