package BallBuster.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Aura {

    private boolean auraStatus = false;
    Ball ball;


    public Aura(){

    }
    public Aura(Ball ball){
        this.ball = ball;
    }

    public void setAuraStatus(boolean b){
        this.auraStatus = b;
    }

    public boolean getAuraStatus(){
        return auraStatus;
    }


    public Ball getBall() {
        return ball;
    }
}
