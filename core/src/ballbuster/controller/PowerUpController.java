package ballbuster.controller;

import ballbuster.model.Player;
import ballbuster.model.PowerUp;
import ballbuster.view.BlockTileView;
import ballbuster.view.HealthPackView;
import ballbuster.view.MapView;
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
    private MapView mapView;
    private ArrayList<BlockTileView> tiles;
    private ArrayList<Float> forbiddenXLocations;
    private ArrayList<Float> forbiddenYLocations;
    private PowerUp powerUp;
    private ArrayList<Player> playerList;
    private ArrayList<PowerUp> powerUpList;

    private Sprite sprite;
    private Sprite healthSprite;
    private SpriteBatch batch;
    private int x;
    private int y;

    public PowerUpController(ArrayList<PowerUp> powerUpList, ArrayList<Player> playerList, Sprite sprite, Sprite healthSprite, SpriteBatch batch, ArrayList<BlockTileView> tiles){
        this.powerUpList = powerUpList;
        this.playerList = playerList;
        this.sprite = sprite;
        this.healthSprite = healthSprite;
        this.batch = batch;
        this.tiles = tiles;
    }

    private void setHealthSpritePosition(){
        for (BlockTileView b : tiles) {
            forbiddenXLocations = new ArrayList<>();
            forbiddenYLocations = new ArrayList<>();
            for (float i = b.getTileX(); i < b.getTileX() + b.getWidth(); i++) {
                for (float k = b.getTileY(); k < b.getTileY() + b.getHeight(); k++) {
                    forbiddenYLocations.add(k);
                }
                forbiddenXLocations.add(i);
            }
            float pos = Gdx.graphics.getWidth();
            Random random = new Random();
            int x = random.nextInt(Math.round(pos)) - Gdx.graphics.getWidth() / 2;
            float pos2 = Gdx.graphics.getHeight();
            int y = random.nextInt(Math.round(pos2)) - Gdx.graphics.getHeight() / 2;

            if (!forbiddenXLocations.contains(x) && !forbiddenYLocations.contains(y)) {
                this.x = x;
                this.y = y;
            } else {
                setHealthSpritePosition();
            }
        }
    }

    @Override
    public void onCreate() {

        setHealthSpritePosition();

        powerUpView = new PowerUpView(powerUp, playerList, sprite, batch);
        healthPackView = new HealthPackView(new PowerUp("healthPack"), playerList, healthSprite, batch);
    }

    @Override
    public void onRender() {
        Random random = new Random();
        int randomInt = random.nextInt(powerUpList.size());
        powerUpView.powerUpSet(powerUpList.get(randomInt), playerList, sprite, Gdx.graphics.getDeltaTime(), batch);


        setHealthSpritePosition();
        healthPackView.healthPackSet(new PowerUp("healthPack"), playerList, healthSprite, Gdx.graphics.getDeltaTime(), batch, x, y);
    }

}
