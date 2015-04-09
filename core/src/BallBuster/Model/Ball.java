package BallBuster.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import java.awt.*;

/**
 * Created by Jacob Lundberg on 2015-03-30.
 */
public class Ball {

    private int shield;

    private Texture ballTexture;
    private Texture shieldTexture;

    private Aura aura;
    private Map map;
    private Point position;

    private Sprite sprite;
    private Sprite shieldSprite;

    private Body body;

    private Vector2 pos;

    private final float SCALE = 100f;

    public Ball(Aura aura, Map map, World world) {
        this.aura = aura;
        this.map = map;

        shield = 100;

        FileHandle ballFileHandle = Gdx.files.internal("core/images/ball.png");
        ballTexture = new Texture(ballFileHandle);
        sprite = new Sprite(ballTexture);
        sprite.setPosition(-sprite.getWidth()/2, -sprite.getHeight()/2);

        FileHandle shieldFileHandle = Gdx.files.internal("core/images/shield.png");
        shieldTexture = new Texture(shieldFileHandle);
        shieldSprite = new Sprite(shieldTexture);

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        bodyDef.position.set((sprite.getX() +sprite.getWidth()/2)/SCALE, (sprite.getY() + sprite.getHeight()/2)/SCALE);

        body = world.createBody(bodyDef);

        //Create the body as a circle
        CircleShape shape = new CircleShape();

        shape.setRadius(sprite.getWidth()/(2*SCALE));

        //Set physical attributes to the body
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 7f;
        fixtureDef.friction = 1f;
        //Make the ball bounce on other bodies
        fixtureDef.restitution = 1f;

        body.createFixture(fixtureDef);

        //Make the body still when no acceleration are applied
        body.setLinearDamping(1f);
        shape.dispose();

        pos = body.getPosition();

    }

    public void moveRight() {
        body.applyLinearImpulse(0.10f, 0, pos.x, pos.y, true);
    }
    public void moveLeft() {
        body.applyLinearImpulse(-0.10f, 0, pos.x, pos.y, true);
    }
    public void moveUp() {
        body.applyLinearImpulse(0, 0.10f, pos.x, pos.y, true);
    }
    public void moveDown() {
        body.applyLinearImpulse(0, -0.10f, pos.x, pos.y, true);
    }

    public int shieldDamage() {
        return 0;
    }

    public Sprite getBallSprite() {
        return sprite;
    }
    public Sprite getShieldSprite() {
        return shieldSprite;
    }
    public Body getBody() {
        return body;
    }
    public void setPosition() {
        sprite.setPosition((body.getPosition().x*SCALE)-sprite.getWidth()/2, (body.getPosition().y*SCALE)-sprite.getHeight()/2);
        //sprite.setRotation((float)Math.toDegrees(body.getAngle()));
    }

}
