/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.p1gu.spacelama.Model.bonus;

import ch.p1gu.spacelama.Assets;
import ch.p1gu.spacelama.Model.Entity;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 *
 * @author da Silva Michael
 */
public class Piece extends Entity{

    public Piece(float x, float y) {
        super(x, y, 25, 25, Entity.Type.bonus);
    }

    @Override
    public void draw(SpriteBatch batch) {
         batch.draw(Assets.bouclier, rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }

    @Override
    public void update(float delta) {
        super.update(delta); 
        double sin = Math.sin(System.currentTimeMillis());
        float y=(float) sin*100;
        rectangle.x-=(float) (100*delta);
        rectangle.y+=y*delta;
    }
    
    

    @Override
    public void toucher(Entity entity) {
        
    }
    
}
