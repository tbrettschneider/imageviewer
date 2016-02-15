package com.tommybrettschneider.imageviewer.ui.imaging;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

import javax.imageio.event.IIOReadProgressListener;
import javax.swing.JComponent;

public interface IImageManager {

    /**
     * Load image from file.
     *
     * @param file
     * @return
     * @throws IOException
     */
    public BufferedImage getImage(File file) throws IOException;

    /**
     * Load image from url.
     *
     * @param uri
     * @return
     * @throws IOException
     */
    public BufferedImage getImage(URI uri) throws IOException;

    public BufferedImage getImage(URI uri, IIOReadProgressListener pl) throws IOException;

    /**
     * Load image from stream.
     *
     * @param inputStream
     * @return
     * @throws IOException
     */
    public BufferedImage getImage(InputStream inputStream) throws IOException;

    /**
     * Scale source image to component's size - keep aspect ratio of source
     * image.
     *
     * @param sourceImage
     * @param component
     * @return
     */
    public BufferedImage proportionalScale(BufferedImage sourceImage, JComponent component);

    /**
     * Scale source image to given width and height - keep aspect ratio of
     * source image.
     *
     * @param sourceImage
     * @param width
     * @param height
     * @return
     */
    public BufferedImage proportionalScale(BufferedImage sourceImage, int width, int height);

    /**
     * Scale source image according to given scale factor - keep aspect ratio of
     * source image.
     *
     * @param sourceImage
     * @param imageDisplayMode
     * @return
     */
    public BufferedImage proportionalScale(BufferedImage sourceImage, ImageDisplayMode imageDisplayMode);
}
