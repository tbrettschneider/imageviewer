package acdsee.ui.imaging;

import java.awt.image.BufferedImage;

public interface IImageCache extends IImageManager {

    public BufferedImage getCurrentImage();

    public void setCurrentImage(BufferedImage img);

    public void resetAutoscaleImage();

    public boolean isCached(Object obj);
}
