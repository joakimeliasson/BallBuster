package BallBuster.Controller;

import BallBuster.View.BallBusterView;
import BallBuster.View.BallView;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * Created by Jacob Lundberg on 2015-04-23.
 */
public class BallController {


    public BallController() {
    }

    public void moveRight(Body body, float x, float y) {
        body.applyLinearImpulse(0.10f, 0, x, y, true);
    }
    public void moveLeft(Body body, float x, float y) {
        body.applyLinearImpulse(-0.10f, 0, x, y, true);
    }
    public void moveUp(Body body, float x, float y) {
        body.applyLinearImpulse(0, 0.10f, x, y, true);
    }
    public void moveDown(Body body, float x, float y) {
        body.applyLinearImpulse(0, -0.10f, x, y, true);
    }
}
