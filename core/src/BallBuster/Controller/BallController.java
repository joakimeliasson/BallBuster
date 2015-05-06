package ballBuster.controller;

import ballBuster.model.Ball;
import ballBuster.model.Player;
import ballBuster.view.BallView;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Jacob Lundberg on 2015-04-23.
 */
public class BallController implements InputProcessor, IController{

    private Player player;
    private Ball ball;

    private Body body;
    private BallView ballView;

    private SpriteBatch batch;

    public BallController(Player player ,SpriteBatch batch, Texture texture, World world) {
        this.player = player;
        this.batch = batch;
        this.ball = player.getBall();
        ballView = new BallView();
        ballView.createBody(texture, player,world);
        this.body = ballView.getBody();
    }


    @Override
    public boolean keyDown(int keycode) {
        if(Gdx.input.isKeyPressed(player.getLeftKey()))
            ballView.moveLeft(body.getPosition().x, body.getPosition().y);
        if(Gdx.input.isKeyPressed(player.getRightKey()))
            ballView.moveRight(body.getPosition().x, body.getPosition().y);
        if(Gdx.input.isKeyPressed(player.getUpKey()))
            ballView.moveUp(body.getPosition().x, body.getPosition().y);
        if(Gdx.input.isKeyPressed(player.getDownKey()))
            ballView.moveDown(body.getPosition().x, body.getPosition().y);
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

    @Override
    public void onCreate() {
    }

    @Override
    public void onRender() {
        ballView.setPosition(ball);
        keyDown(0);
        ballView.renderBall(batch);
    }
    public Body getBody() {
        return body;
    }
    public Ball getBall() {
        return ball;
    }
    public Player getPlayer() { return player; }
    public Sprite getBallSprite() {
        return ballView.getSprite();
    }
}
