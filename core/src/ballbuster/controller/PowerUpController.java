package ballbuster.controller;

import ballbuster.model.Player;
import ballbuster.model.PowerUp;
import ballbuster.view.HealthPackView;
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
    private HealthPackView healthPackView;
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
        sprite.setPosition(0, 0);
        float pos = Gdx.graphics.getWidth();
        Random random = new Random();
        int x = random.nextInt(Math.round(pos)-Gdx.graphics.getWidth()/2);
        float pos2 = Gdx.graphics.getHeight();
        int y  = random.nextInt(Math.round(pos2)-Gdx.graphics.getHeight()/2);
        healthSprite.setPosition(x, y);

        powerUpView = new PowerUpView(powerUp, playerList, sprite, batch);
        healthPackView = new HealthPackView(new PowerUp("healthPack"), playerList, healthSprite, batch);
    }

    @Override
    public void onRender() {
        Random random = new Random();
        int randomInt = random.nextInt(powerUpList.size());
        powerUpView.powerUpSet(powerUpList.get(randomInt), playerList, sprite, Gdx.graphics.getDeltaTime(), batch);

        Random r = new Random();
        float pos = Gdx.graphics.getWidth();
        int x = r.nextInt(Math.round(pos) - Gdx.graphics.getWidth() / 2);
        float pos2 = Gdx.graphics.getHeight();
        int y = r.nextInt(Math.round(pos2) - Gdx.graphics.getHeight() / 2);

        healthPackView.healthPackSet(new PowerUp("healthPack"), playerList, healthSprite, Gdx.graphics.getDeltaTime(), batch, x, y);
    }

}
