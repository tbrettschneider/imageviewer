package acdsee.ui.imaging;

import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.event.IIOReadProgressListener;
import javax.imageio.stream.ImageInputStream;
import javax.swing.JComponent;
import acdsee.io.http.ProxyConnection;
import acdsee.io.http.ProxySettings;

public class ImageManager implements IImageManager {

    private static final GraphicsConfiguration GRAPHICS_CONFIGURATION;

    static {
        GRAPHICS_CONFIGURATION = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
    }

    public ImageManager() {
    }

    @Override
    public final BufferedImage getImage(final File file) throws IOException {
        return getImage(file.toURI());
    }

    @Override
    public final BufferedImage getImage(final URI uri) throws IOException {
        ProxySettings proxySettings = ProxySettings.getInstance();
        proxySettings.setProxyUsername("username");
        proxySettings.setProxyPassword("password");
        proxySettings.setProxyAuthenticationEnabled(true);
        return getImage(new ProxyConnection(uri.toURL().openConnection(), proxySettings).getInputStream(), null);
    }

    public final BufferedImage getImage(final InputStream inputStream, final IIOReadProgressListener progressListener) throws IOException {
        final BufferedImage img;
        try ( //return ImageIO.read(inputStream);
                ImageInputStream iis = ImageIO.createImageInputStream(inputStream)) {
            final ImageReader reader = ImageIO.getImageReaders(iis).next();
            if (progressListener != null) {
                reader.addIIOReadProgressListener(progressListener);
            }
            reader.setInput(iis, false, true);
            img = toCompatibleImage(reader.read(0));
            reader.dispose();
        }
        return img;
    }

    @Override
    public final BufferedImage proportionalScale(final BufferedImage sourceImage, final int width, final int height) {
        final int srcImgWidth = sourceImage.getWidth();
        final int srcImgHeight = sourceImage.getHeight();
        final float ar_src = (float) srcImgWidth / (float) srcImgHeight;
        final float ar = (float) width / (float) height;

        if ((srcImgWidth <= width) && (srcImgHeight <= height)) {
            return sourceImage;
        } else {
            int v_width = width, v_height = height;

            if (ar < ar_src) {
                v_height = (int) (width / ar_src);
            } else {
                v_width = (int) (height * ar_src);
            }

            final BufferedImage thumbnail = GRAPHICS_CONFIGURATION.createCompatibleImage(
                    v_width, v_height, sourceImage.getTransparency());

            final Graphics2D g2d = thumbnail.createGraphics();

            g2d.drawImage(
                    sourceImage.getScaledInstance(v_width, v_height, Image.SCALE_SMOOTH),
                    0, 0, v_width, v_height, null);

            g2d.dispose();
            return thumbnail;
        }
    }

    private static BufferedImage toCompatibleImage(final BufferedImage a_sourceImage) {
        final BufferedImage compatibleImage = GRAPHICS_CONFIGURATION.createCompatibleImage(
                a_sourceImage.getWidth(), a_sourceImage.getHeight(), a_sourceImage.getTransparency());
        final Graphics2D g2d = compatibleImage.createGraphics();
        g2d.drawImage(a_sourceImage, 0, 0, null);
        g2d.dispose();
        return compatibleImage;
    }

    @Override
    public final BufferedImage proportionalScale(final BufferedImage sourceImage, final JComponent component) {
        int width = component.getWidth() - component.getInsets().left - component.getInsets().right;
        int height = component.getHeight() - component.getInsets().top - component.getInsets().bottom;
        return proportionalScale(sourceImage, width, height);
    }

    @Override
    public final BufferedImage proportionalScale(final BufferedImage sourceImage, final ImageDisplayMode imageDisplayMode) {
        float scale = imageDisplayMode.getScale();
        final int width = (int) (scale * sourceImage.getWidth(null));
        final int height = (int) (scale * sourceImage.getHeight(null));
        return proportionalScale(sourceImage, width, height);
    }

    @Override
    public BufferedImage getImage(InputStream inputStream) throws IOException {
        return getImage(inputStream, null);
    }

    @Override
    public BufferedImage getImage(URI uri, IIOReadProgressListener pl) throws IOException {
        return getImage(uri.toURL().openStream(), pl);
    }
}