package ballbuster.model;

import ballbuster.controller.IMenuController;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

/**
 * Created by Matthias on 2015-05-19.
 */
public class BBMenuButton extends ImageButton{

    private int buttonIndex;
    private float alpha = 1f;

    public BBMenuButton(Drawable imageup){
        super(imageup);
    }

    public BBMenuButton(Drawable imageup, Integer buttonIndex){
        super(imageup);
        this.buttonIndex = buttonIndex;
    }

    public void setAlpha(float alpha){
        this.alpha = alpha;
    }

    public float getAlpha(){
        return alpha;
    }

    public int getIndex(){
        return buttonIndex;
    }
}