package acdsee.gui.components;

import java.awt.Cursor;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.TransferHandler;

import acdsee.gui.components.thumbnail.ZipEntryThumbnail;

public class PreviewableTransferHandler extends TransferHandler {

    private final PreviewPane previewPane;

    public PreviewableTransferHandler(PreviewPane previewPane) {
        this.previewPane = previewPane;
    }

    private boolean hasFlavor(DataFlavor[] flavors, DataFlavor target) {
        for (DataFlavor flavor : flavors) {
            if (flavor.equals(target)) {
                return true;
            }
        }
        return false;
    }
    
    private boolean hasFileListFlavor(DataFlavor[] flavors) {
        return hasFlavor(flavors, DataFlavor.javaFileListFlavor);
    }

    private boolean hasImageFlavor(DataFlavor[] flavors) {
        return hasFlavor(flavors, DataFlavor.imageFlavor);
    }

    private boolean hasZipEntryFlavor(DataFlavor[] flavors) {
        return hasFlavor(flavors, DataFlavor.stringFlavor);
    }

    public boolean hasAllowedFlavor(DataFlavor[] flavors) {
        return hasFileListFlavor(flavors) || hasImageFlavor(flavors);
    }

    /* (non-Javadoc)
     * @see javax.swing.TransferHandler#canImport(javax.swing.JComponent, java.awt.datatransfer.DataFlavor[])
     */
    @Override
    public boolean canImport(JComponent comp, DataFlavor[] transferFlavors) {
        return hasFileListFlavor(transferFlavors) || hasImageFlavor(transferFlavors);
    }

    /* (non-Javadoc)
     * @see javax.swing.TransferHandler#importData(javax.swing.JComponent, java.awt.datatransfer.Transferable)
     */
    @Override
    public boolean importData(JComponent comp, Transferable t) {
        if (!canImport(comp, t.getTransferDataFlavors())) {
            return false;
        }
        //A real application would load the file in another
        //thread in order to not block the UI.  This step
        //was omitted here to simplify the code.
        if (hasAllowedFlavor(t.getTransferDataFlavors())) {
            previewPane.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            getTransferedObjectAndSetSource(t);
        }
        return true;
    }

    private void getTransferedObjectAndSetSource(final Transferable t) {
        try {
            final URI uri = getURLFromDnD(t);
            System.out.println(uri);
            if (isSupportedImage(uri)) {
                previewPane.setSource(uri);
                System.out.println("Got image from link...");
            } else {
                System.out.println("fehler");
                throw new Exception("test");
            }
        } catch (Exception e) {
            try {
                final BufferedImage img = getImageFromDnD(t);
                previewPane.setSource(img);
                System.out.println("Got image from image...");
            } catch (IOException | UnsupportedFlavorException e3) {
                try {
                    final File file = getFileFromDnD(t);
                    previewPane.setSource(file.toURI());
                    System.out.println("Got image from file...");
                } catch (Exception e2) {
                    ZipEntryThumbnail zipThumb;
                    try {
                        zipThumb = getZipEntryFromDnD(t);
                        previewPane.setSource(zipThumb.getInputStream());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
    }

    protected boolean isSupportedImage(URI uri) {
        boolean isImage = false;
        String stUrl = uri.getPath().toLowerCase();
        for (String format : ImageIO.getReaderFormatNames()) {
            if (stUrl.contains(format.toLowerCase())) {
                isImage = true;
                break;
            }
        }
        return isImage;
    }

    private ZipEntryThumbnail getZipEntryFromDnD(final Transferable t) throws UnsupportedFlavorException, IOException {
        final ZipEntryThumbnail zipEntryThumb = (ZipEntryThumbnail) t.getTransferData(DataFlavor.javaFileListFlavor);
        return zipEntryThumb;
    }

    private File getFileFromDnD(final Transferable t) throws UnsupportedFlavorException, IOException {
        final List files = new ArrayList();
        files.addAll((List) t.getTransferData(DataFlavor.javaFileListFlavor));
        files.addAll((List) t.getTransferData(DataFlavor.stringFlavor));
        System.out.println(files);
        return (File) files.get(0);
    }

    private URI getURLFromDnD(final Transferable t) throws UnsupportedFlavorException, IOException, UnsupportedEncodingException, MalformedURLException, URISyntaxException {
        final InputStream is = (InputStream) t.getTransferData(DataFlavor.getTextPlainUnicodeFlavor());
        final BufferedReader in = new BufferedReader(new InputStreamReader(is, "UTF-16LE"));
        return new URI(in.readLine());
    }

    private BufferedImage getImageFromDnD(final Transferable t) throws IOException, UnsupportedFlavorException {
        return (BufferedImage) t.getTransferData(DataFlavor.imageFlavor);
    }
}