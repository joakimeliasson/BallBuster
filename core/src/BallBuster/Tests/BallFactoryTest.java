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
        BallFactory ballFactory = new BallFactory(new Aura(), new Map(),null); //TODO Does it really need map? /Johan
        Ball ball = ballFactory.createBall();
        assertNotNull(ball);
    }
}
