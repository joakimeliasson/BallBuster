package ballbuster.view;

import ballbuster.model.Player;
import ballbuster.model.Timer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by jacobth on 2015-05-10.
 */
public class CollisionView {

    private BitmapFont font;

    public CollisionView() {
        font = new BitmapFont(Gdx.files.internal("test.fnt"));
        font.setColor(1, 1, 1, 1);
    }

    public void renderFont(Player player, String message, SpriteBatch batch, Timer timer) {
        if (message != null && player != null) {
            font.setColor(255 / 255f, 184 / 255f, 118 / 255f, timer.getRemaining() * 2);
            batch.begin();
            font.draw(batch, message, player.getBall().getX() - font.getBounds(message).width / 2, player.getBall().getY() - 40f);
            batch.end();
        }
    }
}
