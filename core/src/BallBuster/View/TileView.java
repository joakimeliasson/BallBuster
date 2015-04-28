package BallBuster.View;

import BallBuster.Model.Tile.Tile;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by Jacob Lundberg on 2015-04-24.
 */
public class TileView implements ApplicationListener{

    private Sprite sprite;
    private World world;
    private Tile tile;
    private Texture texture;
    private SpriteBatch batch;
    private Body body;

    public TileView(World world, Tile tile, Texture texture, SpriteBatch batch) {
        this.world = world;
        this.tile = tile;
        this.texture = texture;
        this.batch = batch;
    }

    @Override
    public void create() {
        sprite = new Sprite(texture);
        sprite.setPosition(tile.getX(), tile.getY());

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;

        bodyDef.position.set((sprite.getX() +sprite.getWidth()/2)/BallBusterView.SCALE, (sprite.getY() + sprite.getHeight()/2)/BallBusterView.SCALE);

        body = world.createBody(bodyDef);

        //Create the body as a box
        PolygonShape shape = new PolygonShape();

        shape.setAsBox(sprite.getWidth()/2 / BallBusterView.SCALE, sprite.getHeight()/2 / BallBusterView.SCALE);

        //Set physical attributes to the body
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 7f;
        fixtureDef.friction = 1f;

        body.createFixture(fixtureDef);

        //Make the body still when no acceleration are applied
        shape.dispose();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render() {
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
    public Body getBody() {
        return body;
    }
}
