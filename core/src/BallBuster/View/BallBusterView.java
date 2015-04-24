package BallBuster.View;

import BallBuster.Controller.BallController;
import BallBuster.Controller.CollisionController;
import BallBuster.Controller.GameController;
import BallBuster.Model.Ball;
import BallBuster.Model.Player;
import com.badlogic.gdx.*;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;


public class BallBusterView extends Game {
    private OrthographicCamera camera;
    private World world;

    public static final float SCALE = 100f;

    private Box2DDebugRenderer debugRenderer;
    private Matrix4 debugMatrix;

    private SpriteBatch batch;

    private BallView ballView;
    private BallView ballView2;

    private TileView leftWall;
    private TileView rightWall;
    private TileView upWall;
    private TileView downWall;

    private Ball ball;
    private Texture texture;
    private Player player;

    private Ball ball2;
    private Texture texture2;
    private Player player2;

    private ArrayList<ApplicationListener> viewList;

    @Override
    public void create() {
        world = new World(new Vector2(0, 0), true);

        debugRenderer = new Box2DDebugRenderer();

        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        Gdx.gl.glClearColor(106f/255f, 165f/255f, 255f/255f, 1f);

        batch = new SpriteBatch();

        FileHandle ballFileHandle = Gdx.files.internal("core/images/leftBall.png");
        texture = new Texture(ballFileHandle);

        ball = new Ball(-camera.viewportWidth/2, -camera.viewportHeight/2, null,null);
        player = new Player(0, "", ball);

        ballView = new BallView(world, player,texture, batch);
        ballView.setKeys(Input.Keys.A, Input.Keys.D, Input.Keys.W, Input.Keys.S);

        FileHandle ballFileHandle2 = Gdx.files.internal("core/images/rightBall.png");
        texture2 = new Texture(ballFileHandle2);

        ball2 = new Ball(camera.viewportWidth/2-100f, camera.viewportHeight/2-100f, null,null);
        player2 = new Player(0, "", ball2);

        ballView2 = new BallView(world, player2,texture2, batch);
        ballView2.setKeys(Input.Keys.DPAD_LEFT, Input.Keys.DPAD_RIGHT, Input.Keys.DPAD_UP, Input.Keys.DPAD_DOWN);

        viewList = new ArrayList<ApplicationListener>();
        viewList.add(ballView);
        viewList.add(ballView2);

        for(ApplicationListener listener : viewList)
            listener.create();
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void resume() {
        super.resume();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }


    @Override
    public void render() {
        camera.update();

        world.step(1f / 60f, 6, 2);

        batch.setProjectionMatrix(camera.combined);
        debugMatrix = batch.getProjectionMatrix().cpy().scale(SCALE, SCALE, 0);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        for(ApplicationListener listener : viewList)
            listener.render();

        debugRenderer.render(world,debugMatrix);
    }
}
