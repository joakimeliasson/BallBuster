package BallBuster.Model;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Jacob Lundberg on 2015-04-07.
 */
public class Wall {

    private PolygonShape box;
    private World world;
    private BodyDef wallDef;

    public Wall(World world) {
        this.world = world;

        box = new PolygonShape();

        wallDef =new BodyDef();
        //Making the body static
        wallDef.type = BodyDef.BodyType.StaticBody;
    }
    //Set position of wall and create definition

    public void renderWall(float x, float y, float width, float height) {
        wallDef.position.set(x, y);

        Body body = world.createBody(wallDef);
        box.setAsBox(width/2, height/2);

        body.createFixture(box, 0.0f);
        //box no longer needed
        box.dispose();
    }
}
