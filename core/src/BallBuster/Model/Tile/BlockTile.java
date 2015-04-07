package BallBuster.Model.Tile;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import java.awt.geom.RectangularShape;

/**
 * Created by Matthias on 2015-03-30.
 */

public class BlockTile extends Tile {

    private Sprite boxSprite;
    private World world;
    private PolygonShape box;
    private BodyDef boxDef;
    private Body body;

    public BlockTile(float x, float y, String color, World world, Texture texture) {
        super(x, y);

        this.world = world;

        boxSprite = new Sprite(texture);

        PolygonShape magnetShape = new PolygonShape();

        magnetShape.setAsBox(boxSprite.getWidth()/(200), boxSprite.getHeight()/(200));
        BodyDef bd = new BodyDef();
        bd.type = BodyDef.BodyType.StaticBody;
        bd.position.set(x,y);
        body = world.createBody(bd);
        body.createFixture(magnetShape, 0f);
        magnetShape.dispose();


    }

    public Sprite getSprite() {
        return boxSprite;
    }
    public float getX() {
        return body.getPosition().x;
    }
    public float getY() {
        return body.getPosition().y;
    }
    public float getWidth() {
        return boxSprite.getWidth()/100;
    }
    public float getHeight() {
        return boxSprite.getHeight()/100;
    }
    public void setPosition(float x, float y) {
        boxSprite.setPosition(x,y);
    }


}
