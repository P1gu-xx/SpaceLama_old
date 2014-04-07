/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.p1gu.spacelama.Screens;

import ch.p1gu.spacelama.SpaceLama;
import com.badlogic.gdx.Screen;

/**
 *
 * @author Michael
 */
public class GameSettingsScreen implements Screen{
    

    public GameSettingsScreen() {

    }
     
     
      @Override
    public void render(float f) {
    }

    @Override
    public void resize(int i, int i1) {
    }

    @Override
    public void show() {
        SpaceLama.game.setScreen(new GameScreen());
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
