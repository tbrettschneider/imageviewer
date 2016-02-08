package acdsee.ui.components.thumbnail;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Composite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import acdsee.ui.util.BaseMouseListener;

public class ThumbnailSelection extends JPanel {

    private final DrawSelectionListener drawSelectionListener;
    private final Composite ALPHACOMPOSITE = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f);
    private Rectangle selection;
    private final JComponent c;

    public ThumbnailSelection(JComponent c) {
        super(new BorderLayout(0, 0));
        setOpaque(false);
        setAutoscrolls(true);
        this.c = c;
        add(c, BorderLayout.CENTER);
        drawSelectionListener = new DrawSelectionListener();
        addMouseListener(drawSelectionListener);
        addMouseMotionListener(drawSelectionListener);
    }

    @Override
    protected void paintChildren(Graphics g) {
        super.paintChildren(g);
        if (drawSelectionListener.getStartPoint() != null && drawSelectionListener.getEndPoint() != null) {
            final int offsetX = Math.min(drawSelectionListener.getStartPoint().x, drawSelectionListener.getEndPoint().x);
            final int offsetY = Math.min(drawSelectionListener.getStartPoint().y, drawSelectionListener.getEndPoint().y);
            final int width = Math.max(drawSelectionListener.getStartPoint().x, drawSelectionListener.getEndPoint().x) - offsetX;
            final int height = Math.max(drawSelectionListener.getStartPoint().y, drawSelectionListener.getEndPoint().y) - offsetY;
            final Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(Color.BLUE);
            final Composite originalComposite = g2d.getComposite();
            g2d.setComposite(ALPHACOMPOSITE);
            g2d.fillRoundRect(offsetX, offsetY, width, height, 0, 0);
            g2d.setComposite(originalComposite);
            g2d.drawRoundRect(offsetX, offsetY, width, height, 0, 0);
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
            c.scrollRectToVisible(r);
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
            System.out.println(getSelectedComponents().size() + " selected components...");
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
}