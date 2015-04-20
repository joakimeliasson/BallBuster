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

    }
    public Aura(Ball ball){
        this.auraStatus = false;
        b = ball;

        FileHandle shieldFileHandle = Gdx.files.internal("core/images/shield.png");
        auraTexture = new Texture(shieldFileHandle);
        auraSprite = new Sprite(auraTexture);
        auraSprite.setPosition(b.getBallSprite().getX(), b.getBallSprite().getY());
    }

    public void setAuraPosition(){
        auraSprite.setPosition(b.getBallSprite().getX(), b.getBallSprite().getY());
    }

    public void setAuraStatus(boolean b){
        this.auraStatus = b;
    }

    public boolean getAuraStatus(){
        return auraStatus;
    }

    public Sprite getAuraSprite(){
        return this.auraSprite;
    }

}
