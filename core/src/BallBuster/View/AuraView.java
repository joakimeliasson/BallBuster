package BallBuster.View;

import BallBuster.Controller.AuraController;
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

/**
 * Created by Joakim on 2015-04-24.
 */
public class AuraView implements ApplicationListener, InputProcessor {

    private Sprite sprite;

    private static final int FRAME_COLS = 6;
    private static final int FRAME_ROWS = 5;

    private Animation walkAnimation;
    private Texture walkSheet;
    private TextureRegion[] walkFrames;
    private TextureRegion currentFrame;

    private SpriteBatch batch;

    private Player player;

    private Sprite ballSprite;

    private Aura aura;

    private AuraController auraController;

    float stateTime;

    public AuraView(SpriteBatch batch, Player player) {
        this.batch = batch;
        this.player = player;
        this.aura = player.getBall().getAura();
        this.auraController = new AuraController();
    }


    private void createAnimation() {
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
    private void renderAnimation(SpriteBatch batch) {
        stateTime += Gdx.graphics.getDeltaTime();
        currentFrame = walkAnimation.getKeyFrame(stateTime, true);
        sprite = new Sprite(currentFrame);
        aura.setPosition(player.getBall().getX2()*BallBusterView.SCALE-sprite.getWidth()/2, player.getBall().getY2()*BallBusterView.SCALE-sprite.getHeight()/2);
        sprite.setPosition(aura.getX(), aura.getY());
        batch.begin();
        batch.draw(sprite, sprite.getX(), sprite.getY(), sprite.getOriginX(), sprite.getOriginY(),
                sprite.getWidth(), sprite.getHeight(), sprite.getScaleX(), sprite.getScaleY(), sprite.getRotation());
        batch.end();
    }

    public Aura getAura(){
        return this.aura;
    }

    @Override
    public void create() {
        createAnimation();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render() {
        keyDown(0);
        if(aura.getAuraStatus())
            renderAnimation(batch);
        //aura.setPosition(player.getBall().getX()-sprite.getWidth()/2, (player.getBall().getY()*BallBusterView.SCALE)-sprite.getHeight()/2);
        //sprite.setPosition(aura.getX(),aura.getY());
        //aura.setPosition(player.getBall().getX(), player.getBall().getY());

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public boolean keyDown(int keycode) {
        if (Gdx.input.isKeyJustPressed(player.getAuraKey())){
            if (aura.getAuraStatus()) {
                auraController.activateAura(aura, false);
            }
            else {
                auraController.activateAura(aura, true);
            }

        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
    public Sprite getSprite() {
        return sprite;
    }
}
