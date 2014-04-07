/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.p1gu.spacelama.world;

import ch.p1gu.spacelama.Assets;
import ch.p1gu.spacelama.Model.Ennemi;
import ch.p1gu.spacelama.Model.EnnemiNormal;
import ch.p1gu.spacelama.Model.Entity;
import ch.p1gu.spacelama.Model.Hero;
import ch.p1gu.spacelama.Model.tir.Tir;
import ch.p1gu.spacelama.Screens.GameOverScreen;
import ch.p1gu.spacelama.SpaceLama;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Michael
 */
public class World {

    public static final int x = 1280;
    public static final int y = 640;
    private Sprite bg;
    private float decalageBg;
    private MoteurPhisique moteurPhisique;
    private float dernierEnnemieGenerer = 0, cadenceGeneration = 1.5f;
    private Hero hero;
    private static List<Entity> lesBonus;
    private List<Ennemi> lesEnnemies;
    public static List<Tir> lesTirsEnnemie;
    public static int score, mulitiplicateurDeScore;

    public World() {
        hero = new Hero(50, 50, Assets.superLama.getRegionWidth(), Assets.superLama.getRegionHeight());
        moteurPhisique = new MoteurPhisique();
        bg = new Sprite(Assets.bgJeux);
        lesBonus = new ArrayList();
        lesEnnemies = new ArrayList();
        lesTirsEnnemie = new ArrayList<Tir>();
        decalageBg = 0;
        score = 0;
        mulitiplicateurDeScore = 1;
    }

    public void update(float delta) {
        //bg
        decalageBg -= delta * 100f;
        bg.setX(decalageBg);

        if (decalageBg < -2560) {
            decalageBg = 0;
        }

        //hero
        hero.update(delta);
        //ennemie
        //-Generation ennemie
        if (dernierEnnemieGenerer > cadenceGeneration) {
            switch ((int) (Math.random() * 1)) {
                case 0:
                    lesEnnemies.add(new EnnemiNormal(x, (float) (Math.random() * (y - Assets.vaisseau.getRegionHeight())), Assets.vaisseau.getRegionWidth(), Assets.vaisseau.getRegionHeight()));
                    dernierEnnemieGenerer = 0;
                    cadenceGeneration -= 0.01f;
                    break;
                default:

            }

        } else {
            dernierEnnemieGenerer += delta;

        }
        //-actualisation ennemi
        for (Entity ennemi : lesEnnemies) {
            ennemi.update(delta);
        }
        for (Entity bonus : lesBonus) {
            bonus.update(delta);
        }
        for (Tir tir : lesTirsEnnemie) {
            tir.update(delta);
        }

        //moteurPhisique
        moteurPhisique.deplacerEntity(hero);
        //hero ennemi
        moteurPhisique.dectecterCollision(hero, lesEnnemies);
        //hero tir ennemi


        moteurPhisique.dectecterCollision(hero, lesTirsEnnemie);


        //ennemi tir hero
        moteurPhisique.dectecterCollision(lesEnnemies, hero.lesTirs);

        supprimerLesMort();
    }

    public void render(SpriteBatch batch) {
        bg.draw(batch, 0.9f);

        for (Entity ennemi : lesEnnemies) {
            ennemi.draw(batch);
        }
        for (Entity bonus : lesBonus) {
            bonus.draw(batch);
        }
        for (Tir tir : lesTirsEnnemie) {
            tir.draw(batch);
        }
        hero.draw(batch);

        Assets.white.draw(batch, "VIE : " + hero.vie, 0, 20);
        Assets.white.draw(batch, "Bouclier : " + (int) (hero.bouclier * 100), 150, 20);
    }

    public void pause() {
    }

    public void resume() {
    }

    public void supprimerLesMort() {
        if (hero.mort) {
            SpaceLama.game.setScreen(new GameOverScreen(score));
        }
        Ennemi ennemi;
        for (int i = 0; i < lesEnnemies.size(); i++) {
            ennemi = lesEnnemies.get(i);
            if (ennemi.mort) {
                lesEnnemies.remove(ennemi);
                i--;
            }
        }

        Tir tir;
        for (int i = 0; i < lesTirsEnnemie.size(); i++) {
            tir = lesTirsEnnemie.get(i);
            if (tir.mort) {
                lesTirsEnnemie.remove(tir);
                i--;
            }
        }
        for (int i = 0; i < hero.lesTirs.size(); i++) {
            tir = hero.lesTirs.get(i);
            if (tir.mort) {
                hero.lesTirs.remove(tir);
                i--;
            }
        }

    }

    public static void ajouterScore(int scoreAjouter) {
        score += scoreAjouter * mulitiplicateurDeScore;
        mulitiplicateurDeScore++;
    }
    
    public static void ajouterBonus(Entity e){
        lesBonus.add(e);
    }
}
