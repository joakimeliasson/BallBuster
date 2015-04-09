package BallBuster.Tests;

import BallBuster.Model.Ball;
import BallBuster.Model.Player;
import BallBuster.Model.Aura;
import BallBuster.Model.Map;
import BallBuster.Model.PlayerFactory;
import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


/**
 * Created by Joakim on 2015-03-30.
*/
public class PlayerTest {

    @Test
    public void testAddPlayer() {
        assertNotNull(new Player(1,"Player1", new Ball(1f,1f,new Aura(), new Map(),null, null)));
    }

    @Test
    public void testPlayerId(){
        Player p = new Player(1, "Player1", new Ball(1f,1f,new Aura(), new Map(),null, null));
        assertEquals(1,(p.getPlayerId()));
    }

    @Test
    public void testGetPlayerName(){
        Player p = new Player(1, "Player1", new Ball(1f,1f,new Aura(), new Map(),null, null));
        assertEquals("Player1", p.getPlayerName());
    }

    @Test
    public void testSetPlayerName(){
        Player p = new Player(1, "Player1", new Ball(1f,1f,new Aura(), new Map(),null, null));
        p.setPlayerName("Player2");
        assertEquals("Player2", p.getPlayerName());
    }

    @Test
    public void testPlayerHasBall(){
        Player p = new Player(1, "Player1", new Ball(1f,1f,new Aura(), new Map(),null, null));
        assertNotNull(p.getPlayerBall());
    }

}

