package com.tommybrettschneider.imageviewer.ui.thumbnail;

import java.awt.Graphics2D;
import java.awt.datatransfer.DataFlavor;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.filechooser.FileSystemView;
import org.apache.commons.io.IOUtils;
import sun.awt.shell.ShellFolder;

public class FileThumbnail extends Thumbnail<File> {

    private static final Logger LOGGER = Logger.getLogger(FileThumbnail.class.getName());

    public FileThumbnail(final File file, final ExecutorService threadPool) {
        super(file, threadPool);
    }

    @Override
    public String getSourceFilename() {
        return getSource().getName();
    }
    
    @Override
    public List<File> getTransferData(DataFlavor flavor) {
        final List<File> files = new ArrayList<>();
        files.add(getSource());
        return files;
    }

    @Override
    public void paintProxyImage(Graphics2D g2d) {
        try {
            //final ShellFolder sf = ShellFolder.getShellFolder(getSource());
            //final ImageIcon icon = new ImageIcon(sf.getIcon(true));
            final Icon icon = FileSystemView.getFileSystemView().getSystemIcon(getSource());
            icon.paintIcon(this, g2d, (getThumbnailWidth() - icon.getIconWidth()) / 2, (getThumbnailHeight() - icon.getIconHeight()) / 2);
            //g2d.drawImage(icon.getImage(), (getThumbnailWidth() - icon.getIconWidth()) / 2, (getThumbnailHeight() - icon.getIconHeight()) / 2, this);
        } catch (Exception e) {
            //TODO
        }
    }

    @Override
    public ImageInputStream getImageInputStream() throws IOException {
        return ImageIO.createImageInputStream(getSource());
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return IOUtils.toBufferedInputStream(new FileInputStream(getSource()));
    }

    @Override
    public long getFileSize() {
        return getSource().length();
    }
}