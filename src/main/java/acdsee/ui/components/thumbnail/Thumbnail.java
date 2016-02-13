package acdsee.ui.components.thumbnail;

import acdsee.ui.dialogs.ApplicationWindow;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragGestureRecognizer;
import java.awt.dnd.DragSource;
import java.awt.dnd.DragSourceListener;
import java.awt.dnd.DragSourceMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.JPanel;
/**
 *
 * @author Tommy Brettschneider
 */
public abstract class Thumbnail<E> extends JPanel implements Runnable, Transferable {

    protected final static Composite ALPHACOMPOSITE = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f);
    public final static GraphicsConfiguration graphicsConfiguration = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
    protected static final Color BORDER_COLOR = new Color(236, 233, 216);
    protected static Dimension dimension = new Dimension(135, 135);
    protected int subsampling = 1;
    protected int imageWidth, imageHeight;
    protected float imageRatio;
    protected BufferedImage thumbnailImage;
    protected ExecutorService threadpool;
    protected boolean selected;
    private boolean executed;
    private E source;
    
    /**
     * Gets the <code>Dimension</code> of each and every thumbnail.
     * @return   the dimension of the thumbnails, an instance of <code>Dimension</code>
     */
    public final static Dimension getDimension() {
        return dimension;
    }

    /**
     * Get the aspect ratio of a <code>Thumbnail</code>.
     * @return the aspect ratio of this <code>Thumbnail</code>
     */
    public final static double getAspectRatio() {
        return getDimension().getHeight() / getDimension().getHeight();
    }

    
    public static int getMaxPixelsThumbnail() {
        return getThumbnailWidth() * getThumbnailHeight() * 32;
    }

    public Thumbnail(final E source, final ExecutorService threadPool) {
        this.source = source;
        this.threadpool = threadPool;
        setOpaque(true);
        setBackground(Color.WHITE);
        MyDragGestureListener mdgl = new MyDragGestureListener(this);
        DragSource dragSource = new DragSource();
        DragGestureRecognizer dgr = dragSource.createDefaultDragGestureRecognizer(
                this, DnDConstants.ACTION_COPY, mdgl);
        dragSource.addDragSourceMotionListener((DragSourceMotionListener) ApplicationWindow.GLASSPANE);
        dragSource.addDragSourceListener((DragSourceListener) ApplicationWindow.GLASSPANE);
    }

    @Override
    protected final void paintComponent(final Graphics g) {
        if (!executed) {
            threadpool.execute(this);     
            executed = true;
        }

        super.paintComponent(g);

        final Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_SPEED);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
        g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_DISABLE);
        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_OFF);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);

        if (thumbnailImage != null) {
            g2d.drawImage(thumbnailImage, (getThumbnailWidth() - thumbnailImage.getWidth()) / 2, (getThumbnailHeight() - thumbnailImage.getHeight()) / 2, this);
        } else {
            paintProxyImage(g2d);
        }
        g2d.setColor(BORDER_COLOR);
        g2d.drawRect(0, 0, getThumbnailWidth()-1, getThumbnailHeight()-1); 
                
        if (isSelected()) {
            g2d.setColor(Color.BLUE);
            g2d.setComposite(ALPHACOMPOSITE);
            g2d.fillRect(0, 0, getThumbnailWidth()-1, getThumbnailHeight()-1);
        }

        g2d.dispose();
    }

    private BufferedImage loadOriginalImageOfThumbnail(final int subsampling) {
        try {
            return loadOriginalImageOfThumbnail(subsampling, getImageInputStream());
        } catch (IOException e) {
            return null;
        }
    }

    protected final BufferedImage loadOriginalImageOfThumbnail(final int subsampling, final ImageInputStream iis) {
        try {
            if (iis == null) {
                return null;
            }
            final ImageReader reader = ImageIO.getImageReaders(iis).next();
            reader.setInput(iis);
            this.imageWidth = reader.getWidth(0);
            this.imageHeight = reader.getHeight(0);
            this.imageRatio = reader.getAspectRatio(0);
            final ImageReadParam readParam = reader.getDefaultReadParam();
            readParam.setSourceSubsampling(subsampling, subsampling, 0, 0);
            final BufferedImage bufImg = reader.read(0, readParam);
            // setImageDepth(bufImg.getColorModel().getPixelSize());
            reader.dispose();
            iis.close();
            return bufImg;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public final Dimension getPreferredSize() {
        return getDimension();
    }

    /**
     * Custom painting code to draw a proxy image during imageloading comes in here.
     * @param g the <code>Graphics2D</code> instance to draw onto
     */
    public abstract void paintProxyImage(final Graphics2D g);

    /**
     * Custom imageloading code comes in here.
     * @return an <code>ImageInputStream</code> to access the target image 
     * @throws IOException 
     */
    public abstract ImageInputStream getImageInputStream()
            throws IOException;

    /**
     * @return 
     */
    public final int getSubsampling() {
        if (imageWidth > getDimension().getWidth() && imageHeight > getDimension().getHeight()) {
            subsampling = ((imageWidth * imageHeight) / getMaxPixelsThumbnail()) + 2;
        }
        return subsampling;
    }

    protected final BufferedImage getThumbnail(final BufferedImage a_image) {
        final BufferedImage thumbImg;

        if (imageWidth <= getThumbnailWidth() && imageHeight <= getThumbnailHeight()) {
            thumbImg = a_image;
        } else {
            int w = getThumbnailWidth(), h = getThumbnailHeight();

            if (getAspectRatio() < imageRatio) {
                h = (int)(getThumbnailWidth() / imageRatio);
            } else {
                w = (int)(getThumbnailHeight() * imageRatio);
            }

            thumbImg = graphicsConfiguration.createCompatibleImage(w, h);

            final Graphics2D graphics2D = thumbImg.createGraphics();
            graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
            graphics2D.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_SPEED);
            graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
            graphics2D.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
            graphics2D.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_DISABLE);
            graphics2D.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_OFF);
            graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
            graphics2D.drawImage(a_image, 0, 0, w, h, this);
            graphics2D.dispose();
        }
        return thumbImg;
    }

    protected final BufferedImage getThumbnailHQ(final BufferedImage a_image) {
        final BufferedImage thumbImg;

        if (imageWidth <= getDimension().getWidth() && imageHeight <= getThumbnailHeight()) {
            thumbImg = a_image;
        } else {
            int w = getThumbnailWidth(), h = getThumbnailHeight();

            if (getAspectRatio() < imageRatio) {
                h = (int)(getThumbnailWidth() / imageRatio);
            } else {
                w = (int)(getThumbnailHeight() * imageRatio);
            }

            thumbImg = graphicsConfiguration.createCompatibleImage(w, h);

            final Graphics2D graphics2D = thumbImg.createGraphics();
            graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
            graphics2D.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_SPEED);
            graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
            graphics2D.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
            graphics2D.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_DISABLE);
            graphics2D.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_OFF);
            graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);

            graphics2D.drawImage(
                    a_image.getScaledInstance(w, h, Image.SCALE_SMOOTH),
                    //                getScaledInstance(a_image, w, h, RenderingHints.VALUE_INTERPOLATION_BICUBIC, true),
                    0, 0, w, h, this);

            graphics2D.dispose();
        }
        return thumbImg;
    }

    @Override
    public void run() {
        try {
            thumbnailImage = getThumbnail(loadOriginalImageOfThumbnail(6));
            repaint(10);
            threadpool.execute(() -> {
                thumbnailImage = getThumbnailHQ(loadOriginalImageOfThumbnail(3));
                repaint();                
            });
        } catch (Exception e) {
            //TODO: Exception handling!
        }
    }

    final static class MyDragGestureListener implements DragGestureListener {

        private final Transferable proxy;

        public MyDragGestureListener(Transferable proxy) {
            this.proxy = proxy;
        }

        @Override
        public void dragGestureRecognized(DragGestureEvent e) {
            e.startDrag(null, proxy);
        }
    }

    /**
     * Gets the thumbnail image.
     * @return the already resized <code>Image</code> of this <code>Thumbnail</code>
     */
    public final Image getThumbnailImage() {
        return this.thumbnailImage;
    }

    public final int getImageWidth() {
        return imageWidth;
    }

    public final int getImageHeight() {
        return imageHeight;
    }

    @Override
    public DataFlavor[] getTransferDataFlavors() {
        return new DataFlavor[]{DataFlavor.javaFileListFlavor};
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return flavor.equals(DataFlavor.javaFileListFlavor);
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isSelected() {
        return this.selected;
    }

    protected static int getThumbnailHeight() {
        return (int)getDimension().getHeight();
    }

    protected static int getThumbnailWidth() {
        return (int)getDimension().getWidth();
    }
    
    public E getSource() {
        return this.source;
    }
}