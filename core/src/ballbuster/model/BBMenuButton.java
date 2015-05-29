package ballbuster.model;


import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

/**
 * Created by Matthias on 2015-05-19.
 */
public class BBMenuButton extends ImageButton {

    private int buttonIndex = -1;

    /*
     * To be used by unindexed buttons such as the play button,
     * exit button and cycle buttons.
     */
    public BBMenuButton(Drawable imageup) {
        super(imageup);
    }

    /*
     * To be used in indexed buttons, such as the key rebind buttons
     */
    public BBMenuButton(Drawable imageup, Integer buttonIndex) {
        super(imageup);
        this.buttonIndex = buttonIndex;
    }

    //Gets the buttons index, only relevant for indexed buttons;
    public int getIndex() {
        return buttonIndex;
    }
}