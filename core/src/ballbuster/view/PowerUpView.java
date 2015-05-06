package ballbuster.view;

import ballbuster.model.Player;
import ballbuster.model.PowerUp;
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


    private Timer timer;
    private Timer powerUpTimer;
    private Random random;
    private String message;
    private BitmapFont font;

    public PowerUpView(PowerUp powerUp, ArrayList<Player> playerList, Sprite sprite, SpriteBatch batch) {
        this.powerUp = powerUp;
        this.playerList = playerList;
        this.sprite = sprite;
        this.batch = batch;

        int random = (int )(Math.random() * 20 + 10);
        timer = new Timer(random);
        powerUpTimer = new Timer(5f);
        System.out.println(random);
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
                        player.setSpeedUp(true);
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
                                message = "20 Damage to the Other Player!";
                                System.out.println("Du skadade motspelaren med 20 damage. "+ p.getPlayerName() +" har nu "+ p.getBall().getShield()+" HP kvar.");
                            }
                        }
                        break;
                    case "invertOther":
                        for (Player p : playerList){
                            if (!p.equals(player)){
                                p.invertKeys(true);
                                p.getBall().setHasPowerUp(true);
                                p.setKeys(p.getRightKey(), p.getLeftKey(), p.getDownKey(), p.getUpKey(), p.getAuraKey());
                                System.out.println("invertKeys for other player");
                            }
                        }
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
                    player.getBall().setSpeed(0.5f);
                    player.getBall().setHasPowerUp(false);
                    player.setSpeedUp(false);
                    message = "";
                }
                else if(message!=null){
                    font.setColor(1,1,1,powerUpTimer.remaining*2);
                    batch.begin();
                    font.draw(batch, message, 0-font.getBounds(message).width/2, 0);
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
