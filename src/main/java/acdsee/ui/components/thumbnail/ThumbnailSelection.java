package acdsee.ui.components.thumbnail;

import acdsee.ui.util.BaseMouseListener;
import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.Scrollable;
import javax.swing.SwingUtilities;

public class ThumbnailSelection extends JPanel implements Scrollable {

    private static final Logger LOGGER = Logger.getLogger(ThumbnailSelection.class.getName());

    private final static Color COLOR_SELECTED_RECTANGLE = Color.decode("#009ACD");
    private final DrawSelectionListener drawSelectionListener;
    private final Composite ALPHACOMPOSITE = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f);
    private Rectangle selection;
    private final ThumbnailPanel thumbnailPanel;

    public ThumbnailSelection(ThumbnailPanel thumbnailPanel) {
        super(new BorderLayout(0, 0));
        setOpaque(false);
        setAutoscrolls(true);
        this.thumbnailPanel = thumbnailPanel;
        add(this.thumbnailPanel, BorderLayout.CENTER);
        drawSelectionListener = new DrawSelectionListener();
        addMouseListener(drawSelectionListener);
        addMouseMotionListener(drawSelectionListener);
    }

    @Override
    protected void paintChildren(Graphics g) {
        super.paintChildren(g);
        if (drawSelectionListener.getStartPoint() != null && drawSelectionListener.getEndPoint() != null) {
            final int startX = drawSelectionListener.getStartPoint().x;
            final int startY = drawSelectionListener.getStartPoint().y;
            final int endX = drawSelectionListener.getEndPoint().x;
            final int endY = drawSelectionListener.getEndPoint().y;
            final int offsetX = Math.min(startX, endX);
            final int offsetY = Math.min(startY, endY);
            final int width = Math.max(startX, endX) - offsetX;
            final int height = Math.max(startY, endY) - offsetY;
            final Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(COLOR_SELECTED_RECTANGLE);
            final Composite originalComposite = g2d.getComposite();
            g2d.setComposite(ALPHACOMPOSITE);
            g2d.fillRect(offsetX, offsetY, width, height);
            g2d.setComposite(originalComposite);
            g2d.drawRect(offsetX, offsetY, width, height);
        }
    }

    class DrawSelectionListener extends BaseMouseListener {

        private Point startPoint;
        private Point endPoint;

        public Point getEndPoint() {
            return endPoint;
        }

        public void setEndPoint(Point endPoint) {
            this.endPoint = endPoint;
        }

        public Point getStartPoint() {
            return startPoint;
        }

        public void setStartPoint(Point startPoint) {
            this.startPoint = startPoint;
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            endPoint = e.getPoint();
            repaint();
            Rectangle r = new Rectangle(e.getX(), e.getY(), 1, 1);
            thumbnailPanel.scrollRectToVisible(r);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            setStartPoint(e.getPoint());
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            setEndPoint(e.getPoint());
            final int offsetX = Math.min(drawSelectionListener.getStartPoint().x, drawSelectionListener.getEndPoint().x);
            final int offsetY = Math.min(drawSelectionListener.getStartPoint().y, drawSelectionListener.getEndPoint().y);
            final int width = Math.max(drawSelectionListener.getStartPoint().x, drawSelectionListener.getEndPoint().x) - offsetX;
            final int height = Math.max(drawSelectionListener.getStartPoint().y, drawSelectionListener.getEndPoint().y) - offsetY;
            selection = new Rectangle();
            selection.setBounds(offsetX, offsetY, width, height);
            LOGGER.info("selection contains " + getSelectedComponents().size() + " components");
            setStartPoint(null);
            setEndPoint(null);
            repaint();
        }
    }

    public Collection getSelectedComponents() {
        Collection<Component> selectedComponents = new ArrayList<>();
        Component[] comp = ((JPanel)getComponents()[0]).getComponents();
        for (Component component : comp) {
            ((Thumbnail)component).setSelected(false);
            Rectangle r = component.getBounds();
            if (selection != null && SwingUtilities.isRectangleContainingRectangle(selection, r)) {
                selectedComponents.add(component);
                ((Thumbnail)component).setSelected(true);
            }
        }
        return selectedComponents;
    }
    
    @Override
    public Dimension getPreferredScrollableViewportSize() {
        return getPreferredSize();
    }

    @Override
    public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
        return thumbnailPanel.getThumbSize() + ScrollableThumbnailPane.THUMB_MARGIN_TOP;
    }

    @Override
    public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {
        int rowHeight = thumbnailPanel.getThumbSize() + ScrollableThumbnailPane.THUMB_MARGIN_TOP;
        int numberOfVisibleRows = visibleRect.height / rowHeight;
        return numberOfVisibleRows * rowHeight;
    }

    @Override
    public boolean getScrollableTracksViewportWidth() {
        return Boolean.TRUE;       
    }

    @Override
    public boolean getScrollableTracksViewportHeight() {
        return Boolean.FALSE;
    }
}