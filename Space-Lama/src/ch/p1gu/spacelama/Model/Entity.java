/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.p1gu.spacelama.Model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author Michael
 */
public abstract class Entity {

    public enum Type {
        hero, ennemi, tirHero, tirEnnemi, bonus
    };
    public int vie,maxVie;
    public Type type;
    public Rectangle rectangle;
    public Vector2 vitesse;
    public boolean mort;

    public Entity(float x, float y, float width, float height, Type type) {
        rectangle = new Rectangle(x, y, width, height);
        this.type = type;
        vitesse=new Vector2();
        mort=false;
    }


    
    public void update(float delta){
        if(rectangle.x<0-rectangle.width){
        mort=true;
        }
    }

    public abstract void draw(SpriteBatch batch);
    

    public abstract void toucher(Entity entity);

    public void setX(float x) {
        rectangle.x = x;
    }

    public float getX() {
        return rectangle.getX();
    }

    public void setY(float y) {
        rectangle.y = y;
    }

    public float getY() {
        return rectangle.getY();
    }

    public void setPosition(float x, float y) {
        rectangle.y = y;
        rectangle.x = x;
    }
}
