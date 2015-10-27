package acdsee.gui.components.thumbnail;

import static acdsee.gui.components.thumbnail.AbstractThumbnail.getThumbnailWidth;
import java.awt.Graphics2D;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
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

public final class ZipEntryThumbnail extends AbstractThumbnail {

    private final ZipEntry zipEntry;
    private final ZipFile zipFile;
    private static final ImageIcon icon = new ImageIcon("d:\\image.gif");

    public ZipEntryThumbnail(final ZipEntry zipEntry, final ZipFile zipFile, final ExecutorService threadPool) {
        super(threadPool);
        this.zipEntry = zipEntry;
        this.zipFile = zipFile;
    }

    public ZipEntry getZipEntry() {
        return zipEntry;
    }

    public ZipFile getZipFile() {
        return zipFile;
    }

    @Override
    public void paintProxyImage(Graphics2D g2d) {
        g2d.drawImage(icon.getImage(), (thumbnailWidth - icon.getIconWidth()) / 2, (thumbnailHeight - icon.getIconHeight()) / 2, this);
    }

    @Override
    public ImageInputStream getImageInputStream() throws IOException {
        return ImageIO.createImageInputStream(getInputStream());
    }

    public InputStream getInputStream() throws IOException {
        return zipFile.getInputStream(zipEntry);
    }

    @Override
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
        return this;
    }

    @Override
    public String toString() {
        return "<HTML>" + getZipEntry().getName() /*+ "<br/>" + getImageWidth() + " x " + getImageHeight() */ + "<br/></HTML>";
    }

    @Override
    public void run() {
        try {
            img = Thumbnails.of(getInputStream()).size(getThumbnailWidth(), getThumbnailWidth()).asBufferedImage();
            imageWidth = img.getWidth(this); //otherwise sorting will not work
            imageHeight = img.getHeight(this);
            repaint();
        } catch (IOException ex) {
            Logger.getLogger(FileThumbnail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
