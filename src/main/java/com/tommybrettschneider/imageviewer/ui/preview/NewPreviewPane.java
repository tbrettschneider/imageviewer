package com.tommybrettschneider.imageviewer.ui.preview;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.event.IIOReadProgressListener;
import javax.imageio.stream.ImageInputStream;
import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 *
 * @author Tommy Brettschneider
 */
public class NewPreviewPane extends JComponent implements IIOReadProgressListener {

    /**
     * Creates a new instance of NewPreviewPane
     */
    public NewPreviewPane() {
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    @Override
    public void sequenceStarted(ImageReader source, int minIndex) {
    }

    @Override
    public void sequenceComplete(ImageReader source) {
    }

    @Override
    public void imageStarted(ImageReader source, int imageIndex) {
    }

    @Override
    public void imageProgress(ImageReader source, float percentageDone) {
    }

    @Override
    public void imageComplete(ImageReader source) {
    }

    @Override
    public void thumbnailStarted(ImageReader source, int imageIndex, int thumbnailIndex) {
    }

    @Override
    public void thumbnailProgress(ImageReader source, float percentageDone) {
    }

    @Override
    public void thumbnailComplete(ImageReader source) {
    }

    @Override
    public void readAborted(ImageReader source) {
    }

    public final BufferedImage loadOriginalImage(File file) throws IOException {
        final BufferedImage img;
        try (ImageInputStream iis = ImageIO.createImageInputStream(file)) {
            final ImageReader reader = (ImageReader) ImageIO.getImageReaders(iis).next();
            reader.setInput(iis);
            reader.addIIOReadProgressListener(this);
            img = reader.read(0);
            reader.dispose();
        }
        return img;
    }

    public static void main(String[] args) throws Exception {
        JFrame f = new JFrame();
        NewPreviewPane p = new NewPreviewPane();
        f.add(p, BorderLayout.CENTER);
        f.setSize(800, 600);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        p.loadOriginalImage(new File("I:\\Pictures\\Sonycam\\DSC01183.jpg"));
    }
}