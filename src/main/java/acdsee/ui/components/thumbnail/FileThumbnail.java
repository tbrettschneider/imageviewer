package acdsee.ui.components.thumbnail;

import acdsee.io.util.FileHelper;
import java.awt.Graphics2D;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.swing.ImageIcon;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.tasks.UnsupportedFormatException;
import org.apache.commons.io.IOUtils;

import sun.awt.shell.ShellFolder;

public class FileThumbnail extends Thumbnail {

    private static final Logger LOGGER = Logger.getLogger(FileThumbnail.class.getName());

    private File file;
    private BufferedImage previewZipImage;

    public FileThumbnail(final File file, final ExecutorService threadPool) {
        super(threadPool);
        this.file = file;
    }

    public FileThumbnail() {
        this(null, null);
    }

    public File getFile() {
        return file;
    }

    @Override
    public List<File> getTransferData(DataFlavor flavor) {
        final List<File> files = new ArrayList<>();
        files.add(getFile());
        return files;
    }

    @Override
    public void paintProxyImage(Graphics2D g2d) {
        try {
            if (FileHelper.isZIP(this.file)) {
                ImageIcon icon = new ImageIcon(getThumbOfFirstZipEntry());
                g2d.drawImage(icon.getImage(), (getThumbnailWidth() - icon.getIconWidth()) / 2, (getThumbnailHeight() - icon.getIconHeight()) / 2, this);
                return;
            }
            final ShellFolder sf = ShellFolder.getShellFolder(file);
            final ImageIcon icon = new ImageIcon(sf.getIcon(true));
            g2d.drawImage(icon.getImage(), (getThumbnailWidth() - icon.getIconWidth()) / 2, (getThumbnailHeight() - icon.getIconHeight()) / 2, this);
        } catch (Exception e) {
            //TODO
        }
    }

    @Override
    public ImageInputStream getImageInputStream() throws IOException {
        return ImageIO.createImageInputStream(this.file);
    }

    private BufferedImage getThumbOfFirstZipEntry() {
        if (previewZipImage == null) {
            ZipFile zip = null;
            try {
                zip = new ZipFile(file);
            } catch (ZipException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            if (zip != null) {
                ZipEntry entry = zip.entries().nextElement();
                try {
                    previewZipImage = loadOriginalImageOfThumbnail(4, ImageIO.createImageInputStream(zip.getInputStream(entry)));
                    previewZipImage = getThumbnailHQ(previewZipImage);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return previewZipImage;
    }

    @Override
    public String toString() {
        return "<HTML>" + getFile().getName() /*+ "<br/>" + getImageWidth() + " x " + getImageHeight() */ + "<br/></HTML>";
    }

    @Override
    public void run() {
        try {
            thumbnailImage = Thumbnails.of(getFile()).size(getThumbnailWidth(), getThumbnailWidth()).asBufferedImage();
            imageWidth = thumbnailImage.getWidth(this); //otherwise sorting will not work
            imageHeight = thumbnailImage.getHeight(this);
            repaint();
        } catch (UnsupportedFormatException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        } 
    }
}