package com.tommybrettschneider.imageviewer.ui.actions;

import com.tommybrettschneider.imageviewer.ui.thumbnail.FileThumbnail;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 *
 * @author Tommy Brettschneider
 */
public class DeleteAction extends AbstractAction {

    /**
     * Creates a new instance of DeleteAction
     */
    public DeleteAction() {
        super();
    }

    public DeleteAction(String text) {
        super(text, null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = null;
        if (source instanceof FileThumbnail) {
            FileThumbnail thumb = (FileThumbnail) source;
            //thumb.delete();
        }
    }
}
