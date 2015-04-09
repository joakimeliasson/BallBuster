package BallBuster.Model.Tile;

import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by Matthias on 2015-03-30.
 */
public class WallTile extends Tile {

    private World world;
    private BodyDef wallDef;
    private Body body;

    public WallTile(float x, float y, World world) {
        super(x, y);
        this.world = world;


    }
    //Set position of wall and create definition

    public void renderWall(float width, float height) {
        //wallDef.position.set(super.getX(), super.getY());
        wallDef =new BodyDef();
        //Making the body static
        wallDef.type = BodyDef.BodyType.StaticBody;
        wallDef.position.set(super.getX(), super.getY());

        FixtureDef def = new FixtureDef();

        EdgeShape shape = new EdgeShape();
        shape.set(-width/2,-height/2,width/2,-height/2);
        def.shape = shape;
        body = world.createBody(wallDef);

        body.createFixture(def);
        //box no longer needed
        shape.dispose();
        }
    }


