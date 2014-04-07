/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.p1gu.spacelama.Model;

import ch.p1gu.spacelama.Assets;
import ch.p1gu.spacelama.Model.bonus.Piece;
import ch.p1gu.spacelama.Model.tir.Tir;
import ch.p1gu.spacelama.Model.tir.TirDiagonal;
import ch.p1gu.spacelama.Model.tir.TirDroit;
import ch.p1gu.spacelama.world.World;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 *
 * @author Michael
 */
public class EnnemiNormal extends Ennemi {

    private float dernierTir = 0, cadenceTir;
    private boolean tirEnDiagonal;

    public EnnemiNormal(float x, float y, float width, float height) {
        super(x, y, width, height);
        switch ((int) (Math.random() * 2)) {
            case 0:
                tirEnDiagonal = true;
                cadenceTir = 0.6f;
                break;
            case 1:
                tirEnDiagonal = false;
                cadenceTir = 0.3f;
                break;
            default:

        }
    }

    @Override
    public void attaquer(float delta) {
        if (dernierTir > cadenceTir) {
            if (tirEnDiagonal) {
                World.lesTirsEnnemie.add(new TirDiagonal(rectangle.x, rectangle.y + rectangle.height / 2, Assets.tirLama.getRegionWidth(), Assets.tirLama.getRegionHeight(), Type.tirEnnemi, true));
                World.lesTirsEnnemie.add(new TirDiagonal(rectangle.x, rectangle.y + rectangle.height / 2, Assets.tirLama.getRegionWidth(), Assets.tirLama.getRegionHeight(), Type.tirEnnemi, false));
            } else {
                World.lesTirsEnnemie.add(new TirDroit(rectangle.x, rectangle.y + rectangle.height / 2, Assets.tirLama.getRegionWidth(), Assets.tirLama.getRegionHeight(), Type.tirEnnemi));
            }
            dernierTir = 0;
        } else {
            dernierTir += delta;
        }

    }

    @Override
    public void deplacer(float delta) {
        rectangle.x -= 200 * delta;
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        deplacer(delta);
        attaquer(delta);

    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(Assets.vaisseau, rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }

    @Override
    public void toucher(Entity entity) {
        mort = true;
        World.ajouterBonus(new Piece(rectangle.x, rectangle.y));
        World.ajouterScore(10);
    }
}
