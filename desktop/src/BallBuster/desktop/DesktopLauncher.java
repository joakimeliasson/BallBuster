package BallBuster.desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import BallBuster.View.BallBusterView;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new BallBusterView(), config);

        config.height = Gdx.graphics.getHeight();
        config.width = Gdx.graphics.getWidth();
    }
}
