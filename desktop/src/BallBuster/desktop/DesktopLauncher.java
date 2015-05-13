package BallBuster.desktop;

import ballbuster.controller.BallBuster;
import ballbuster.view.MenuView;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new MenuView(), config);

        config.height = 720;
        config.width = 1280;
    }
}
