package com.tommybrettschneider.imageviewer.ui.actions;

import com.tommybrettschneider.imageviewer.util.UIUtils;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JFrame;

/**
 *
 * @author Tommy Brettschneider
 */
public class FullscreenAction extends AbstractAction {

    private final JFrame frame;

    /**
     * Creates a new instance of FullscreenAction
     * @param text
     * @param frame
     */
    public FullscreenAction(String text, JFrame frame) {
        super(text);
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        if (gd.isFullScreenSupported()) {
            UIUtils.toggleFullScreenMode(frame);
        }
    }
}