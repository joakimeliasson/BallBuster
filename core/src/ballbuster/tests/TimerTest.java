package ballbuster.tests;
import ballbuster.model.Timer;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Johan Segerlund on 2015-05-19.
 */


public class TimerTest {

    private Timer timer;
    @Before
    public void setUp() throws Exception {
        timer = new Timer(5f);
    }

    @Test
    public void testUpdate() {
        timer.update(1f);
        assertTrue(timer.getRemaining() == 4f);
    }
    @Test
    public void testReset() {
        timer.reset();
        assertTrue(timer.getRemaining() == 5f);
        timer.update(1f);
        timer.reset(6f);
        assertTrue(timer.getRemaining() == 6f);
    }
    @Test
    public void testHasTimeElapsed() {
        timer.update(3f);
        assertFalse(timer.hasTimeElapsed());
        timer.update(2f);
        assertTrue(timer.hasTimeElapsed());
    }
}
