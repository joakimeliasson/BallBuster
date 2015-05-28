package ballbuster.tests;

import ballbuster.model.Player;
import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


/**
 * Created by Joakim on 2015-03-30.
*/
public class PlayerTest {
    private Player p;
    @Before
    public void setUpPlayerTest(){
        p = new Player(1,"Player1",1f,1f);
    }
    @Test
    public void testAddPlayer() {
        assertNotNull(p);
    }

    @Test
    public void testPlayerId(){
        assertEquals(1, p.getPlayerId());
    }

    @Test
    public void testGetPlayerName(){
        assertEquals("Player1", p.getPlayerName());
    }

    @Test
    public void testSetPlayerName(){
        p.setPlayerName("Player2");
        assertEquals("Player2", p.getPlayerName());
    }

    @Test
    public void testPlayerHasBall(){
        assertNotNull(p.getBall());
    }

    @Test
    public void testPlayerKeys(){
        p.setKeys(1,2,3,4,5,6);
        assertEquals(1, p.getUpKey());
        assertEquals(2, p.getLeftKey());
        assertEquals(3, p.getDownKey());
        assertEquals(4, p.getRightKey());
        assertEquals(5, p.getAuraKey());
        assertEquals(6, p.getSpeedKey());
    }


    @Test
    public void testPlayerSpeedUp() {
        p.setSpeedUp(true);
        assertTrue(p.hasSpeedUp());
    }


}

