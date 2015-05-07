package ballbuster.controller;

import ballbuster.model.Player;
import ballbuster.model.PowerUp;
import ballbuster.view.PowerUpView;
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
    private Sprite healthSprite;
    private SpriteBatch batch;

    public PowerUpController(ArrayList<PowerUp> powerUpList, ArrayList<Player> playerList, Sprite sprite, Sprite healthSprite, SpriteBatch batch){
        this.powerUpList = powerUpList;
        this.playerList = playerList;
        this.sprite = sprite;
        this.healthSprite = healthSprite;
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
        //if (powerUpList.get(randomInt).getPowerUp().toString() == "healthPack")
        //    powerUpView.powerUpSet(powerUpList.get(randomInt), playerList, healthSprite, Gdx.graphics.getDeltaTime(), batch);
        //else
            powerUpView.powerUpSet(powerUpList.get(randomInt), playerList, sprite, Gdx.graphics.getDeltaTime(), batch);
    }
}
