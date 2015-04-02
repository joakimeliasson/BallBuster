package BallBuster.Tests;

import BallBuster.Model.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Joakim on 2015-04-01.
 */
public class PlayerFactoryTest {

    @Test
    public void testAddPlayer(){
        PlayerFactory pf = new PlayerFactory();
        Player player = pf.addPlayer(1,"Player1", new Ball(new Aura(), new Map()));
        assertNotNull(player);
    }

    @Test
    public void testPlayerId(){
        PlayerFactory pf = new PlayerFactory();
        Player player = pf.addPlayer(1,"Player1", new Ball(new Aura(), new Map()));
        assertEquals(1,player.getPlayerId());
    }

    @Test
    public void testPlayerName(){
        PlayerFactory pf = new PlayerFactory();
        Player player = pf.addPlayer(1,"Player1", new Ball(new Aura(), new Map()));
        assertEquals("Player1", player.getPlayerName());
    }

    @Test
    public void testSetPlayerName(){
        PlayerFactory pf = new PlayerFactory();
        Player player = pf.addPlayer(1,"Player1", new Ball(new Aura(), new Map()));
        pf.setPlayerName(player,"Player2");
        assertEquals("Player2", player.getPlayerName());
    }

    @Test
    public void testPlayerBall(){
        PlayerFactory pf = new PlayerFactory();
        Player player = pf.addPlayer(1,"Player1", new Ball(new Aura(), new Map()));
        assertNotNull(player.getPlayerBall());
    }
}
