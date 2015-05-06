package ballbuster.view;

import ballbuster.model.tile.BlockTile;
import ballbuster.model.tile.Tile;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by jacobth on 2015-04-24.
 */
public class BlockTileView extends TileView{
    public BlockTileView(World world, BlockTile blockTile, Texture texture, SpriteBatch batch) {
        super(world, blockTile, texture, batch);
    }

    public BlockTileView(World world, Tile tile,float width, float height ){
        super(world,tile,width,height);
    }
}
