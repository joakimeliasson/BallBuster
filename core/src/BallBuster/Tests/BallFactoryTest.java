package BallBuster.Tests;

import BallBuster.Model.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * Created by jacobth on 2015-03-31.
 */
public class BallFactoryTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testCreateBall() {
        BallFactory ballFactory = new BallFactory(new Player(), new Aura(), new Map());
        Ball ball = ballFactory.createBall();
        assertNotNull(ball);
    }
}
