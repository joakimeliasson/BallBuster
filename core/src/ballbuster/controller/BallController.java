package ballbuster.controller;

import ballbuster.model.Ball;
import ballbuster.model.Player;
import ballbuster.view.BallView;
import com.badlogic.gdx.Gdx;
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
    protected Ball ball;

    protected Body body;
    protected BallView ballView;

    protected SpriteBatch batch;

    private float ballSpeed;


    public BallController(Player player ,SpriteBatch batch, Texture texture, World world, Texture shieldTexture) {
        this.player = player;
        this.batch = batch;
        this.ball = player.getBall();
        ballView = new BallView();
        ballView.createBody(texture, player,world, shieldTexture);
        this.body = ballView.getBody();
        ballSpeed = ball.getSpeed();

    }


    private void increaseMana() {
        if(player.getBall().getMaximumMana() > player.getBall().getMana()) {
            player.getBall().changeMana(0.1f); //The amount of mana regained each tick
        }
    }


    @Override
    public boolean keyDown(int keycode) {
        ballSpeed = player.getBall().getSpeed();
        if (Gdx.input.isKeyPressed(player.getSpeedKey())&& player.getBall().getMana() >= 1) {
                player.getBall().setSpeed(ballSpeed*3); // The speed increase
                player.getBall().changeMana(-1); //How much mana that will drain each tick
        }
        if(Gdx.input.isKeyPressed(player.getLeftKey()))
            ballView.moveLeft(body.getPosition().x, body.getPosition().y);
        if(Gdx.input.isKeyPressed(player.getRightKey()))
            ballView.moveRight(body.getPosition().x, body.getPosition().y);
        if(Gdx.input.isKeyPressed(player.getUpKey()))
            ballView.moveUp(body.getPosition().x, body.getPosition().y);
        if(Gdx.input.isKeyPressed(player.getDownKey()))
            ballView.moveDown(body.getPosition().x, body.getPosition().y);

        player.getBall().setSpeed(ballSpeed); // Reset our changes to the ball speed
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
        increaseMana();

       // if(Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)) {
        keyDown(0);
        ballView.renderBall(batch);
        //}
        //else
        //batch.dispose();
    }

    public Body getBody() {
        return body;
    }
    public Ball getBall() {
        return ball;
    }
    public Player getPlayer() { return player; }
}
