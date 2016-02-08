package acdsee.ui.components.previewer;

import acdsee.ui.imaging.ImageDisplayMode;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.net.URI;

/**
 * @author Tommy Brettschneider
 */
public interface IPreviewPane {
    
    public void setSource(InputStream is) throws Exception;

    public void setSource(File file) throws Exception;

    public void setSource(URI uri) throws Exception;

    public void setSource(BufferedImage img) throws Exception;

    public void setDisplayMode(ImageDisplayMode displayMode);

    public ImageDisplayMode getDisplayMode();

    public void setDnDEnabled(boolean enableDnD);

    public boolean isDnDEnabled();
}
