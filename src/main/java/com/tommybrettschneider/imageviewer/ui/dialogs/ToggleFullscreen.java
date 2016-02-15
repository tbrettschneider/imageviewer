package com.tommybrettschneider.imageviewer.ui.dialogs;

/**
 * File: ToggleFullscreen.java
 *
 * This file is free to use and modify as it is for educational use. brought to
 * you by Game Programming Snippets (http://gpsnippets.blogspot.com/)
 *
 * Revisions: 1.1 Initial Revision
 *
 */
import java.awt.BorderLayout;
import java.awt.DisplayMode;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * This class was created to show how to setup a fullscreen window and how to
 * change the current screen resolution. Also how you can toggle a window
 * between fullscreen and windowed modes with a keypress.
 */
public class ToggleFullscreen extends JFrame {

    //a reference to the GraphicsDevice for changing resolution and making
    //this window fullscreen.
    private GraphicsDevice device = null;
    //the resolution in which we want to change the monitor to.
    private final DisplayMode dispMode = new DisplayMode(800, 600, 32, 60);
    //the original resolution before our program is run.
    private DisplayMode dispModeOld = null;
    //variable used to toggle between windowed and fullscreen.
    protected boolean fullscreen = false;
    //needed because the JFrame area changes.
    private JPanel drawingArea = null;

    /**
     * The function that first removes the window border and title bar. Then
     * makes the window fullscreen and finally sets the current display mode for
     * the monitor to a custom resolution.
     *
     * @param gd A valid GraphicsDevice to be used for this application.
     */
    public ToggleFullscreen(GraphicsDevice gd) {
        super();

        //get a reference to the device.
        this.device = gd;

        //save the old display mode before changing it.
        dispModeOld = gd.getDisplayMode();

        //set fullscreen mode.
        setFullscreen(false);

        //create an Anonymous JPanel to do rendering
        drawingArea = new JPanel() {
            public void paint(Graphics g) {
                //display toggling instructions.
                g.drawString("Press 'f' key to toggle fullscreen", 10, 20);
            }
        };

        //set the layout to ensure that the component is resized to the full
        //JFrame.
        getContentPane().setLayout(new BorderLayout());

        //add the JPanel to the JFrame
        getContentPane().add(drawingArea);

        //add keylistener so that we may exit.
        this.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    //do all cleanup before closing the program
                    onExit();
                } else if (e.getKeyCode() == KeyEvent.VK_F) {
                    //toggle fullscreen mode.
                    setFullscreen(!isFullscreen());
                }
            }
        });

        //add the window listener to detect when the window is closing.
        addWindowListener(
                new WindowAdapter() {
                    public void windowClosing(WindowEvent e) {
                        //do all cleanup before closing the program
                        onExit();
                    }
                });

        //initially set the window size.
        setSize(800, 600);

        //center the window on screen.
        this.setLocationRelativeTo(null);

        //show the JFrame.
        setVisible(true);
    }

    /**
     * Used as a single function to save off information before exiting and to
     * keep all cleanup code in the same place.
     */
    public void onExit() {

        //immediately hide the window (no falling apart windows)
        setVisible(false);

        //cleanup and destroy the window threads
        dispose();

        //exit the application without an error
        System.exit(0);
    }

    /**
     * Method allows changing whether this window is displayed in fullscreen or
     * windowed mode.
     *
     * @param fullscreen true = change to fullscreen, false = change to windowed
     */
    public void setFullscreen(boolean fullscreen) {
        if (this.fullscreen != fullscreen) { //are we actually changing modes.
            //change modes.
            this.fullscreen = fullscreen;

            // toggle fullscreen mode
            if (!fullscreen) { //change to windowed mode.

                //set the display mode back to the what it was when
                //the program was launched.
                device.setDisplayMode(dispModeOld);

                //hide the frame so we can change it.
                setVisible(false);

                //remove the frame from being displayable.
                dispose();

                //put the borders back on the frame.
                setUndecorated(false);

                //needed to unset this window as the fullscreen window.
                device.setFullScreenWindow(null);

                //make sure the size of the window is correct.
                setSize(800, 600);

                //recenter window
                setLocationRelativeTo(null);

                //reset the display mode to what it was before
                //we changed it.
                setVisible(true);

            } else { //change to fullscreen.
                //hide everything
                setVisible(false);

                //remove the frame from being displayable.
                dispose();

                //remove borders around the frame
                setUndecorated(true);

                //make the window fullscreen.
                device.setFullScreenWindow(ToggleFullscreen.this);

                //attempt to change the screen resolution.
                device.setDisplayMode(dispMode);

                //show the frame
                setVisible(true);

            } // end if

            //make sure that the screen is refreshed.
            repaint();
        }
    }

    /**
     * This method returns true is this frame is in fullscreen. False if in
     * windowed mode.
     *
     * @return true = fullscreen, false = windowed.
     */
    public boolean isFullscreen() {
        return fullscreen;
    } 
}