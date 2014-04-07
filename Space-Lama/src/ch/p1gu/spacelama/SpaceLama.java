package ch.p1gu.spacelama;

import ch.p1gu.spacelama.Screens.SplashScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.FPSLogger;


public class SpaceLama extends Game{

    public static Game game;
    public final static String TITLE="Space Lama - 0.0.0";
    public final static String LOG="Space-Lama";
    private FPSLogger fps;


    public SpaceLama() {
        game=this;

    }    
    
    @Override
    public void create() {
        Assets.load();
        fps=new FPSLogger();
        setScreen(new SplashScreen());
    }

    @Override
    public void dispose() {
        super.dispose(); 
        Assets.dispose();
    }

    @Override
    public void render() {
        super.render();
        fps.log();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void pause() {
        super.pause(); 
    }

    @Override
    public void resume() {
        super.resume(); 
        Assets.load();
    }   
	
}
