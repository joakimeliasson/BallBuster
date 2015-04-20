package BallBuster.Model.Tile;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by Matthias on 2015-03-30.
 */

public class Tile {

    private float x;
    private float y;

    private Sprite sprite;
    private Body body;

    private final float SCALE = 100f;

    public Tile(float x, float y, World world, Texture texture){
        this.x = x;
        this.y = y;

        sprite = new Sprite(texture);

        sprite.setPosition(x, y);

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;

        bodyDef.position.set((sprite.getX() +sprite.getWidth()/2)/SCALE, (sprite.getY() + sprite.getHeight()/2)/SCALE);

        body = world.createBody(bodyDef);

        //Create the body as a box
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
    }

