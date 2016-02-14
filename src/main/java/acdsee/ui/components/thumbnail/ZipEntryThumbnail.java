package acdsee.ui.components.thumbnail;

import static acdsee.ui.components.thumbnail.Thumbnail.getThumbnailWidth;
import java.awt.Graphics2D;
import java.awt.datatransfer.DataFlavor;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.swing.ImageIcon;
import net.coobird.thumbnailator.Thumbnails;

public final class ZipEntryThumbnail extends Thumbnail<ZipEntry> {

    private final ZipFile zipFile;
    private static final ImageIcon icon = new ImageIcon("d:\\image.gif");

    public ZipEntryThumbnail(final ZipEntry zipEntry, final ExecutorService threadPool, final ZipFile zipFile) {
        super(zipEntry, threadPool);
        this.zipFile = zipFile;
    }

    private ZipFile getZipFile() {
        return zipFile;
    }

    @Override
    protected String getSourceFilename() {
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

    public InputStream getInputStream() throws IOException {
        return getZipFile().getInputStream(getSource());
    }

    @Override
    public ZipEntryThumbnail getTransferData(DataFlavor flavor) {
        return this;
    }

    @Override
    public void run() {
        try {
            thumbnailImage = Thumbnails.of(getInputStream()).size(getThumbnailWidth(), getThumbnailWidth()).asBufferedImage();
            imageWidth = thumbnailImage.getWidth(this); //otherwise sorting will not work
            imageHeight = thumbnailImage.getHeight(this);
            repaint();
        } catch (IOException ex) {
            Logger.getLogger(FileThumbnail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
