package BallBuster.View;

import BallBuster.Controller.BallController;
import BallBuster.Model.Ball;
import BallBuster.Model.Player;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by jacobth on 2015-04-23.
 */
public class BallView implements ApplicationListener, InputProcessor{
    private Sprite sprite;

    private Texture texture;

    private Body body;

    private Vector2 pos;

    private Ball ball;

    private World world;

    private BallController ballController;

    private SpriteBatch batch;

    private Player player;

    public BallView(World world, Player player, Texture texture, SpriteBatch batch) {
        this.world = world;
        this.player = player;
        this.texture = texture;
        this.batch = batch;
        this.ball = player.getBall();

    }
    public void setKeys(int leftKey, int rightKey, int upKey, int downKey) {
        player.setKeys(leftKey, rightKey, upKey, downKey);
    }

    @Override
    public void create() {
        sprite = new Sprite(texture);
        sprite.setPosition(ball.getX(), ball.getY());

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        bodyDef.position.set((sprite.getX() +sprite.getWidth()/2)/BallBusterView.SCALE, (sprite.getY() + sprite.getHeight()/2)/BallBusterView.SCALE);

        body = world.createBody(bodyDef);

        //Create the body as a circle
        CircleShape shape = new CircleShape();

        shape.setRadius(sprite.getWidth()/(2*BallBusterView.SCALE));

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

        ballController = new BallController();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render() {
        setPosition();
        keyDown(0);

        batch.begin();
        batch.draw(sprite, sprite.getX(), sprite.getY(), sprite.getOriginX(), sprite.getOriginY(),
                sprite.getWidth(), sprite.getHeight(), sprite.getScaleX(), sprite.getScaleY(), sprite.getRotation());
        batch.end();

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

    public Sprite getBallSprite() {
        return sprite;
    }
    public Body getBody() {
        return body;
    }
    public void setPosition() {
        ball.setPosition((body.getPosition().x*BallBusterView.SCALE)-sprite.getWidth()/2, (body.getPosition().y*BallBusterView.SCALE)-sprite.getHeight()/2);
        sprite.setPosition(ball.getX(),ball.getY());
    }
    public Ball getBall() {
        return ball;
    }
    public float getX() {
       return body.getPosition().x;
    }
    public float getY() {
        return body.getPosition().y;
    }

    @Override
    public boolean keyDown(int keycode) {

        if(Gdx.input.isKeyPressed(player.getLeftKey()))
            ballController.moveLeft(body, pos.x, pos.y);
        if(Gdx.input.isKeyPressed(player.getRightKey()))
            ballController.moveRight(body, pos.x, pos.y);
        if(Gdx.input.isKeyPressed(player.getUpKey()))
            ballController.moveUp(body, pos.x, pos.y);
        if(Gdx.input.isKeyPressed(player.getDownKey()))
            ballController.moveDown(body, pos.x, pos.y);
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
