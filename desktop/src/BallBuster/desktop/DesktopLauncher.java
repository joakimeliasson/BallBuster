package BallBuster.desktop;

import ballbuster.controller.BallBuster;
import ballbuster.controller.MenuController;
import ballbuster.view.MenuView;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import java.awt.*;

public class DesktopLauncher {
	public static void main (String[] arg) {
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int width = gd.getDisplayMode().getWidth();
        int height = gd.getDisplayMode().getHeight();
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new MenuController(), config);
        config.height = height;
        config.width = width;
        config.resizable = false;
        config.title = "Ball Buster";
        config.fullscreen = true;
    }
}
