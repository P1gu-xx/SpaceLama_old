/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.p1gu.spacelama.Model.tir;

import ch.p1gu.spacelama.Assets;
import ch.p1gu.spacelama.Model.Entity;
import static ch.p1gu.spacelama.Model.Entity.Type.tirEnnemi;
import static ch.p1gu.spacelama.Model.Entity.Type.tirHero;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 *
 * @author Michael
 */
public class TirDroit extends Tir {

    private static final float SPEED = 950f;

    public TirDroit(float x, float y, float width, float height, Type type) {
        super(x, y, width, height, type);

    }

    @Override
    public void update(float delta) {
        super.update(delta);

        switch (type) {
            case tirHero:
                rectangle.x += (SPEED * delta);

            case tirEnnemi:
                rectangle.x -= (SPEED * 0.5 * delta);
                break;
            default:
                throw new AssertionError();
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        switch (type) {
            case tirHero:
                batch.draw(Assets.tirLama, rectangle.x, rectangle.y, rectangle.width, rectangle.height);
                break;
            case tirEnnemi:
               batch.draw(Assets.tir, rectangle.x, rectangle.y, rectangle.width, rectangle.height);
                break;
            default:
                throw new AssertionError();
        }
    }

    @Override
    public void toucher(Entity entity) {
        mort = true;
    }
}
