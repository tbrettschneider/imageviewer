package com.tommybrettschneider.imageviewer.ui.util;

import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.image.BufferedImage;

/**
 *
 * @author Tommy Brettschneider
 */
public class ImageUtil {

    /**
     * Convenience method that returns a scaled instance of the provided
     * {@code BufferedImage}.
     *
     * @param img the original image to be scaled
     * @param targetWidth the desired width of the scaled instance, in pixels
     * @param targetHeight the desired height of the scaled instance, in pixels
     * @param hint one of the rendering hints that corresponds to
     * {@code RenderingHints.KEY_INTERPOLATION} (e.g.
     * {@code RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR},
     * {@code RenderingHints.VALUE_INTERPOLATION_BILINEAR},
     * {@code RenderingHints.VALUE_INTERPOLATION_BICUBIC})
     * @param higherQuality if true, this method will use a multi-step scaling
     * technique that provides higher quality than the usual one-step technique
     * (only useful in downscaling cases, where {@code targetWidth} or
     * {@code targetHeight} is smaller than the original dimensions, and
     * generally only when the {@code BILINEAR} hint is specified)
     * @return a scaled version of the original {@code BufferedImage}
     */
    public BufferedImage getScaledInstance(BufferedImage img,
            int targetWidth,
            int targetHeight,
            Object hint,
            boolean higherQuality) {
//        if (thumbnailRatio < imageRatio) {
//                targetHeight = (int) (thumbnailWidth / imageRatio);
//            } else {
//                targetWidth = (int) (thumbnailHeight * imageRatio);
//            }

        int type = (img.getTransparency() == Transparency.OPAQUE)
                ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
        BufferedImage ret = (BufferedImage) img;
        int w, h;
        if (higherQuality) {
            // Use multi-step technique: start with original size, then
            // scale down in multiple passes with drawImage()
            // until the target size is reached
            w = img.getWidth();
            h = img.getHeight();
        } else {
            // Use one-step technique: scale directly from original
            // size to target size with a single drawImage() call
            w = targetWidth;
            h = targetHeight;
        }

        do {
            if (higherQuality && w > targetWidth) {
                w /= 2;
                if (w < targetWidth) {
                    w = targetWidth;
                }
            }

            if (higherQuality && h > targetHeight) {
                h /= 2;
                if (h < targetHeight) {
                    h = targetHeight;
                }
            }

            BufferedImage tmp = new BufferedImage(w, h, type);
            Graphics2D graphics2D = tmp.createGraphics();
            graphics2D.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_SPEED);
            graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
            graphics2D.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
            graphics2D.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_DISABLE);
            graphics2D.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_OFF);
            graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
            graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, hint);
            graphics2D.drawImage(ret, 0, 0, w, h, null);
            graphics2D.dispose();

            ret = tmp;
        } while (w != targetWidth || h != targetHeight);

        return ret;
    }

    /**
     * This one boosts performance twice... !!!!
     *
     * @param image
     * @param gc
     * @return
     */
    public static final BufferedImage toCompatibleImage(final BufferedImage image, final GraphicsConfiguration gc) {
        final BufferedImage result = gc.createCompatibleImage(image.getWidth(), image.getHeight(), image.getColorModel().getTransparency());
        final Graphics2D g2 = result.createGraphics();
        g2.drawRenderedImage(image, null);
        g2.dispose();
        return result;
    }
}
