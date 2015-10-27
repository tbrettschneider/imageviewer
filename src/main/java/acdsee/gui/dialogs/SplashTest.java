/*
 * SplashTest.java
 *
 * Created on 4. Juli 2007, 22:37
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package acdsee.gui.dialogs;

import java.awt.*;
import java.awt.event.*;

public class SplashTest extends Frame implements ActionListener {

    static void renderSplashFrame(Graphics2D g, int frame) {
        final String[] comps = {"foo", "bar", "baz"};
        g.setComposite(AlphaComposite.Clear);
        g.fillRect(130, 250, 280, 40);
        g.setPaintMode();
        g.setColor(Color.BLACK);
        g.drawString("Loading " + comps[(frame / 5) % 3] + "...", 130, 260);
        g.fillRect(130, 270, (frame * 10) % 280, 20);
    }

    public SplashTest() {
        super("SplashScreen demo");
        setSize(500, 300);
        setLayout(new BorderLayout());
        Menu m1 = new Menu("File");
        MenuItem mi1 = new MenuItem("Exit");
        m1.add(mi1);
        mi1.addActionListener(this);

        MenuBar mb = new MenuBar();
        setMenuBar(mb);
        mb.add(m1);
        /*final SplashScreen splash = SplashScreen.getSplashScreen();
         if (splash == null) {
         System.out.println("SplashScreen.getSplashScreen() returned null");
         return;
         }
         Graphics2D g = (Graphics2D)splash.createGraphics();
         if (g == null) {
         System.out.println("g is null");
         return;
         }
         for(int i=0; i<100; i++) {
         renderSplashFrame(g, i);
         splash.update();
         try {
         Thread.sleep(2000);
         }
         catch(InterruptedException e) {
         }
         }
         splash.close();
         setVisible(true);
         toFront();*/
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        System.exit(0);
    }

    public static void main(String args[]) {
        SplashTest test = new SplashTest();
    }
}
