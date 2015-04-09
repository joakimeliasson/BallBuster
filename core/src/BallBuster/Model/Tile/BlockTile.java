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

    private Sprite sprite;
    private Body body;

    private final float SCALE = 100f;

    public BlockTile(float x, float y, World world, Texture texture) {
        super(x, y);

        sprite = new Sprite(texture);

        sprite.setPosition(-sprite.getWidth()/2, -sprite.getHeight()/2);

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;

        bodyDef.position.set((sprite.getX() +sprite.getWidth()/2)/SCALE, (sprite.getY() + sprite.getHeight()/2)/SCALE);

        body = world.createBody(bodyDef);

        //Create the body as a circle
        PolygonShape shape = new PolygonShape();

        shape.setAsBox(sprite.getWidth()/2 /SCALE, sprite.getHeight()/2 /SCALE);

        //Set physical attributes to the body
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 7f;
        fixtureDef.friction = 1f;

        body.createFixture(fixtureDef);

        //Make the body still when no acceleration are applied
        shape.dispose();


    }

    public Sprite getSprite() {
        return sprite;
    }
    public float getX() {
        return body.getPosition().x;
    }
    public float getY() {
        return body.getPosition().y;
    }
    public void activateMagnet(Body body) {
        float xDiff = this.body.getPosition().x - body.getPosition().x;
        float yDiff = this.body.getPosition().y - body.getPosition().y;
        float rad2 = xDiff*xDiff + yDiff*yDiff;

        body.getFixtureList().get(0).setRestitution(0f);
        body.applyLinearImpulse(((2f * xDiff)/rad2), (2f * yDiff) / rad2, body.getPosition().x, body.getPosition().y,true);
    }
    public void resetRestitution(Body body) {
        body.getFixtureList().get(0).setRestitution(1f);
    }


}
