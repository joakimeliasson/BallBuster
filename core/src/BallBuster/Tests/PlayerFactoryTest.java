package BallBuster.Tests;

import BallBuster.Model.*;
import org.junit.Test;

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
}
