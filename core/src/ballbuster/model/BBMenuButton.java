package ballbuster.model;

import ballbuster.controller.MenuButtonListener;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

/**
 * Created by Matthias on 2015-05-19.
 */
public class BBMenuButton extends ImageButton implements InputProcessor{

    private int buttonIndex;
    private float alpha = 1f;
    private MenuButtonListener listener;

    public BBMenuButton(Drawable imageup, MenuButtonListener listener){
        super(imageup);
        this.listener = listener;
    }

    public BBMenuButton(Drawable imageup, Integer buttonIndex, MenuButtonListener listener){
        super(imageup);
        this.buttonIndex = buttonIndex;
        this.listener = listener;
    }

    public void setAlpha(float alpha){
        this.alpha = alpha;
    }

    public float getAlpha(){
        return alpha;
    }

    @Override
    public boolean keyUp(int keycode) {
        listener.sendKey(keycode, buttonIndex);
        return true;
    }

    @Override
    public boolean keyDown(int keycode) {return false;}
    @Override
    public boolean keyTyped(char character) {return false;}
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {return false;}
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {return false;}
    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {return false;}
    @Override
    public boolean mouseMoved(int screenX, int screenY) {return false;}
    @Override
    public boolean scrolled(int amount) {return false;}
}