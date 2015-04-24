package BallBuster.Controller;

import com.badlogic.gdx.physics.box2d.Body;

/**
 * Created by jacobth on 2015-04-24.
 */
public class BlockTileController {

    public BlockTileController() {

    }
    public void activateMagnet(Body blockBody, Body body) {
        float xDiff = blockBody.getPosition().x - body.getPosition().x;
        float yDiff = blockBody.getPosition().y - body.getPosition().y;
        float rad2 = xDiff*xDiff + yDiff*yDiff;
        double tmp = (double)rad2;

        if(Math.sqrt(tmp) < 20f) {

            body.getFixtureList().get(0).setRestitution(0f);
            body.applyLinearImpulse(((1f * xDiff) / rad2), (1f * yDiff) / rad2, body.getPosition().x, body.getPosition().y, true);
        }
    }
    public void resetRestitution(Body body) {
        body.getFixtureList().get(0).setRestitution(1f);
    }
}
