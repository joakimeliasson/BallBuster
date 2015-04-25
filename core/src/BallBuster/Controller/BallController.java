package BallBuster.Controller;

import BallBuster.Model.Player;
import BallBuster.View.BallBusterView;
import BallBuster.View.BallView;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * Created by Jacob Lundberg on 2015-04-23.
 */
public class BallController implements InputProcessor{

    private Player player;
    private Body body;

    public BallController(Player player, Body body) {
        this.player = player;
        this.body = body;
    }

    private void moveRight(Body body, float x, float y) {
        body.applyLinearImpulse(0.10f, 0, x, y, true);
    }
    private void moveLeft(Body body, float x, float y) {
        body.applyLinearImpulse(-0.10f, 0, x, y, true);
    }
    private void moveUp(Body body, float x, float y) {
        body.applyLinearImpulse(0, 0.10f, x, y, true);
    }
    private void moveDown(Body body, float x, float y) {
        body.applyLinearImpulse(0, -0.10f, x, y, true);
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
}
