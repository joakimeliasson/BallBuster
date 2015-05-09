package BallBuster.desktop;

import ballbuster.controller.BallBuster;
import ballbuster.view.MenuView;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new BallBuster(), config);

        config.height = 1080;
        config.width = 1920;
    }
}
