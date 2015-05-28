package ballbuster.tests;

import ballbuster.model.BBMenuButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by Matthias on 2015-05-28.
 */
public class BBMenuButtonTest {

    private BBMenuButton indexedButton;
    private BBMenuButton unindexedButton;
    private final int INDEX = 2;

    @Before
    public void setUp() throws Exception {
        Drawable drawable = new TextureRegionDrawable();
        unindexedButton = new BBMenuButton(drawable);
        indexedButton = new BBMenuButton(drawable, INDEX);
    }

    @Test
    public void testGetIndex(){
        assertTrue(unindexedButton.getIndex() == -1);
        assertTrue(indexedButton.getIndex() == INDEX);
    }
}
