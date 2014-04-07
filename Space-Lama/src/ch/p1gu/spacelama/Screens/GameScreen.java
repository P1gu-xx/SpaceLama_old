/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.p1gu.spacelama.Screens;

import ch.p1gu.spacelama.RemoteAndroid;
import ch.p1gu.spacelama.world.World;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.RemoteInput;

/**
 *
 * @author Michael
 */
public class GameScreen implements Screen {

    private OrthographicCamera guiCam;
    private SpriteBatch batch;
    private World world;
  
    public GameScreen() {
      //   Gdx.input=RemoteAndroid.getInstance();
    }

    @Override
    public void render(float delta) {

        world.update(delta);
        guiCam.update();
        batch.setProjectionMatrix(guiCam.combined);
        batch.enableBlending();

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        world.render(batch);
        batch.end();
    }

    @Override
    public void resize(int x, int y) {
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        world = new World();

        guiCam = new OrthographicCamera(World.x, World.y+65);
        guiCam.position.set(World.x / 2,( World.y-65 )/ 2, 0);


    }

    @Override
    public void hide() {
        
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
        show();
    }

    @Override
    public void dispose() {
    }
}
