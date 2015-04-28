package BallBuster.View;

import BallBuster.Controller.AuraController;
import BallBuster.Controller.BallBuster;
import BallBuster.Model.Aura;
import BallBuster.Model.Player;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;

import java.util.ArrayList;

/**
 * Created by Joakim on 2015-04-24.
 */
public class AuraView{

    private Sprite sprite;

    private static final int FRAME_COLS = 6;
    private static final int FRAME_ROWS = 5;

    private Animation walkAnimation;
    private Texture walkSheet;
    private TextureRegion[] walkFrames;
    private TextureRegion currentFrame;

    float stateTime;

    public void createAnimation() {
        walkSheet = new Texture(Gdx.files.internal("core/images/animation.png"));
        TextureRegion[][] tmp = TextureRegion.split(walkSheet, walkSheet.getWidth()/FRAME_COLS, walkSheet.getHeight()/FRAME_ROWS);
        walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                walkFrames[index++] = tmp[i][j];
            }
        }
        walkAnimation = new Animation(0.025f, walkFrames);
        stateTime = 0f;
    }
    public void renderAnimation(SpriteBatch batch, Player player) {
        stateTime += Gdx.graphics.getDeltaTime();
        currentFrame = walkAnimation.getKeyFrame(stateTime, true);
        sprite = new Sprite(currentFrame);
        Aura aura = player.getBall().getAura();
        aura.setPosition(player.getBall().getX2()*BallBuster.SCALE-sprite.getWidth()/2, player.getBall().getY2()*BallBuster.SCALE-sprite.getHeight()/2);
        sprite.setPosition(aura.getX(), aura.getY());
        batch.begin();
        batch.draw(sprite, sprite.getX(), sprite.getY(), sprite.getOriginX(), sprite.getOriginY(),
                sprite.getWidth(), sprite.getHeight(), sprite.getScaleX(), sprite.getScaleY(), sprite.getRotation());
        batch.end();
    }
}
