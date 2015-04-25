package BallBuster.Tests;

import BallBuster.Model.Aura;
import BallBuster.Model.Tile.Tile;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by jacobth on 2015-04-25.
 */
public class TileTest {

    private Tile tile;

    @Before
    public void setUp() throws Exception {
        tile = new Tile(2f, 3f);
    }
    @Test
    public void testGetX() {
        assertTrue(tile.getX() == 2f);
    }
    @Test
    public void testGetY() {
        assertTrue(tile.getY() == 3f);
    }
    @Test
    public void testSetPosition() {
        tile.setPosition(1f, 2f);
        assertTrue(tile.getY() == 2f && tile.getX() == 1f);
    }
}
