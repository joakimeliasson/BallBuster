package ballbuster.view;

import ballbuster.model.Ball;
import ballbuster.model.Player;
import ballbuster.model.PowerUp;
import ballbuster.model.Timer;
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
        font = new BitmapFont(Gdx.files.internal("core/images/test.fnt"));
    }


    public void powerUpSet(PowerUp powerUp, ArrayList<Player> playerList, Sprite sprite, float delta, SpriteBatch batch) {
        timer.update(delta);
        powerUpTimer.update(delta);
        showSprite(sprite);
        Player player = getHitPlayer(playerList, sprite, powerUpTimer);
        if(timer.hasTimeElapsed()) {
            draw(sprite, batch);
            if (player != null) {
                int random = (int )(Math.random() * 20 + 10);
                timer.reset(random);
                message = player.applyPowerUp(powerUp, playerList);
            }
        }
        resetBall(playerList, delta, powerUpTimer);
    }
    public void resetBall(ArrayList<Player> playerList, float delta, Timer objectTimer) {
        objectTimer.update(delta);
        for(Player player : playerList){
                if(objectTimer.hasTimeElapsed()) {
                    player.resetBall();
                    message = "";
                }
                else if(message!=null){
                    font.setColor(1,1,1,objectTimer.getRemaining()*2);
                    batch.begin();
                    font.draw(batch, message, 0-font.getBounds(message).width/2, 0);
                    batch.end();
                }
        }
    }

    public Player getHitPlayer(ArrayList<Player> playerList, Sprite sprite, Timer objectTimer) {
        for(Player player : playerList) {
            if (hasCollision(player.getBall(), sprite)){
                player.getBall().setHasPowerUp(true);
               hideSprite(sprite);
                objectTimer.reset();
                return player;
            }
        }
        return null;
    }
    public void hideSprite(Sprite sprite) {
        sprite.setSize(0,0);
    }
    public void showSprite(Sprite sprite) {
        sprite.setSize(sprite.getTexture().getWidth(), sprite.getTexture().getHeight());
    }

    public void draw(Sprite sprite, SpriteBatch batch) {
        batch.begin();
        batch.draw(sprite, sprite.getX(), sprite.getY(), sprite.getOriginX(), sprite.getOriginY(),
                sprite.getWidth(), sprite.getHeight(), sprite.getScaleX(), sprite.getScaleY(), sprite.getRotation());
        batch.end();
    }

    public void setMessage(String message){
        this.message = message;
    }
    public boolean hasCollision(Ball ball, Sprite sprite){
        double xDiff = ball.getX() + ball.getRadius() - sprite.getX() - sprite.getWidth()/2;
        double yDiff = ball.getY() + ball.getRadius() - sprite.getY() - sprite.getWidth()/2;

        double distance = Math.sqrt(Math.pow(xDiff, 2) + Math.pow(yDiff, 2));

        return distance < (ball.getRadius() + sprite.getHeight()/2);
    }

}
