package BallBuster.Model.Tile;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Matthias on 2015-03-30.
 */
public class WallTile extends Tile {

    private PolygonShape box;
    private World world;
    private BodyDef wallDef;

    public WallTile(float x, float y, World world) {
        super(x, y);
        this.world = world;

        box = new PolygonShape();

        wallDef =new BodyDef();
        //Making the body static
        wallDef.type = BodyDef.BodyType.StaticBody;
    }
    //Set position of wall and create definition

    public void renderWall(float width, float height) {
        wallDef.position.set(super.getX(), super.getY());

        Body body = world.createBody(wallDef);
        box.setAsBox(width, height);

        body.createFixture(box, 0.0f);
        //box no longer needed
        box.dispose();
        }
    }


