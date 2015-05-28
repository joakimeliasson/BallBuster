package ballbuster.tests;

import ballbuster.model.Map;
import ballbuster.model.Tile;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Johan Segerlund on 2015-05-28.
 */
public class MapTest {

    private Map map;

    @Before
    public void setUp() throws Exception {
        map = new Map();
    }

    @Test
    public void testAddTile(){
        Tile tile = new Tile(0f,1f);
        map.addTile(tile);
        assertNotNull(map.getTiles());
    }
}
