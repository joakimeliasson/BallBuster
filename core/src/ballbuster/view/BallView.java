package ballbuster.view;

import ballbuster.controller.BallBuster;
import ballbuster.model.Ball;
import ballbuster.model.Player;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by jacobth on 2015-04-23.
 */
public class BallView{
    private Sprite sprite;
    private Sprite shieldSprite;
    private Player player;
    private Body body;
    private Texture shieldTexture;

    public void createBody(Texture texture, Player player, World world, Texture shieldTexture) {
        sprite = new Sprite(texture);

        this.shieldTexture = shieldTexture;

        shieldSprite = new Sprite(shieldTexture);
        this.player = player;
        Ball ball = player.getBall();
        sprite.setPosition(ball.getX(), ball.getY());
        ball.setRadius(sprite.getWidth()/2);

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        bodyDef.position.set((sprite.getX() +sprite.getWidth()/2)/BallBuster.SCALE, (sprite.getY() + sprite.getHeight()/2)/BallBuster.SCALE);

        body = world.createBody(bodyDef);

        //Create the body as a circle
        CircleShape shape = new CircleShape();

        shape.setRadius(sprite.getWidth()/(2* BallBuster.SCALE));

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

    }
    public Body getBody() {
        return body;
    }
    public void renderBall(SpriteBatch batch) {

        float shield = (float)player.getBall().getShield()/100;
        if(shieldSprite.getWidth()>= sprite.getWidth())
        shieldSprite.setSize(shieldTexture.getWidth()*shield, shieldTexture.getHeight()*shield);

        batch.begin();
        batch.draw(sprite, sprite.getX(), sprite.getY(), sprite.getOriginX(), sprite.getOriginY(),
                sprite.getWidth(), sprite.getHeight(), sprite.getScaleX(), sprite.getScaleY(), sprite.getRotation());
        batch.draw(shieldSprite, shieldSprite.getX(), shieldSprite.getY(), shieldSprite.getOriginX(), shieldSprite.getOriginY(),
                shieldSprite.getWidth(), shieldSprite.getHeight(), shieldSprite.getScaleX(), shieldSprite.getScaleY(), shieldSprite.getRotation());
        batch.end();
    }

    public void setPosition(Ball ball) {
        ball.setBodyPosition(body.getPosition().x, body.getPosition().y);
        ball.setPosition(body.getPosition().x * BallBuster.SCALE - sprite.getWidth() / 2, body.getPosition().y * BallBuster.SCALE - sprite.getHeight() / 2);
        sprite.setPosition(ball.getX(),ball.getY());
        shieldSprite.setPosition(body.getPosition().x* BallBuster.SCALE-shieldSprite.getWidth()/2, body.getPosition().y*BallBuster.SCALE-shieldSprite.getHeight()/2);
    }
    public float getX() {
       return body.getPosition().x;
    }
    public float getY() {
        return body.getPosition().y;
    }

    public void moveRight(float x, float y) {
        body.applyLinearImpulse(player.getBall().getSpeed(), 0, x, y, true);
    }
    public void moveLeft(float x, float y) {
        body.applyLinearImpulse(-player.getBall().getSpeed(), 0, x, y, true);
    }
    public void moveUp(float x, float y) {
        body.applyLinearImpulse(0, player.getBall().getSpeed(), x, y, true);
    }
    public void moveDown(float x, float y) {
        body.applyLinearImpulse(0, -player.getBall().getSpeed(), x, y, true);
    }
}
