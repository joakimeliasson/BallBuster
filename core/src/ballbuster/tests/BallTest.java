package ballbuster.tests;

/**
 * Created by jacobth on 2015-03-30.
 */
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
        assertTrue(90 == ball.getShield());
    }

    @Test
    public void testAddHealthToShield(){
        double health = ball.getShield();
        ball.addHealthToShield(10);
        if (health >= 90){
            assertTrue(ball.getShield() == 100);
        } else {
            assertTrue(ball.getShield() == health+10);
        }
    }
    @Test
    public void testGetX2() {
        ball.setBodyPosition(3f, 4f);
        assertTrue(ball.getX2() == 3f);
    }
    @Test
    public void testGetY2() {
        ball.setBodyPosition(3f,4f);
        assertTrue(ball.getY2() == 4f);
    }

    @Test
    public void testGetBallsAura() {
        assertNotNull(ball.getAura());
    }


    @Test
    public void testGetAndSetMana() {
        ball.setMana(10);
        assertTrue(ball.getMana() == 10);
    }

    @Test
    public void testChangeMana(){
        float mana = ball.getMana();
        float manaChange = 10;
        if (mana+manaChange <= 0){
            ball.changeMana(manaChange);
            assertTrue(ball.getMana() == 0);
        } else if (mana+manaChange >= 100){
            ball.changeMana(manaChange);
            assertTrue(ball.getMana() == 100);
        } else {
            ball.changeMana(manaChange);
            assertTrue(ball.getMana() == mana+manaChange);
        }
    }

    @Test
    public void testSetAndHasPowerUp(){
        ball.setHasPowerUp(true);
        assertTrue(ball.hasPowerUp());
    }

    @Test
    public void testSetAndGetRadius(){
        ball.setRadius(2f);
        assertTrue(ball.getRadius() == 2f);
    }

    @Test
    public void testSetAndGetSpeed(){
        ball.setSpeed(2f);
        assertTrue(ball.getSpeed() == 2f);
    }

    @Test
    public void testMaximumMana(){
        assertTrue(ball.getMaximumMana() == 100);
    }





}
