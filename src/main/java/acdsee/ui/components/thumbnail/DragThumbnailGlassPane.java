package acdsee.ui.components.thumbnail;

import acdsee.ui.components.thumbnail.Thumbnail;
import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DragSourceDropEvent;
import java.awt.dnd.DragSourceEvent;
import java.awt.dnd.DragSourceListener;
import java.awt.dnd.DragSourceMotionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

/**
 * 
 * @author Tommy Brettschneider
 */
public class DragThumbnailGlassPane extends JPanel implements DragSourceMotionListener, DragSourceListener {

    private Timer timer;
    private Image img;
    private int centerImgX;
    private int centerImgY;

    private Rectangle oldRect;
    private Rectangle newRect;
    private Rectangle unionRect;
    private static float f = 0.7f;
    private static Composite c = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, f);

    public DragThumbnailGlassPane() {
        super();
        setOpaque(false);
        timer = new Timer(1, (ActionEvent evt) -> {
            if (f < 0.05f) {
                img = null;
                setVisible(false);
                oldRect = newRect = unionRect = null;
                timer.stop();
                f = 0.7f;
                c = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, f);
                return;
            }
            
            if (unionRect != null) {
                f -= 0.05f;
                c = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, f);
                repaint(unionRect);
            }
        });
    }

    @Override
    public void dragMouseMoved(DragSourceDragEvent dsde) {
        if (img == null) {
            Thumbnail proxy = (Thumbnail) dsde.getDragSourceContext().getTransferable();
            img = proxy.getThumbnailImage();
        }

        if (img != null) {

            if (!isVisible()) {
                setVisible(true);
            }

            final int imgWidth = img.getWidth(null);
            final int imgHeight = img.getHeight(null);

            centerImgX = dsde.getX() - (imgWidth / 2);
            centerImgY = dsde.getY() - (imgHeight / 2) - 32;

            if (oldRect == null) {
                oldRect = new Rectangle(centerImgX, centerImgY, imgWidth, imgHeight);
            }

            newRect = new Rectangle(centerImgX, centerImgY, imgWidth, imgHeight);

            unionRect = SwingUtilities.computeUnion(oldRect.x, oldRect.y, oldRect.width, oldRect.height, newRect);

            repaint(unionRect);

            oldRect = newRect;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        final Graphics2D g2 = (Graphics2D) g;
        if (img != null) {
            g2.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_SPEED);
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
            g2.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
            g2.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_DISABLE);
            g2.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_OFF);
            g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
            g2.setComposite(c);
            g2.drawImage(img, centerImgX, centerImgY, this);
        }
        g2.dispose();
    }

    @Override
    public void dragEnter(DragSourceDragEvent dsde) {
    }

    @Override
    public void dragOver(DragSourceDragEvent dsde) {
    }

    @Override
    public void dropActionChanged(DragSourceDragEvent dsde) {
    }

    @Override
    public void dragExit(DragSourceEvent dse) {
    }

    @Override
    public void dragDropEnd(DragSourceDropEvent dsde) {
        timer.start();
    }
}