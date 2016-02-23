package com.tommybrettschneider.imageviewer.ui.thumbnail;

import static com.tommybrettschneider.imageviewer.ui.thumbnail.ScrollableThumbnailPane.THUMB_MARGIN;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JPanel;

/**
 *
 * @author Tommy Brettschneider <tommy.brettschneider@gmail.com>
 */
public class ThumbnailPanel extends JPanel {

    private final ScrollableThumbnailPane stp;

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
        return new Dimension(getThumbSize() + THUMB_MARGIN * THUMB_MARGIN, getThumbSize() + THUMB_MARGIN + THUMB_MARGIN);
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
}
