/**
 *
 */
package acdsee.gui.util;

import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputAdapter;

/**
 * @author Tommy Brettschneider
 *
 */
public class BaseMouseListener extends MouseInputAdapter {

    @Override
    public void mouseClicked(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e) && e.getClickCount() == 2) {
            mouseDoubleClicked(e);
        } else if (SwingUtilities.isRightMouseButton(e)) {
            rightMouseButtonClicked(e);
        }
    }

    protected void mouseDoubleClicked(MouseEvent event) {
    }

    protected void rightMouseButtonClicked(MouseEvent event) {
    }
}