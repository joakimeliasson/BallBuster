package BallBuster.View;

import BallBuster.Model.Ball;
import BallBuster.Model.Tile.BlockTile;
import BallBuster.Model.Wall;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class BallBusterView implements ApplicationListener {

    private SpriteBatch batch;

    private OrthographicCamera camera;
    private World world;

    //SCALE due to speed issues
    private final float SCALE = 100f;

    private Box2DDebugRenderer debugRenderer;

    private Ball ball;
    private Ball ball2;

    private BlockTile magnet;

    @Override
    public void create() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280/SCALE, 720/SCALE);
        camera.update();

        Gdx.gl.glClearColor(1, 1, 1, 1);

        //Creating box2d world with no gravity
        world = new World(new Vector2(0, 0), true);

        debugRenderer = new Box2DDebugRenderer();

        batch = new SpriteBatch();

        ball = new Ball(null,null, world);
        ball2 = new Ball(null,null,world);

        FileHandle boxFileHandle = Gdx.files.internal("core/images/normal.png");
        Texture boxTexture = new Texture(boxFileHandle);

        magnet = new BlockTile(camera.viewportWidth/2,camera.viewportHeight/2,null,world, boxTexture);

        Wall groundWall = new Wall(world);
        groundWall.renderWall(0,0,camera.viewportWidth,0);

        Wall upperWall = new Wall(world);
        upperWall.renderWall(0,camera.viewportHeight,camera.viewportWidth,0);

        Wall leftWall = new Wall(world);
        leftWall.renderWall(0,0,0,camera.viewportHeight);

        Wall rightWall = new Wall(world);
        rightWall.renderWall(camera.viewportWidth,0,0,camera.viewportHeight);
    }

    @Override
    public void render() {
        world.step(1f/60f, 6, 2);

        camera.update();
        batch.setProjectionMatrix(camera.combined);
        debugRenderer.render(world,camera.combined);

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
        batch.draw(ball.getBallSprite(), ball.getBodyPosition().x, ball.getBodyPosition().y, ball.getBallSprite().getWidth() / SCALE, ball.getBallSprite().getHeight() / SCALE);
        batch.draw(ball2.getBallSprite(),ball2.getBodyPosition().x, ball2.getBodyPosition().y,ball2.getBallSprite().getWidth()/SCALE,ball2.getBallSprite().getHeight()/SCALE);
        batch.draw(magnet.getSprite(), magnet.getX(),magnet.getY(),magnet.getWidth(),magnet.getHeight());
        batch.end();

    }


    @Override
    public void resume() {
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void dispose() {
    }
}
