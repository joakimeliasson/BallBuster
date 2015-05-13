package ballbuster.tests;

import ballbuster.model.Aura;
import ballbuster.model.Ball;
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
        p = new Player(1,"Player1", new Ball(1f,1f,new Aura()));
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
        p.setKeys(1,2,3,4,5);
        assertEquals(1, p.getLeftKey());
        assertEquals(2, p.getRightKey());
        assertEquals(3, p.getUpKey());
        assertEquals(4, p.getDownKey());
        assertEquals(5, p.getAuraKey());
    }

    @Test
    public void testGetAndSetPlayerPowerUp(){
        p.setPlayerPowerUp("test");
        assertEquals("test", p.getPlayerPowerUp());
    }

    @Test
    public void testPlayerHasPowerUp(){
        p.setPlayerPowerUp("test");
        assertTrue(p.playerHasPowerUp());
    }


}

