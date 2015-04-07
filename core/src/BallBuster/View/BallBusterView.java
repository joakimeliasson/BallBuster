package BallBuster.View;

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
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

public class BallBusterView implements ApplicationListener {

    private SpriteBatch batch;
    private Texture ballTexture;
    private Sprite ball;
    private Sprite ballTwo;

    private float ballSpeed = 200.0f; // 100 pixels per second.
    private float ballX;
    private float ballY;

    private float ballTwoX;
    private float ballTwoY;

    private OrthographicCamera camera;
    private World world;

    //SCALE due to speed issues
    private final float SCALE = 100f;

    private Box2DDebugRenderer debugRenderer;

    @Override
    public void create() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280/SCALE, 720/SCALE);
        camera.update();

        //Creating box2d world with no gravity
        world = new World(new Vector2(0, 0), true);

        debugRenderer = new Box2DDebugRenderer();

        batch = new SpriteBatch();
        Gdx.gl.glClearColor(135/255f, 206/255f, 235/255f, 1);
        FileHandle ballFileHandle = Gdx.files.internal("core/images/ball.png");
        ballTexture = new Texture(ballFileHandle);
        ball = new Sprite(ballTexture, 0, 0, 32, 32);
        ballTwo = new Sprite(ballTexture, 0, 0, 32, 32);

        ballX = 0;
        ballY = 0;

        ballTwoX = 200;
        ballTwoY = 200;
    }

    @Override
    public void render() {
        world.step(1f/60f, 6, 2);

        camera.update();
        batch.setProjectionMatrix(camera.combined);
        debugRenderer.render(world,camera.combined);

        if(Gdx.input.isKeyPressed(Input.Keys.DPAD_LEFT))
            ballX -= Gdx.graphics.getDeltaTime() * ballSpeed;
        if(Gdx.input.isKeyPressed(Input.Keys.DPAD_RIGHT))
            ballX += Gdx.graphics.getDeltaTime() * ballSpeed;
        if(Gdx.input.isKeyPressed(Input.Keys.DPAD_UP))
            ballY += Gdx.graphics.getDeltaTime() * ballSpeed;
        if(Gdx.input.isKeyPressed(Input.Keys.DPAD_DOWN))
            ballY -= Gdx.graphics.getDeltaTime() * ballSpeed;

        if(Gdx.input.isKeyPressed(Input.Keys.A))
            ballTwoX -= Gdx.graphics.getDeltaTime() * ballSpeed;
        if(Gdx.input.isKeyPressed(Input.Keys.D))
            ballTwoX += Gdx.graphics.getDeltaTime() * ballSpeed;
        if(Gdx.input.isKeyPressed(Input.Keys.W))
            ballTwoY += Gdx.graphics.getDeltaTime() * ballSpeed;
        if(Gdx.input.isKeyPressed(Input.Keys.S))
            ballTwoY -= Gdx.graphics.getDeltaTime() * ballSpeed;

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(ball, (int) ballX, (int) ballY);
        batch.draw(ballTwo, (int) ballTwoX, (int) ballTwoY);
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
