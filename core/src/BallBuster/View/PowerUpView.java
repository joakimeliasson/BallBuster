package ballBuster.view;

import ballBuster.controller.PowerUpController;
import ballBuster.model.Ball;
import ballBuster.model.PowerUp;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

/**
 * Created by jacobth on 2015-04-25.
 */
public class PowerUpView implements ApplicationListener{

    private PowerUp powerUp;
    private Ball ball;
    private Sprite sprite;
    private SpriteBatch batch;
    private PowerUpController powerUpController;
    private ArrayList<Ball> ballList;

    public PowerUpView(PowerUp powerUp, ArrayList<Ball> ballList, Sprite sprite, SpriteBatch batch) {
        this.powerUp = powerUp;
        this.ballList = ballList;
        this.sprite = sprite;
        this.batch = batch;
        powerUpController = new PowerUpController();
    }
    @Override
    public void create() {
        sprite.setPosition(0, 0);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render() {
        powerUpController.powerUpSet(powerUp,ballList,sprite, Gdx.graphics.getDeltaTime(), batch);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {

    }
}
