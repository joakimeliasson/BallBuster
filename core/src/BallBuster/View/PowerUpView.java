package BallBuster.View;

import BallBuster.Controller.PowerUpController;
import BallBuster.Model.Ball;
import BallBuster.Model.Player;
import BallBuster.Model.PowerUp;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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

    private boolean isHidden;

    private BitmapFont font;

    private Timer timer;
    private Timer powerUpTimer;
    private Timer fontTimer;
    private Random random;

    private String message;

    public PowerUpView(PowerUp powerUp, ArrayList<Player> playerList, Sprite sprite, SpriteBatch batch) {
        this.powerUp = powerUp;
        this.playerList = playerList;
        this.sprite = sprite;
        this.batch = batch;

        int random = (int )(Math.random() * 20);
        timer = new Timer(random);
        powerUpTimer = new Timer(10f);
        fontTimer = new Timer(5f);
        System.out.println(random);
        isHidden = true;

        font = new BitmapFont(Gdx.files.internal("core/images/test.fnt"));
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
                        player.getBall().setSpeed(player.getBall().getSpeed()*2);
                        System.out.println("speedUp");
                        message = "Obtained Faster Speed!";
                        break;
                    case "slowDown":
                        player.getBall().setSpeed(0.02f);
                        System.out.println("slowDown");
                        message = "Obtained Slower Speed!";
                        break;
                    case "invertKeys":
                        player.invertKeys(true);
                        player.setKeys(player.getRightKey(), player.getLeftKey(), player.getDownKey(), player.getUpKey(), player.getAuraKey());
                        System.out.println("invertKeys");
                        message = "Inverted Keys!";
                        break;
                    case "damageOther":
                        for (Player p : playerList){
                            if (!p.equals(player)){
                                p.getBall().shieldDamage(20);
                                System.out.println("Du skadade motspelaren." + p.getBall().getShield());
                                message = (int)p.getBall().getShield() + " Damage to the Other Player!";
                            }
                        }
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
                    player.getBall().setSpeed(0.5f);
                    player.getBall().setHasPowerUp(false);
                    message = "";
                }
                else if(message!=null){
                    font.setColor(1,1,1,powerUpTimer.remaining*2);
                    batch.begin();
                    font.draw(batch, message, 0-(font.getBounds(message).width/2), 0);
                    batch.end();
                }
            }
        }
    }

    public Player getHitPlayer(ArrayList<Player> playerList, Sprite sprite) {
        for(Player player : playerList) {
            if (sprite.getBoundingRectangle().contains(player.getBall().getX(), player.getBall().getY())) {
                player.getBall().setHasPowerUp(true);
                powerUpTimer.reset();
                fontTimer.reset();
                return player;
            }
        }
        return null;
    }
    private void hideSprite(Sprite sprite) {
        sprite.setSize(0, 0);
    }
    private void showSprite(Sprite sprite) {
        sprite.setSize(sprite.getTexture().getWidth(), sprite.getTexture().getHeight());
        isHidden = true;
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
