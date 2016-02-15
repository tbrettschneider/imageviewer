package com.tommybrettschneider.imageviewer.ui.preview;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.event.IIOReadProgressListener;
import javax.swing.JComponent;

import static com.tommybrettschneider.imageviewer.ui.preview.ImageDisplayMode.*;

public class ImageCache implements IImageCache {

    private final Map<Object, BufferedImage> cache;
    private final IImageManager imageManager;
    private final Map<ImageDisplayMode, BufferedImage> resizedInstances;

    public ImageCache() {
        cache = new HashMap<>();
        resizedInstances = new HashMap<>();
        imageManager = new ImageManager();
    }

    public final IImageManager getImageManager() {
        return imageManager;
    }

    @Override
    public final BufferedImage getImage(final URI uri) {
        return getImage(uri, null);
    }

    @Override
    public final BufferedImage getImage(final URI uri, final IIOReadProgressListener pl) {
        BufferedImage img = cache.get(uri);
        if (img == null) {
            cache.clear();
            resizedInstances.clear();
            try {
                img = imageManager.getImage(uri, pl);
                cache.put(uri, img);
                resizedInstances.put(FULLSIZE, img);
            } catch (IOException e) {
                //TODO
            }
        }
        return img;
    }

    @Override
    public final BufferedImage getImage(final File file) {
        BufferedImage img = cache.get(file);
        if (img == null) {
            cache.clear();
            resizedInstances.clear();
            try {
                img = imageManager.getImage(file);
                cache.put(file, img);
                resizedInstances.put(FULLSIZE, img);
            } catch (IOException e) {
                //TODO
            }
        }
        return img;
    }

    @Override
    public boolean isCached(Object object) {
        return cache.get(object) != null;
    }

    @Override
    public final BufferedImage getCurrentImage() {
        return resizedInstances.get(FULLSIZE);
    }

    @Override
    public final BufferedImage getImage(InputStream inputStream) throws IOException {
        cache.clear();
        resizedInstances.clear();
        setCurrentImage(imageManager.getImage(inputStream));
        return getCurrentImage();
    }

    @Override
    public final BufferedImage proportionalScale(final BufferedImage sourceImage, final ImageDisplayMode imageDisplayMode) {
        BufferedImage img = resizedInstances.get(imageDisplayMode);
        if (img == null && sourceImage != null) {
            img = imageManager.proportionalScale(sourceImage, imageDisplayMode);
            resizedInstances.put(imageDisplayMode, img);
        }
        return img;
    }

    @Override
    public final BufferedImage proportionalScale(final BufferedImage sourceImage, final int width, final int height) {
        return imageManager.proportionalScale(sourceImage, width, height);
    }

    @Override
    public BufferedImage proportionalScale(BufferedImage sourceImage, JComponent component) {
        BufferedImage img = resizedInstances.get(AUTORESIZE);
        if (img == null || isInvalid(img, component)) {
            img = imageManager.proportionalScale(sourceImage, component);
            resizedInstances.put(AUTORESIZE, img);
        }
        return img;
    }

    private boolean isInvalid(final BufferedImage autoresizedImg, final JComponent c) {
        return !((autoresizedImg.getHeight() <= c.getHeight() && autoresizedImg.getWidth() == c.getWidth())
                || (autoresizedImg.getWidth() <= c.getWidth() && autoresizedImg.getHeight() == c.getHeight()));
    }

    @Override
    public void setCurrentImage(final BufferedImage img) {
        resizedInstances.put(FULLSIZE, img);
    }

    @Override
    public void resetAutoscaleImage() {
        resizedInstances.remove(AUTORESIZE);
    }
}