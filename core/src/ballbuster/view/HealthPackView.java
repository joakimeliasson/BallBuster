package ballbuster.view;

import ballbuster.model.Player;
import ballbuster.model.PowerUp;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ballbuster.model.Timer;

import java.util.ArrayList;

/**
 * Created by Joakim on 2015-05-07.
 */
public class HealthPackView extends PowerUpView {

    private Timer healthPackTimer;

    private Timer timer;

    public HealthPackView(ArrayList<Player> playerList, SpriteBatch batch) {
        super(playerList, batch);

        int random = (int) (Math.random() * 20);
        this.timer = new Timer(random);
        healthPackTimer = new Timer(5f);
    }

    public void healthPackSet(PowerUp powerUp, ArrayList<Player> playerList, Sprite sprite, float delta, SpriteBatch batch, int x, int y) {
        timer.update(delta);
        healthPackTimer.update(delta);
        showSprite(sprite);
        Player player = this.getHitPlayer(playerList, sprite, healthPackTimer);
        if (timer.hasTimeElapsed()) {
            if (player != null)
                sprite.setPosition(x, y);
            draw(sprite, batch);
            if (player != null) {
                int random = (int) (Math.random() * 20 + 10);
                timer.reset(random);
                String message = player.applyHealthPack(powerUp, playerList);
                setMessage(message);
                resetBall(playerList, delta, healthPackTimer);
            }
        }

    }
    @Override
    public Player getHitPlayer(ArrayList<Player> playerList, Sprite sprite, Timer objectTimer) {
        for (Player player : playerList) {
            if (hasCollision(player.getBall(), sprite)) {
                player.getBall().setHasPowerUp(true);
                hideSprite(sprite);
                objectTimer.reset();
                return player;
            }
        }
        return null;
    }

}
