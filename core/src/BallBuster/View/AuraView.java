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

    private static final int FRAME_COLS = 5;
    private static final int FRAME_ROWS = 4;

    private Animation walkAnimation;
    private Texture walkSheet;
    private TextureRegion[] walkFrames;
    private TextureRegion currentFrame;

    float stateTime;

    private AuraView auraView;

    private Player player;
    private Aura aura;

    private Body body;
    private ArrayList<Body> bodyList;

    private SpriteBatch batch;

    public AuraView(Player player, Body body, ArrayList<Body> bodyList, SpriteBatch batch) {
        this.player = player;
        this.aura = player.getBall().getAura();
        this.body = body;
        this.bodyList = bodyList;
        this.batch = batch;
    }

    public void createAnimation() {
        walkSheet = new Texture(Gdx.files.internal("core/images/aurasheet.png"));
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
    public void activateAura(Aura aura, boolean b){
        aura.setAuraStatus(b);
    }
    public void activateMagnet() {

        for(int i = 0; i < bodyList.size(); i++) {
            float xDiff = bodyList.get(i).getPosition().x - body.getPosition().x;
            float yDiff = bodyList.get(i).getPosition().y - body.getPosition().y;
            float rad2 = xDiff * xDiff + yDiff * yDiff;
            double tmp = (double) rad2;

            if (Math.sqrt(tmp) < 2f) {
                //body.getFixtureList().get(0).setRestitution(0f);
                body.applyLinearImpulse(0.1f * xDiff / rad2, 0.1f * yDiff / rad2, body.getPosition().x, body.getPosition().y, true);
            }
        }
    }
    public void resetRestitution(Body body) {
        body.getFixtureList().get(0).setRestitution(1f);
    }
}
