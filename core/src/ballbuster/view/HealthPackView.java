package ballbuster.view;

import ballbuster.model.Player;
import ballbuster.model.PowerUp;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ballbuster.model.Timer;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Joakim on 2015-05-07.
 */
public class HealthPackView extends PowerUpView {

    private Timer healthPackTimer;

    private Timer timer;
    private String message;

    public HealthPackView(PowerUp powerUp, ArrayList<Player> playerList, Sprite sprite, SpriteBatch batch) {
        super(powerUp, playerList, sprite, batch);

        int random = (int )(Math.random() * 20);
        this.timer = new Timer(random);
        healthPackTimer = new Timer(5f);
        System.out.println(random);
    }

    public void healthPackSet(PowerUp powerUp, ArrayList<Player> playerList, Sprite sprite, float delta, SpriteBatch batch, int x, int y){
        timer.update(delta);
        healthPackTimer.update(delta);


        showSprite(sprite);
        Player player = getHitPlayer(playerList, sprite);
        if(timer.hasTimeElapsed()) {
            if (getHitPlayer(playerList, sprite) != null)
                sprite.setPosition(x, y);


            draw(sprite, batch);
            if (player != null) {
                int random = (int) (Math.random() * 20 + 10);
                timer.reset(random);
                System.out.println(random);
                hideSprite(sprite);
                if (powerUp.getPowerUp().toString() == "healthPack"){
                    player.getBall().addHealthToShield(10);
                    System.out.println(player.getBall().getShield());
                    this.message = player.getPlayerName()+" found a healthPack. +10HP";
                    super.setMessage(message);
                }
            }
        }
    }
}
