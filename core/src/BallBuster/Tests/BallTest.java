package BallBuster.Tests;

/**
 * Created by jacobth on 2015-03-30.
 */
import BallBuster.Model.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class BallTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testCreateBall() {
        Ball  ball = new Ball(1f,1f, new Aura(),new Map(), null,null);
        assertNotNull(ball);
    }


}
