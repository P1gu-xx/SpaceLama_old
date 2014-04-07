package ch.p1gu.spacelama;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {

    public static void main(String[] args) {
        
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "Space-Lama";
        cfg.fullscreen = false;
        cfg.useGL20 = true;
        cfg.width = 1280;
        cfg.height = 720;
        cfg.addIcon("data/logo32.png", Files.FileType.Internal);

        new LwjglApplication(new SpaceLama(), cfg);
    }
}
