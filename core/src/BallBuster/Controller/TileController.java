package BallBuster.Controller;

import BallBuster.Model.Tile.Tile;
import BallBuster.View.TileView;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Jacob Lundberg on 2015-04-24.
 */
public class TileController implements IController{

    private TileView tileView;

    public TileController(World world, Tile tile, Texture texture, SpriteBatch batch) {
        tileView = new TileView(world, tile, texture, batch);
    }
    public TileController(World world, Tile tile,float width, float height) {
        tileView = new TileView(world, tile, width, height);
    }
    @Override
    public void onCreate() {
        tileView.createBody();
    }

    @Override
    public void onRender() {
        tileView.renderBody();
    }
    public Body getBody() {
        return tileView.getBody();
    }
}
