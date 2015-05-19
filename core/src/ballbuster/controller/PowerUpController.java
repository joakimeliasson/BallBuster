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

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by jacobth on 2015-04-25.
 */
public class PowerUpController implements IController{

    private PowerUpView powerUpView;
    private HealthPackView healthPackView;
    private ArrayList<BlockTileView> tiles;
    private ArrayList<Point> forbiddenLocations;
    private PowerUp powerUp;
    private ArrayList<Player> playerList;
    private ArrayList<PowerUp> powerUpList;

    private Sprite sprite;
    private Sprite healthSprite;
    private SpriteBatch batch;
    private int x;
    private int y;
    private Point location;
    private Point spawnLocation;
    private Random random;

    public PowerUpController(ArrayList<PowerUp> powerUpList, ArrayList<Player> playerList, Sprite sprite, Sprite healthSprite, SpriteBatch batch, ArrayList<BlockTileView> tiles){
        this.powerUpList = powerUpList;
        this.playerList = playerList;
        this.sprite = sprite;
        this.healthSprite = healthSprite;
        this.batch = batch;
        this.tiles = tiles;
        forbiddenLocations = new ArrayList<>();
        location = new Point();
        spawnLocation = new Point();
        random = new Random();

    }
    private void setForbiddenLocations(){
        for (BlockTileView b : tiles) {
            for (float i = b.getTile().getX()-healthSprite.getWidth()/2; i < b.getTile().getX() + b.getWidth() + healthSprite.getWidth()/2; i++) {
                for (float k = b.getTile().getY()-healthSprite.getHeight()/2; k < b.getTile().getY() + b.getHeight() + healthSprite.getWidth(); k++) {
                    forbiddenLocations.add(new Point((int)i,(int)k));
                }
            }
        }
    }
    private void setHealthSpritePosition(){

            float pos = Gdx.graphics.getWidth();
            int xpos = random.nextInt(Math.round(pos)) - Gdx.graphics.getWidth() / 2;
            float pos2 = Gdx.graphics.getHeight();
            int ypos = random.nextInt(Math.round(pos2)) - Gdx.graphics.getHeight() / 2;

            spawnLocation.setLocation(xpos,ypos);
            if (!forbiddenLocations.contains(spawnLocation)) {
                this.x = xpos;
                this.y = ypos;
            } else {
                setHealthSpritePosition();
            }

    }

    @Override
    public void onCreate() {
        setForbiddenLocations();
        setHealthSpritePosition();
        healthSprite.setPosition(x,y);
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
