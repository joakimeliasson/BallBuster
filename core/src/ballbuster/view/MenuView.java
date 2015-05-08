package ballbuster.view;

import ballbuster.controller.BallBuster;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by jacobth on 2015-05-08.
 */
public class MenuView implements ApplicationListener, InputProcessor{

    private Sprite background;
    private SpriteBatch batch;
    private Sprite playButton;
    private BallBuster ballBuster;

    @Override
    public void create() {
        batch = new SpriteBatch();

        FileHandle backFileHandle = Gdx.files.internal("core/images/background2.png");
        Texture backgroundTexture = new Texture(backFileHandle);

        background = new Sprite(backgroundTexture);

        FileHandle playFileHandle = Gdx.files.internal("core/images/play.png");
        Texture playTexture = new Texture(playFileHandle);
        playButton = new Sprite(playTexture);
        playButton.setPosition(Gdx.graphics.getWidth() / 2 - playTexture.getWidth() / 2, Gdx.graphics.getHeight() / 2 - playTexture.getHeight() / 2);
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render() {
        if(ballBuster==null) {
            batch.begin();
            batch.draw(background, 0, 0);
            batch.end();

            batch.begin();
            batch.draw(playButton, playButton.getX(), playButton.getY());
            batch.end();
        }

        if(ballBuster!=null)
        ballBuster.render();
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

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        int x1 = Gdx.input.getX();
        int y1 = Gdx.input.getY();

        Vector3 input = new Vector3(x1, y1, 0);

        if(playButton.getBoundingRectangle().contains(input.x, input.y)) {
            ballBuster = new BallBuster();
            ballBuster.create();
            System.out.println("Create BallBuster");
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
