/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.p1gu.spacelama.Model;

/**
 *
 * @author Michael
 */
public abstract class Ennemi extends Entity{
    public Ennemi(float x, float y, float width, float height) {
        super(x, y, width, height, Type.ennemi);
        
    }
    
    public abstract void attaquer(float delta);
    public abstract void deplacer(float delta);
    
}
