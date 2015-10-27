/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acdsee.gui.components;

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
    
    private final float scale;
    private final String label;
}