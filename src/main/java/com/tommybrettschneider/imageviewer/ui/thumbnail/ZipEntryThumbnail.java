package com.tommybrettschneider.imageviewer.ui.thumbnail;

import static com.tommybrettschneider.imageviewer.ui.thumbnail.Thumbnail.getThumbnailWidth;
import java.awt.Graphics2D;
import java.awt.datatransfer.DataFlavor;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.swing.ImageIcon;
import org.apache.commons.io.IOUtils;

public final class ZipEntryThumbnail extends Thumbnail<ZipEntry> {

    private static final ImageIcon icon = new ImageIcon("d:\\image.gif");
    private final ZipFile zipFile;

    public ZipEntryThumbnail(final ZipEntry zipEntry, final ExecutorService threadPool, final ZipFile zipFile) {
        super(zipEntry, threadPool);
        this.zipFile = zipFile;
    }

    private ZipFile getZipFile() {
        return zipFile;
    }

    @Override
    public String getSourceFilename() {
        return getSource().getName();
    }
    
    @Override
    public void paintProxyImage(Graphics2D g2d) {
        g2d.drawImage(icon.getImage(), (getThumbnailWidth() - icon.getIconWidth()) / 2, (getThumbnailHeight() - icon.getIconHeight()) / 2, this);
    }

    @Override
    public ImageInputStream getImageInputStream() throws IOException {
        return ImageIO.createImageInputStream(getInputStream());
    }
    
    @Override
    public InputStream getInputStream() throws IOException {
        return IOUtils.toBufferedInputStream(getZipFile().getInputStream(getSource()));
    }

    @Override
    public ZipEntryThumbnail getTransferData(DataFlavor flavor) {
        return this;
    }

    @Override
    public long getFileSize() {
        return getSource().getSize();
    }
}
