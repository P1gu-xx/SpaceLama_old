/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.p1gu.spacelama.Model;

import ch.p1gu.spacelama.Model.tir.Tir;
import ch.p1gu.spacelama.Assets;
import ch.p1gu.spacelama.Model.tir.TirDroit;
import ch.p1gu.spacelama.world.World;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Michael
 */
public class Hero extends Entity {

	private static final float SPEED = 150f;
	private float vitesseDeRegenerationVie = 10f, deltaRegenerationVie = 0f;
	private static TextureRegion superLamaTexture = Assets.superLama;
	private static Texture textureBouclier = Assets.bouclier;
	public List<Tir> lesTirs;
	private float cadenceDeTir = 0.4f, dernierTir = 0;
	private boolean bouclierActiver;
	private float maxBouclier = 2, vitesseDeRegenerationBouclier = 2f;
	public float bouclier = 2;

	public Hero(float x, float y, float width, float height) {
		super(x, y, width, height, Type.hero);
		lesTirs = new ArrayList<Tir>();
		maxVie = 5;
		vie = 5;
	}

	@Override
	public void update(float delta) {
		calculeVitesse(delta);
		tirer(delta);
		for (Tir tir : lesTirs) {
			tir.update(delta);
		}
		regeneVie(delta);
		updateBouclier(delta);
	}

	private void updateBouclier(float delta) {
		if (bouclier > 1 || bouclierActiver) {

			if (Gdx.input.isTouched(0)
					&& Gdx.input.getX(0) > Gdx.graphics.getWidth() / 2) {
				bouclierActiver = true;
			} else if (Gdx.input.isTouched(1)
					&& Gdx.input.getX(1) > Gdx.graphics.getWidth() / 2) {
				bouclierActiver = true;
			}

			else if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)) {
				bouclierActiver = true;
			} else {
				bouclierActiver = false;
			}
		}
		if (bouclierActiver) {
			bouclier -= delta;
			if (bouclier < 0) {
				bouclier = 0;
				bouclierActiver = false;
			}
		} else {
			if (bouclier < maxVie) {
				bouclier += delta / vitesseDeRegenerationBouclier;
				if (bouclier > maxBouclier) {
					bouclier = maxBouclier;
				}
			}
		}
	}

	private void regeneVie(float delta) {
		if (vie < maxVie) {
			if (deltaRegenerationVie > vitesseDeRegenerationVie && vie < maxVie) {
				vie++;
				deltaRegenerationVie = 0;
			} else if (deltaRegenerationVie < vitesseDeRegenerationVie) {
				deltaRegenerationVie += delta;
			}
		}
	}

	/**
	 * Permet de calculer la vittesse par rapport aux touches appuyé ou a
	 * l'accelerometre.
	 * 
	 */
	public void calculeVitesse(float delta) {
		vitesse.x = 0;
		vitesse.y = 0;
		if (Gdx.input.isPeripheralAvailable(Input.Peripheral.Accelerometer)) {
			float adjustedX = ((Gdx.input.getAccelerometerX() - 5) / 2);

			adjustedX = (adjustedX) * SPEED;

			// y: -2 (left), 0 (still), 2 (right)
			float adjustedY = ((Gdx.input.getAccelerometerY()) / 2);
			adjustedY = (adjustedY) * SPEED;

			// notice the inverted axis because the game is displayed in
			// landscape mode
			vitesse.x = (adjustedY * delta);
			vitesse.y = (-adjustedX * delta);

		} else {
			if (Gdx.input.isKeyPressed(Input.Keys.W)) {
				vitesse.y = SPEED * delta;
			}
			if (Gdx.input.isKeyPressed(Input.Keys.S)) {
				vitesse.y = -SPEED * delta;
			}
			if (Gdx.input.isKeyPressed(Input.Keys.A)) {
				vitesse.x = -SPEED * delta;
			}
			if (Gdx.input.isKeyPressed(Input.Keys.D)) {
				vitesse.x = SPEED * delta;
			}
		}
	}

	@Override
	public void draw(SpriteBatch batch) {
		batch.draw(superLamaTexture, rectangle.x, rectangle.y, rectangle.width,
				rectangle.height);
		for (Tir tir : lesTirs) {
			tir.draw(batch);
		}
		if (bouclierActiver) {
			batch.draw(
					textureBouclier,
					rectangle.x
							- ((textureBouclier.getWidth() - rectangle.width) / 2),
					rectangle.y
							- ((textureBouclier.getHeight() - rectangle.height) / 2));
		}

	}

	@Override
	public void toucher(Entity entity) {

		switch (entity.type) {
		case tirEnnemi:
			if (!bouclierActiver) {
				vie -= 1;
				if (vie <= 0) {
					mort = true;
				}
				World.mulitiplicateurDeScore = 1;
			}
			break;
		case ennemi: {
			if (!bouclierActiver) {
				vie -= 1;
				if (vie <= 0) {
					mort = true;
				}
				World.mulitiplicateurDeScore = 1;
			}
		}
		default:

		}
	}

	public void tirer(float delta) {
		if (dernierTir > cadenceDeTir) {

			if (Gdx.input.isTouched(0)
					&& Gdx.input.getX(0) < Gdx.graphics.getWidth()/2) {
				lesTirs.add(new TirDroit(rectangle.x + rectangle.width,
						rectangle.y + rectangle.height - 10, Assets.tirLama
								.getRegionWidth(), Assets.tirLama
								.getRegionHeight(), Type.tirHero));
				dernierTir = 0;
			} else if (Gdx.input.isTouched(1)
					&& Gdx.input.getX(1) < Gdx.graphics.getWidth()/2) {
				lesTirs.add(new TirDroit(rectangle.x + rectangle.width,
						rectangle.y + rectangle.height - 10, Assets.tirLama
								.getRegionWidth(), Assets.tirLama
								.getRegionHeight(), Type.tirHero));
				dernierTir = 0;

			} else if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
				lesTirs.add(new TirDroit(rectangle.x + rectangle.width,
						rectangle.y + rectangle.height - 10, Assets.tirLama
								.getRegionWidth(), Assets.tirLama
								.getRegionHeight(), Type.tirHero));
				dernierTir = 0;
			}
		} else {
			dernierTir += delta;
		}
	}
}
