/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.p1gu.spacelama.Model.tir;

import ch.p1gu.spacelama.Model.Entity;
import ch.p1gu.spacelama.world.World;

/**
 *
 * @author Michael
 */
public abstract class Tir extends Entity {

    public Tir(float x, float y, float width, float height, Type type) {
        super(x, y, width, height, type);
    }

    @Override
    public void update(float delta) {
        if (rectangle.x > World.x) {
            mort = true;
        }
        if (rectangle.x < -rectangle.width) {
            mort = true;
        }
    }
}
