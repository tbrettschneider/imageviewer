package com.tommybrettschneider.imageviewer.util;

import javax.swing.ToolTipManager;

/**
 *
 * @author Tommy Brettschneider <tommy.brettschneider@gmail.com>
 */
public class Tooltips {

    private static final ToolTipManager tooltipMgr = ToolTipManager.sharedInstance();

    private Tooltips() {}
    
    public static Tooltips on() {
        tooltipMgr.setEnabled(true);
        return new Tooltips();
    }
    
    public Tooltips delay(int ms) {
        tooltipMgr.setInitialDelay(ms);
        return this;
    }
    
    public Tooltips dismiss(int ms) {
        tooltipMgr.setDismissDelay(ms);
        return this;
    }
    
    public Tooltips reshow(int ms) {
        tooltipMgr.setReshowDelay(ms);
        return this;
    }
    
    public void off() {
        tooltipMgr.setEnabled(false);
    }
}