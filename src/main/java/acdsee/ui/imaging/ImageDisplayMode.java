package acdsee.ui.imaging;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author Tommy Brettschneider
 */
public enum ImageDisplayMode {
    
    AUTORESIZE("Autom. Größe", -1.0f),
    FULLSIZE("Vollbildgröße", 1.0f),
    HALFSIZE("1/2 Größe", 0.5f),
    QUARTERSIZE("1/4 Größe", 0.25f),
    EIGHTHSIZE("1/8 Größe", 0.125f);

    ImageDisplayMode(String label, float scale) {
        this.label = label;
        this.scale = scale;
    }
    
    public String getLabel() {
        return this.label;
    }
    
    public float getScale() {
        return this.scale;
    }
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
    
    private final float scale;
    private final String label;
}