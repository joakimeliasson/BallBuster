package BallBuster.Controller;

import BallBuster.Model.Ball;
import BallBuster.Model.Player;
import BallBuster.Model.PowerUp;
import BallBuster.View.PowerUpView;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by jacobth on 2015-04-25.
 */
public class PowerUpController implements IController{

    private PowerUpView powerUpView;
    private PowerUp powerUp;
    private ArrayList<Player> playerList;
    private Sprite sprite;
    private SpriteBatch batch;

    public PowerUpController(PowerUp powerUp, ArrayList<Player> playerList, Sprite sprite, SpriteBatch batch){
        this.powerUp = powerUp;
        this.playerList = playerList;
        this.sprite = sprite;
        this.batch = batch;
    }

    @Override
    public void onCreate() {
        sprite.setPosition(0,0);
        powerUpView = new PowerUpView(powerUp, playerList, sprite, batch);
    }

    @Override
    public void onRender() {
         powerUpView.powerUpSet(powerUp, playerList, sprite, Gdx.graphics.getDeltaTime(), batch);
    }
}
