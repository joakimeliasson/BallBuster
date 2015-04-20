package BallBuster.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Aura {
    private boolean auraStatus;
    Texture auraTexture;
    Sprite auraSprite;
    Ball b;

    public Aura(){
        this.auraStatus = false;

        FileHandle shieldFileHandle = Gdx.files.internal("core/images/shield.png");
        auraTexture = new Texture(shieldFileHandle);
        auraSprite = new Sprite(auraTexture);
        auraSprite.setPosition(b.getBody().getPosition().x, b.getBody().getPosition().y);
    }

    public void setAuraStatus(boolean b){
        this.auraStatus = b;
    }

    public boolean getAuraStatus(){
        return auraStatus;
    }

}
