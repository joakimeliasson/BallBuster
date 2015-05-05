package BallBuster.View;

import BallBuster.Controller.PowerUpController;
import BallBuster.Model.Ball;
import BallBuster.Model.Player;
import BallBuster.Model.PowerUp;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by jacobth on 2015-04-25.
 */
public class PowerUpView{

    private PowerUp powerUp;
    private Sprite sprite;
    private SpriteBatch batch;
    private ArrayList<Player> playerList;


    private Timer timer;
    private Timer powerUpTimer;
    private Random random;

    public PowerUpView(PowerUp powerUp, ArrayList<Player> playerList, Sprite sprite, SpriteBatch batch) {
        this.powerUp = powerUp;
        this.playerList = playerList;
        this.sprite = sprite;
        this.batch = batch;

        int random = (int )(Math.random() * 20 + 10);
        timer = new Timer(random);
        powerUpTimer = new Timer(5f);
        System.out.println(random);



    }



    public void powerUpSet(PowerUp powerUp, ArrayList<Player> playerList, Sprite sprite, float delta, SpriteBatch batch) {
        timer.update(delta);
        powerUpTimer.update(delta);
        showSprite(sprite);
        Player player = getHitPlayer(playerList, sprite);
        if(timer.hasTimeElapsed()) {
            draw(sprite, batch);
            if (player != null) {
                int random = (int )(Math.random() * 20 + 10);
                timer.reset(random);
                System.out.println(random);
                hideSprite(sprite);
                switch (powerUp.getPowerUp().toString()) {
                    case "speedUp":
                        player.getBall().setSpeed(0.4f);
                        System.out.println("speedUp");
                        break;
                    case "slowDown":
                        player.getBall().setSpeed(0.02f);
                        System.out.println("slowDown");
                        break;
                    case "invertKeys":
                        player.invertKeys(true);
                        player.setKeys(player.getRightKey(), player.getLeftKey(), player.getDownKey(), player.getUpKey(), player.getAuraKey());
                        System.out.println("invertKeys");
                        break;
                }
            }
        }
        resetBall(playerList, delta);
    }
    public void resetBall(ArrayList<Player> playerList, float delta) {
        powerUpTimer.update(delta);
        for(Player player : playerList){
            if(player.getBall().hasPowerUp()) {
                if(powerUpTimer.hasTimeElapsed()) {
                    if (player.hasInvertedKeys()) {
                        player.setKeys(player.getRightKey(), player.getLeftKey(), player.getDownKey(), player.getUpKey(), player.getAuraKey());
                        player.invertKeys(false);
                    }
                    player.getBall().setSpeed(0.1f);
                    player.getBall().setHasPowerUp(false);
                }
            }
        }
    }

    public Player getHitPlayer(ArrayList<Player> playerList, Sprite sprite) {
        for(Player player : playerList) {
            if (sprite.getBoundingRectangle().contains(player.getBall().getX(), player.getBall().getY())) {
                player.getBall().setHasPowerUp(true);
                powerUpTimer.reset();
                return player;
            }
        }
        return null;
    }
    private void hideSprite(Sprite sprite) {
        sprite.setSize(0,0);
    }
    private void showSprite(Sprite sprite) {
        sprite.setSize(sprite.getTexture().getWidth(), sprite.getTexture().getHeight());
    }

    private void draw(Sprite sprite, SpriteBatch batch) {
        batch.begin();
        batch.draw(sprite, sprite.getX(), sprite.getY(), sprite.getOriginX(), sprite.getOriginY(),
                sprite.getWidth(), sprite.getHeight(), sprite.getScaleX(), sprite.getScaleY(), sprite.getRotation());
        batch.end();
    }

    private class Timer {
        private float remaining;
        private float interval;

        public Timer(float interval) {
            this.interval = interval;
            this.remaining = interval;
        }
        public boolean hasTimeElapsed() {
            return remaining<0.0f;
        }
        public void reset() {
            remaining = interval;
        }
        public void reset(float interval) {
            this.interval = interval;
            this.remaining = interval;
        }
        public void update(float delta) {
            remaining = remaining - delta;
        }
    }

}
