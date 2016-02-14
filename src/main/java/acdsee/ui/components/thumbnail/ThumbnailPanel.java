package acdsee.ui.components.thumbnail;

import static acdsee.ui.components.thumbnail.ScrollableThumbnailPane.THUMB_MARGIN_BOTTOM;
import static acdsee.ui.components.thumbnail.ScrollableThumbnailPane.THUMB_MARGIN_LEFT;
import static acdsee.ui.components.thumbnail.ScrollableThumbnailPane.THUMB_MARGIN_RIGHT;
import static acdsee.ui.components.thumbnail.ScrollableThumbnailPane.THUMB_MARGIN_TOP;
import java.awt.Color;
import java.awt.Dimension;
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
        final int hgap = ((java.awt.FlowLayout) getLayout()).getHgap();
        final int vgap = ((java.awt.FlowLayout) getLayout()).getVgap();
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
}
