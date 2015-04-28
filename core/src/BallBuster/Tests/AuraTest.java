package ballBuster.tests;

import ballBuster.model.Aura;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by jacobth on 2015-04-25.
 */
public class AuraTest {

    private Aura aura;

    @Before
    public void setUp() throws Exception {
        aura = new Aura();
    }
    @Test
    public void testSetAuraStatus() {
        aura.setAuraStatus(true);
        assertTrue(aura.getAuraStatus());
    }
    @Test
    public void testSetPosition() {
        aura.setPosition(5f, 4f);
        assertTrue(aura.getX() == 5f && aura.getY() == 4f);
    }
}
