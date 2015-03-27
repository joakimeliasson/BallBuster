package BallBuster.View;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

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

    @Override
    public void create() {
        batch = new SpriteBatch();
        Gdx.gl.glClearColor(135/255f, 206/255f, 235/255f, 1);
        FileHandle ballFileHandle = Gdx.files.internal("C:/Users/jacobth/Downloads/test/android/assets/ball.png");
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
