package BallBuster.Controller;

import BallBuster.Model.Aura;
import BallBuster.Model.Ball;
import BallBuster.Model.Player;
import BallBuster.Model.Tile.Tile;
import BallBuster.View.*;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;

/**
 * Created by jacobth on 2015-04-28.
 */
public class BallBuster extends Game{
    private OrthographicCamera camera;
    private World world;

    public static final float SCALE = 100f;

    private Box2DDebugRenderer debugRenderer;
    private Matrix4 debugMatrix;

    private SpriteBatch batch;

    private Ball ball;
    private Texture texture;
    private Player player;

    private Ball ball2;
    private Texture texture2;
    private Player player2;

    private Aura aura;
    private Aura aura2;


    private AuraController auraController;
    private AuraController auraController2;
    private BallController ballController;
    private BallController ballController2;
    private MapController mapController;
    private TileController leftWallController;
    private TileController rightWallController;
    private TileController upWallController;
    private TileController downWallController;

    private ArrayList<IController> controllerList;
    private ArrayList<BallController> ballList;
    private ArrayList<TileController> wallList;

    @Override
    public void create() {
        world = new World(new Vector2(0, 0), true);

        debugRenderer = new Box2DDebugRenderer();

        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        Gdx.gl.glClearColor(106f / 255f, 165f / 255f, 255f / 255f, 1f);

        batch = new SpriteBatch();

        controllerList = new ArrayList<>();

        createWalls();
        createMap();
        createBalls();
        createAura();
        collision();

        for(IController controller : controllerList)
            controller.onCreate();
    }

    @Override
    public void render() {
        camera.update();

        world.step(1f / 60f, 6, 2);

        batch.setProjectionMatrix(camera.combined);
        debugMatrix = batch.getProjectionMatrix().cpy().scale(SCALE, SCALE, 0);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        for(IController controller : controllerList)
            controller.onRender();

        world.setContactListener(new CollisionController(wallList, ballList));

        debugRenderer.render(world, debugMatrix);
    }
    public void createBalls() {
        FileHandle ballFileHandle = Gdx.files.internal("core/images/leftBall.png");
        texture = new Texture(ballFileHandle);

        aura = new Aura();
        ball = new Ball(-camera.viewportWidth/2, -camera.viewportHeight/2, aura,null);
        player = new Player(0, "", ball);
        player.getBall().getAura().setPosition(ball.getX(), ball.getY());
        player.setKeys(Input.Keys.A, Input.Keys.D, Input.Keys.W, Input.Keys.S, Input.Keys.ALT_LEFT);

        FileHandle ballFileHandle2 = Gdx.files.internal("core/images/rightBall.png");
        texture2 = new Texture(ballFileHandle2);

        aura2 = new Aura();
        ball2 = new Ball(camera.viewportWidth/2-100f, camera.viewportHeight/2-100f, aura2,null);
        player2 = new Player(0, "", ball2);
        player2.setKeys(Input.Keys.DPAD_LEFT, Input.Keys.DPAD_RIGHT, Input.Keys.DPAD_UP, Input.Keys.DPAD_DOWN, Input.Keys.SPACE);

        ballController = new BallController(player, batch, texture, world);
        ballController2 = new BallController(player2, batch, texture2, world);

        controllerList.add(ballController);
        controllerList.add(ballController2);
    }
    public void createMap() {
        mapController = new MapController(world, camera);
        controllerList.add(mapController);
    }
    public void createAura() {
        auraController = new AuraController(player, aura, ballController.getBody(),mapController.getBodyListPlayer1(), batch);
        auraController2 = new AuraController(player2, aura2, ballController2.getBody(), mapController.getBodyListPlayer2(), batch);
        controllerList.add(auraController);
        controllerList.add(auraController2);
    }
    private void createWalls() {
        FileHandle horizontalFileHandle = Gdx.files.internal("core/images/wallHorizontal.png");
        Texture horizontalTexture = new Texture(horizontalFileHandle);

        FileHandle verticalFileHandle = Gdx.files.internal("core/images/wallVertical.png");
        Texture verticalTexture = new Texture(verticalFileHandle);

        Tile downTile = new Tile(-camera.viewportWidth/2, -camera.viewportHeight/2);
        Tile upTile = new Tile(-camera.viewportWidth/2, camera.viewportHeight/2-horizontalTexture.getHeight());
        Tile leftTile = new Tile(-camera.viewportWidth/2, -camera.viewportHeight/2);
        Tile rightTile = new Tile(camera.viewportWidth/2-verticalTexture.getWidth(), -camera.viewportHeight/2);

        downWallController = new TileController(world, downTile, horizontalTexture, batch);
        upWallController = new TileController(world, upTile, horizontalTexture, batch);
        leftWallController = new TileController(world, leftTile, verticalTexture, batch);
        rightWallController = new TileController(world, rightTile, verticalTexture, batch);

        controllerList.add(downWallController);
        controllerList.add(upWallController);
        controllerList.add(leftWallController);
        controllerList.add(rightWallController);
    }
    private void collision() {
        wallList = new ArrayList<TileController>();
        ballList = new ArrayList<BallController>();

        wallList.add(leftWallController);
        wallList.add(rightWallController);
        wallList.add(downWallController);
        wallList.add(upWallController);

        ballList.add(ballController);
        ballList.add(ballController2);
    }
}
