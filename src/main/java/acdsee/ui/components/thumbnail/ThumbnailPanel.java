package acdsee.ui.components.thumbnail;

import static acdsee.ui.components.thumbnail.ScrollableThumbnailPane.THUMB_MARGIN_BOTTOM;
import static acdsee.ui.components.thumbnail.ScrollableThumbnailPane.THUMB_MARGIN_LEFT;
import static acdsee.ui.components.thumbnail.ScrollableThumbnailPane.THUMB_MARGIN_RIGHT;
import static acdsee.ui.components.thumbnail.ScrollableThumbnailPane.THUMB_MARGIN_TOP;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JPanel;

/**
 *
 * @author Tommy Brettschneider <tommy.brettschneider@gmail.com>
 */
public class ThumbnailPanel extends JPanel {

    private ScrollableThumbnailPane stp;

    public ThumbnailPanel(ScrollableThumbnailPane stp) {
        this.stp = stp;
        setBorder(null);
        setBackground(Color.WHITE);
    }

    public int getThumbSize() {
        return this.stp.getThumbSize();
    }

    @Override
    public java.awt.Dimension getMinimumSize() {
        return new Dimension(getThumbSize() + THUMB_MARGIN_LEFT * THUMB_MARGIN_RIGHT, getThumbSize() + THUMB_MARGIN_TOP + THUMB_MARGIN_BOTTOM);
    }

    @Override
    public java.awt.Dimension getPreferredSize() {
        final java.awt.Insets insets = getInsets();
        final int count = getComponentCount();
        final FlowLayout layout = (FlowLayout)getLayout();
        final int hgap = layout.getHgap();
        final int vgap = layout.getVgap();
        final int cols = getVisibleRect().width / (getThumbSize() + 10);

        if (cols == 0) {
            return getMinimumSize();
        }

        final java.awt.Dimension d = new java.awt.Dimension(
                cols * (getThumbSize() + hgap) + hgap,
                (count / cols) * (getThumbSize() + vgap) + vgap);

        // Dont forget the frame's insets
        d.width += insets.left + insets.right;
        d.height += insets.top + insets.bottom;

        return d;
    }
    
//        @Override
//    public String getToolTipText(MouseEvent evt) {
//        System.out.println(evt.getSource());
//        System.out.println(this.getComponentCount());
//        System.out.println(this.getClass());
//        System.out.println(evt.getPoint());
//        System.out.println(evt.getLocationOnScreen());
//        JComponent component = (JComponent)SwingUtilities.getDeepestComponentAt(this, evt.getX(), evt.getY());
//        Component c2 = getComponentAt(evt.getPoint());
//        System.out.println(c2);
//        if (component != null) {
//            System.out.println(component);
//            return component.getToolTipText();
//        }
//        return null;
//    }
}
