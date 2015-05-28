package ballbuster.controller;

import ballbuster.model.Player;
import ballbuster.model.PowerUp;
import ballbuster.view.BallBusterView;
import ballbuster.view.BlockTileView;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by jacobth on 2015-04-28.
 */
public class BallBuster extends Game{

    private BallBusterView ballBusterView;

    public static final float SCALE = 100f;

    private Texture texture;
    private Player player;
    private Texture texture2;
    private Player player2;
    private boolean isGameOver = false;
    private boolean isAIActive;

    private AuraController auraController;
    private AuraController auraController2;
    private BallController ballController;
    private BallController ballController2;
    private MapController mapController;
    private TileController tileWallController;
    private PowerUpController powerUpController;
    private HudController hudController;

    private ArrayList<IController> controllerList;
    private ArrayList<BallController> ballList;
    private ArrayList<Player> playerList;
    private ArrayList<PowerUp> powerUpList;
    private ArrayList<BlockTileView> tileLocations;

    private CollisionController collisionController;

    private final String map; //Location of the map example: "core/res/TiledMaps/designmap.tmx"

    private World world;
    private OrthographicCamera camera;
    private SpriteBatch batch;

    public BallBuster(String map, boolean isAIActive) {
        this.map = map;
        this.isAIActive = isAIActive;
    }

    @Override
    public void create() {
        ballBusterView = new BallBusterView();
        ballBusterView.onCreate();
        world = ballBusterView.getWorld();
        camera = ballBusterView.getCamera();
        batch = ballBusterView.getBatch();

        controllerList = new ArrayList<>();

        createWalls();
        createMap();
        createBalls();
        createAura();
        createPowerUp();
        collision();
        controllerList.add(new LightController(world, camera,playerList));

        for(IController controller : controllerList)
            controller.onCreate();

        collisionController = new CollisionController(tileWallController.getWallList(), ballList, batch);

        controllerList.add(collisionController);
    }

    @Override
    public void render() {
        ballBusterView.onRender();

        for(IController controller : controllerList)
            controller.onRender();

        world.setContactListener(collisionController);

        //ballBusterView.setDebugRenderer();
        ballBusterView.setDebugRenderer();
        for(BallController ball: ballList){
            if(ball.getBall().getShield()<0){
                isGameOver = true;
                break;
            }
        }
    }

    public boolean getIsGameOver(){
        return isGameOver;
    }

    public void createBalls() {
        playerList = new ArrayList<>();
        FileHandle ballFileHandle = Gdx.files.internal("core/images/leftBall.png");
        texture = new Texture(ballFileHandle);

        FileHandle shieldFileHandle = Gdx.files.internal("core/images/playershield.png");
        Texture shieldTexture = new Texture(shieldFileHandle);

        //aura = new Aura();
        //ball = new Ball(-camera.viewportWidth/2, -camera.viewportHeight/2;
        player = new Player(1,"Player1",-camera.viewportWidth/2,-camera.viewportHeight/2);
        //player.getBall().getAura().setPosition(ball.getX(), ball.getY());
        player.setKeys(Input.Keys.A, Input.Keys.D, Input.Keys.W, Input.Keys.S, Input.Keys.ALT_LEFT,Input.Keys.Q);
        playerList.add(player);

        FileHandle ballFileHandle2 = Gdx.files.internal("core/images/rightBall.png");
        texture2 = new Texture(ballFileHandle2);

        FileHandle shield2FileHandle = Gdx.files.internal("core/images/playershield2.png");
        Texture shieldTexture2 = new Texture(shield2FileHandle);

        //aura2 = new Aura();
        //ball2 = new Ball();
        player2 = new Player(2, "Player2",camera.viewportWidth/2-100f, camera.viewportHeight/2-100f);
        player2.setKeys(Input.Keys.DPAD_LEFT, Input.Keys.DPAD_RIGHT, Input.Keys.DPAD_UP, Input.Keys.DPAD_DOWN, Input.Keys.SPACE, Input.Keys.M);
        this.playerList.add(player2);

        ballController = new BallController(player, batch, texture, world, shieldTexture);
        if(isAIActive){
            ballController2 = new AIController(player2, batch, texture2, world, shieldTexture2,playerList);
        }else{
            ballController2 = new BallController(player2, batch, texture2, world, shieldTexture2);

        }

        controllerList.add(ballController);
        controllerList.add(ballController2);

        hudController = new HudController(player,player2,batch,camera);
        controllerList.add(hudController);
    }
    public void createMap() {
        mapController = new MapController(map,world, camera);
        controllerList.add(mapController);
        tileLocations = mapController.getTileLocations();
    }
    public void createAura() {
        auraController = new AuraController(player, ballController.getBody(),mapController.getBodyListPlayer1(), batch);
        auraController2 = new AuraController(player2, ballController2.getBody(), mapController.getBodyListPlayer2(), batch);
        controllerList.add(auraController);
        controllerList.add(auraController2);
    }
    public void createPowerUp(){
        powerUpList = new ArrayList<>();
        PowerUp speedUp = new PowerUp("speedUp");
        PowerUp slowDown = new PowerUp("slowDown");
        PowerUp invertKeys = new PowerUp("invertKeys");
        PowerUp damageOther = new PowerUp("damageOther");
        PowerUp invertOther = new PowerUp("invertOther");
        powerUpList.add(speedUp);
        powerUpList.add(slowDown);
        powerUpList.add(invertKeys);
        powerUpList.add(damageOther);
        powerUpList.add(invertOther);

        FileHandle powerUpFileHandle = Gdx.files.internal("core/images/powerUp.png");
        Texture powerUpTexture = new Texture(powerUpFileHandle);

        FileHandle healthPackFileHandle = Gdx.files.internal("core/images/heart.png");
        Texture healthPackTexture = new Texture(healthPackFileHandle);

        powerUpController = new PowerUpController(powerUpList,playerList, new Sprite(powerUpTexture), new Sprite(healthPackTexture), batch, tileLocations);
        controllerList.add(powerUpController);
    }

    private void createWalls() {
        tileWallController = new TileController(world, batch, camera);

        controllerList.add(tileWallController);
    }
    private void collision() {
        ballList = new ArrayList<>();

        ballList.add(ballController);
        ballList.add(ballController2);
    }

    public List<Player> getPlayers(){
        List<Player> playerList = new LinkedList<>();
        playerList.add(player);
        playerList.add(player2);
        return playerList;
    }
}
