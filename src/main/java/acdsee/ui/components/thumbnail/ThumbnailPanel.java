/*
 * The MIT License
 *
 * Copyright 2016 Tommy Brettschneider <tommy.brettschneider@gmail.com>.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
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
