package BallBuster.Tests;

import BallBuster.Model.Aura;
import BallBuster.Model.Ball;
import BallBuster.Model.Map;
import BallBuster.Model.PlayerFactory;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Joakim on 2015-04-01.
 */
public class PlayerFactoryTest {

    @Test
    public void testAddPlayer(){
        PlayerFactory pf = new PlayerFactory();
        assertNotNull(pf.addPlayer(1, "Player1", new Ball(new Aura(), new Map())));
    }
}
