package ballbuster.tests;

/**
 * Created by jacobth on 2015-03-30.
 */
import ballbuster.model.Aura;
import ballbuster.model.Ball;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


public class BallTest {

    private Ball ball;

    @Before
    public void setUp() throws Exception {
        //ball = new Ball(4f, 5f, new Aura);
        ball = new Ball(4f, 5f);
    }

    @Test
    public void testCreateBall() {
        assertNotNull(ball);
    }
    @Test
    public void testGetX() {
        assertTrue(ball.getX() == 4f);
    }
    @Test
    public void testGetY() {
        assertTrue(ball.getY() == 5f);
    }
    @Test
    public void testSetPosition() {
        ball.setPosition(1f,2f);
        assertTrue(ball.getX() == 1f);
        assertTrue(ball.getY() == 2f);
    }
    @Test
    public void testGetShield() {
        int tmp = (int)ball.getShield();
        assertEquals(100, tmp);
    }
    @Test
    public void testDamage() {
        ball.shieldDamage(10);
        int tmp = (int)ball.getShield();
        assertEquals(90, tmp);
    }
    @Test
    public void testGetX2() {
        ball.setBodyPosition(3f,4f);
        assertTrue(ball.getX2() == 3f);
    }
    @Test
    public void testGetY2() {
        ball.setBodyPosition(3f,4f);
        assertTrue(ball.getY2() == 4f);
    }


}
