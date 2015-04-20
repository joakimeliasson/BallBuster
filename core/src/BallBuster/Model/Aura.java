package BallBuster.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Aura {
    private boolean auraStatus;
    Texture auraTexture;
    Sprite auraSprite;

    public Aura(){
        this.auraStatus = false;

        FileHandle shieldFileHandle = Gdx.files.internal("core/images/aura.png");
        auraTexture = new Texture(shieldFileHandle);
        auraSprite = new Sprite(auraTexture);
    }

    public void setAuraStatus(boolean b){
        this.auraStatus = b;
    }

    public boolean getAuraStatus(){
        return auraStatus;
    }

}
