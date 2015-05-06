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

    private BallBuster ballBuster;
    private PowerUpView powerUpView;
    private PowerUp powerUp;
    private ArrayList<Player> playerList;
    private ArrayList<PowerUp> powerUpList;
    private Sprite sprite;
    private SpriteBatch batch;

    public PowerUpController(ArrayList<PowerUp> powerUpList, ArrayList<Player> playerList, Sprite sprite, SpriteBatch batch){
        this.powerUpList = powerUpList;
        this.playerList = playerList;
        this.sprite = sprite;
        this.batch = batch;
        ballBuster = new BallBuster();
    }

    @Override
    public void onCreate() {
        sprite.setPosition(0,0);
        powerUpView = new PowerUpView(powerUp, playerList, sprite, batch);
    }

    @Override
    public void onRender() {
        Random random = new Random();
        int randomInt = random.nextInt(powerUpList.size());
         powerUpView.powerUpSet(powerUpList.get(randomInt), playerList, sprite, Gdx.graphics.getDeltaTime(), batch);
    }
}
