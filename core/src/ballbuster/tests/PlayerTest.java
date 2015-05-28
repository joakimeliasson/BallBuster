package ballbuster.tests;

import ballbuster.model.Player;
import ballbuster.model.PowerUp;
import org.junit.Test;
import org.junit.Before;

import java.util.ArrayList;

import static org.junit.Assert.*;


/**
 * Created by Joakim on 2015-03-30.
*/
public class PlayerTest {
    private Player p;
    private Player p2;
    private PowerUp powerUp;
    private ArrayList<Player> playerList;

    @Before
    public void setUpPlayerTest(){
        p = new Player(1,"Player1",1f,1f);
        p2 = new Player(2, "Player2", 1f, 1f);
        p.getBall().setHasPowerUp(true);
        p.getBall().setSpeed(12f);
        p.invertKeys(true);
        playerList = new ArrayList<>();
        playerList.add(p);
        playerList.add(p2);
        p.setKeys(1, 2, 3, 4, 5, 6);
        p2.setKeys(1, 2, 3, 4, 5, 6);
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
    public void testGetUpKey() {
        assertEquals(1, p.getUpKey());
    }

    @Test
    public void testGetLeftKey() {
        assertEquals(2, p.getLeftKey());
    }
    @Test
    public void testGetDownKey() {
        assertEquals(3, p.getDownKey());
    }

    @Test
    public void testGetRightKey() {
        assertEquals(4, p.getRightKey());
    }

    @Test
    public void testGetAuraKey() {
        assertEquals(5, p.getAuraKey());
    }

    @Test
    public void testGetSpeedKey(){
        assertEquals(6, p.getSpeedKey());
    }


    @Test
    public void testPlayerSpeedUp() {
        p.setSpeedUp(true);
        assertTrue(p.hasSpeedUp());
    }

    @Test
    public void testResetBall() {
        p.resetBall();
        assertFalse(p.hasInvertedKeys());
        assertEquals(0.5f, p.getBall().getSpeed(), 0);
        assertFalse(p.getBall().hasPowerUp());
        assertFalse(p.hasSpeedUp());
        assertEquals("", p.getMessage());
    }

    @Test
    public void testApplyPowerUpSpeedUp() {
        powerUp = new PowerUp("speedUp");
        double speed = p.getBall().getSpeed();
        p.applyPowerUp(powerUp, playerList);
        assertTrue(p.hasSpeedUp());
        assertTrue(p.getBall().getSpeed() == speed * 2);
        assertTrue(p.getMessage() == "Obtained Faster Speed!");
    }

    @Test
    public void testApplyPowerUpSlowDown(){
        powerUp = new PowerUp("slowDown");
        p.applyPowerUp(powerUp, playerList);
        assertTrue(p.getBall().getSpeed() == 0.02f);
        assertTrue(p.getMessage() == "Obtained Slower Speed!");
    }

    @Test
    public void testApplyPowerUpInvertKeys(){
        powerUp = new PowerUp("invertKeys");
        p.applyPowerUp(powerUp, playerList);
        assertTrue(p.hasInvertedKeys());
        assertEquals(3, p.getUpKey());
        assertEquals(4, p.getLeftKey());
        assertEquals(1, p.getDownKey());
        assertEquals(2, p.getRightKey());
        assertEquals(5, p.getAuraKey());
        assertEquals(6, p.getSpeedKey());
        assertTrue(p.getMessage() == "Inverted Keys!");
    }

    @Test
    public void testApplyPowerUpDamageOther(){
        powerUp = new PowerUp("damageOther");
        double shield = p2.getBall().getShield();
        p.applyPowerUp(powerUp, playerList);
        assertTrue(p2.getBall().getShield() == shield - 20);
        assertTrue(p.getMessage() == "20 Damage to the Other Player!");
    }

    @Test
    public void testApplyPowerUpInvertOther(){
        powerUp = new PowerUp("invertOther");
        p.applyPowerUp(powerUp, playerList);
        assertTrue(p2.hasInvertedKeys());
        assertTrue(p2.getBall().hasPowerUp());
        assertEquals(3, p2.getUpKey());
        assertEquals(4, p2.getLeftKey());
        assertEquals(1, p2.getDownKey());
        assertEquals(2, p2.getRightKey());
        assertEquals(5, p2.getAuraKey());
        assertEquals(6, p2.getSpeedKey());
        assertTrue(p.getMessage() == "Inverted keys for other player");
    }

    @Test
    public void testApplyHealthPack(){
        powerUp = new PowerUp("healthPack");
        double shield = p.getBall().getShield();
        p.applyHealthPack(powerUp, playerList);
        if (shield < 90){
            assertTrue(p.getBall().getShield() == shield+10);
        } else {
            assertTrue(p.getBall().getShield() == 100);
        }
        assertTrue(p.getMessage() == "A player found a healthPack. +10HP");
    }



}

