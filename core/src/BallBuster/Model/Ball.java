package BallBuster.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;

import java.awt.*;

/**
 * Created by Jacob Lundberg on 2015-03-30.
 */
public class Ball {

    private int shield;
    private int ballSpeed;

    private Texture ballTexture;
    private Texture shieldTexture;

    private Aura aura;
    private Map map;
    private Point position;

    private Sprite sprite;
    private Sprite shieldSprite;

    private Body body;

    private final float SCALE = 100;

    public Ball(Aura aura, Map map, World world) {
        this.aura = aura;
        this.map = map;

        shield = 100;
        position = new Point(0,0);
        ballSpeed = 0;

        FileHandle ballFileHandle = Gdx.files.internal("core/images/ball.png");
        ballTexture = new Texture(ballFileHandle);
        sprite = new Sprite(ballTexture,position.x,position.y,ballTexture.getWidth(),ballTexture.getHeight());

        FileHandle shieldFileHandle = Gdx.files.internal("core/images/shield.png");
        shieldTexture = new Texture(shieldFileHandle);
        shieldSprite = new Sprite(shieldTexture,position.x,position.y,shieldTexture.getWidth(),shieldTexture.getHeight());

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        bodyDef.position.set(sprite.getX(), sprite.getY());

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
    }

    public void moveRight() {
        position.x += Gdx.graphics.getDeltaTime() * ballSpeed;
    }
    public void moveLeft() {
        position.x -= Gdx.graphics.getDeltaTime() * ballSpeed;
    }
    public void moveUp() {
        position.y += Gdx.graphics.getDeltaTime() * ballSpeed;
    }
    public void moveDown() {
        position.y -= Gdx.graphics.getDeltaTime() * ballSpeed;
    }

    public void accelerateBall() {
        ballSpeed++;
    }

    public void slowBall() {
        ballSpeed--;
    }

    public void changePosition(int x, int y) {
        position.x = x;
        position.y = y;
    }


    public int shieldDamage() {

        return 0;
    }
    public int getBallSpeed() {
        return ballSpeed;
    }
    public int getX() {
        return position.x;
    }
    public int getY() {
        return position.y;
    }

    public Sprite getBallSprite() {
        return ball;
    }
    public Sprite getShieldSprite() {
        return shieldSprite;
    }

}
