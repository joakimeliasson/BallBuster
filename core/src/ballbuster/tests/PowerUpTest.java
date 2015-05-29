package ballbuster.tests;

import ballbuster.model.PowerUp;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Johan Segerlund on 2015-05-19.
 */
public class PowerUpTest {
    PowerUp powerup;

    @Before
    public void setUp() throws Exception {
        powerup = new PowerUp("Test");
    }

    @Test
    public void testCreatePowerUp() {
        assertNotNull(powerup);
    }

    @Test
    public void testGetPowerUp() {
        assertEquals(powerup.getPowerUp(), "Test");
    }

    @Test
    public void testSetPowerUp() {
        powerup.setPowerUp("PowerTest");
        assertEquals("PowerTest", powerup.getPowerUp());
    }

}
