/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acdsee.base;

import java.util.List;

/**
 *
 * @author tommy
 * @param <Source>
 * @param <ChildType>
 */
public abstract class Walkable<Source, ChildType> {
    
    private Source source;
    
    public abstract List<ChildType> getChildren();
    
    public Source getSource() {
        return this.source;
    }
}
