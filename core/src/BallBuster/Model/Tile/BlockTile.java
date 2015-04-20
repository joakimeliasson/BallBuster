package BallBuster.Model.Tile;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;

import java.awt.geom.RectangularShape;

/**
 * Created by Matthias on 2015-03-30.
 */

public class BlockTile extends Tile {

    private final float SCALE = 100f;

    public BlockTile(float x, float y, World world, Texture texture) {
        super(x, y, world, texture);
    }

    public void activateMagnet(Body body) {
        float xDiff = super.body.getPosition().x - body.getPosition().x;
        float yDiff = super.body.getPosition().y - body.getPosition().y;
        float rad2 = xDiff*xDiff + yDiff*yDiff;
        double tmp = (double)rad2;

        if(Math.sqrt(tmp) < 2f) {
            body.getFixtureList().get(0).setRestitution(0f);
            body.applyLinearImpulse(((2f * xDiff) / rad2), (2f * yDiff) / rad2, body.getPosition().x, body.getPosition().y, true);
        }
    }
    public void resetRestitution(Body body) {
        body.getFixtureList().get(0).setRestitution(1f);
    }


}
