package BallBuster.Controller;

import BallBuster.Model.Ball;
import BallBuster.Model.Player;
import BallBuster.View.BallView;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
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

    private Texture texture;
    private World world;

    public BallController(Player player ,SpriteBatch batch, Texture texture, World world) {
        this.player = player;
        this.batch = batch;
        this.ball = player.getBall();
        ballView = new BallView();
        this.texture = texture;
        this.world = world;
        ballView.createBody(texture, ball,world);
        this.body = ballView.getBody();
    }

    private void moveRight(Body body, float x, float y) {
        body.applyLinearImpulse(player.getBall().getSpeed(), 0, x, y, true);
    }
    private void moveLeft(Body body, float x, float y) {
        body.applyLinearImpulse(-player.getBall().getSpeed(), 0, x, y, true);
    }
    private void moveUp(Body body, float x, float y) {
        body.applyLinearImpulse(0, player.getBall().getSpeed(), x, y, true);
    }
    private void moveDown(Body body, float x, float y) {
        body.applyLinearImpulse(0, -player.getBall().getSpeed(), x, y, true);
    }

    @Override
    public boolean keyDown(int keycode) {
        if(Gdx.input.isKeyPressed(player.getLeftKey()))
            moveLeft(body, body.getPosition().x, body.getPosition().y);
        if(Gdx.input.isKeyPressed(player.getRightKey()))
            moveRight(body, body.getPosition().x, body.getPosition().y);
        if(Gdx.input.isKeyPressed(player.getUpKey()))
            moveUp(body,body.getPosition().x, body.getPosition().y);
        if(Gdx.input.isKeyPressed(player.getDownKey()))
            moveDown(body, body.getPosition().x, body.getPosition().y);
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
}
