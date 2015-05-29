package ballbuster.view;

import ballbuster.model.Tile;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by jacobth on 2015-04-24.
 */
public class BlockTileView extends TileView {
    public BlockTileView(World world, Tile tile, float width, float height) {
        super(world, tile, width, height);
    }
}
