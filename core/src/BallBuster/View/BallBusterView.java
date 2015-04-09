package BallBuster.View;

import BallBuster.Model.Ball;
import BallBuster.Model.Tile.BlockTile;
import BallBuster.Model.Tile.WallTile;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class BallBusterView extends ApplicationAdapter {

    private SpriteBatch batch;

    private OrthographicCamera camera;
    private World world;

    //SCALE due to speed issues
    private final float SCALE = 100f;

    private Box2DDebugRenderer debugRenderer;
    private Matrix4 debugMatrix;

    private Ball ball;
    private Ball ball2;

    private BlockTile magnet;

    @Override
    public void create() {

        world = new World(new Vector2(0, 0), true);

        debugRenderer = new Box2DDebugRenderer();

        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        Gdx.gl.glClearColor(1, 1, 1, 1);

        //Creating box2d world with no gravity




        batch = new SpriteBatch();

        ball = new Ball(null,null, world);
        ball2 = new Ball(null,null,world);

        FileHandle boxFileHandle = Gdx.files.internal("core/images/normal.png");
        Texture boxTexture = new Texture(boxFileHandle);

        magnet = new BlockTile(camera.viewportWidth/2, camera.viewportHeight/2, world, boxTexture);

        WallTile groundWall = new WallTile(0, 0, world);
        groundWall.renderWall(Gdx.graphics.getWidth()/SCALE, Gdx.graphics.getHeight()/SCALE);

        //WallTile upperWall = new WallTile(0,Gdx.graphics.getHeight()/SCALE, world);
        //upperWall.renderWall(Gdx.graphics.getWidth()/SCALE,0);

        //WallTile leftWall = new WallTile(0,0,world);
       // leftWall.renderWall(0, Gdx.graphics.getHeight()/SCALE);

      //  WallTile rightWall = new WallTile(Gdx.graphics.getWidth(),0, world);
      //  rightWall.renderWall(0, Gdx.graphics.getHeight()/SCALE);
    }

    @Override
    public void render() {
        camera.update();

        world.step(1f / 60f, 6, 2);

        ball.setPosition();
        ball2.setPosition();

        batch.setProjectionMatrix(camera.combined);
        debugMatrix = batch.getProjectionMatrix().cpy().scale(SCALE, SCALE, 0);

        if(Gdx.input.isKeyPressed(Input.Keys.DPAD_LEFT))
            ball2.moveLeft();
        if(Gdx.input.isKeyPressed(Input.Keys.DPAD_RIGHT))
            ball2.moveRight();
        if(Gdx.input.isKeyPressed(Input.Keys.DPAD_UP))
            ball2.moveUp();
        if(Gdx.input.isKeyPressed(Input.Keys.DPAD_DOWN))
            ball2.moveDown();
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE))
            magnet.activateMagnet(ball2.getBody());
        else
            magnet.resetRestitution(ball2.getBody());

        if(Gdx.input.isKeyPressed(Input.Keys.A))
            ball.moveLeft();
        if(Gdx.input.isKeyPressed(Input.Keys.D))
            ball.moveRight();
        if(Gdx.input.isKeyPressed(Input.Keys.W))
            ball.moveUp();
        if(Gdx.input.isKeyPressed(Input.Keys.S))
            ball.moveDown();
        if(Gdx.input.isKeyPressed(Input.Keys.ALT_LEFT))
            magnet.activateMagnet(ball.getBody());
        else
            magnet.resetRestitution(ball.getBody());

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(ball.getBallSprite(), ball.getBallSprite().getX(), ball.getBallSprite().getY(), ball.getBallSprite().getOriginX(), ball.getBallSprite().getOriginY(),ball.getBallSprite().getWidth(),ball.getBallSprite().getHeight(),ball.getBallSprite().getScaleX(),ball.getBallSprite().getScaleY(),ball.getBallSprite().getRotation());
        batch.draw(ball2.getBallSprite(), ball2.getBallSprite().getX(), ball2.getBallSprite().getY(), ball2.getBallSprite().getOriginX(), ball2.getBallSprite().getOriginY(),ball2.getBallSprite().getWidth(),ball2.getBallSprite().getHeight(),ball2.getBallSprite().getScaleX(),ball2.getBallSprite().getScaleY(),ball2.getBallSprite().getRotation());
        //batch.draw(magnet.getSprite(), magnet.getX(),magnet.getY(),magnet.getWidth(),magnet.getHeight());
        batch.end();

        debugRenderer.render(world,debugMatrix);

    }
}
