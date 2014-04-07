/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.p1gu.spacelama.Screens;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;
import ch.p1gu.spacelama.Assets;
import ch.p1gu.spacelama.SpaceLama;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 *
 * @author Michael
 */
public class GameOverScreen implements Screen{
    
    private int score;
    private SpriteBatch batch;
    private TweenManager tm;

    public GameOverScreen(int score) {
        this.score=score;
        batch=new SpriteBatch();
                    tm=new TweenManager();
        Tween.call(new TweenCallback() {

            @Override
            public void onEvent(int type, BaseTween<?> source) {
                SpaceLama.game.setScreen(new MenuScreen());
            }
        }).delay(3).start(tm);
    }
     
     
     
     
      @Override
    public void render(float f) {
                  
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        Assets.white.draw(batch, "Game over", Gdx.graphics.getWidth()/2-50,Gdx.graphics.getHeight()/3*2 );
        
        Assets.white.draw(batch, "Score : "+score, Gdx.graphics.getWidth()/2-50,Gdx.graphics.getHeight()/2 );
        batch.end();
        tm.update(f);
    }

    @Override
    public void resize(int i, int i1) {
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    }
}
