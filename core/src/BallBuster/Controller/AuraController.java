package BallBuster.Controller;

import BallBuster.Model.Aura;
import BallBuster.Model.Ball;
import BallBuster.Model.Player;
import BallBuster.Model.Tile.BlockTile;
import BallBuster.View.AuraView;
import BallBuster.View.BallView;
import BallBuster.View.BlockTileView;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;

import java.util.ArrayList;

/**
 * Created by jacobth on 2015-04-21.
 */
public class AuraController implements InputProcessor, IController{

    private AuraView auraView;

    private Player player;
    private Aura aura;

    private Body body;
    private ArrayList<Body> bodyList;

    private SpriteBatch batch;

    public AuraController(Player player, Aura aura, Body body, ArrayList<Body> bodyList, SpriteBatch batch) {
        this.player = player;
        this.aura = aura;
        this.body = body;
        this.bodyList = bodyList;
        this.batch = batch;
        auraView = new AuraView();
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
                body.getFixtureList().get(0).setRestitution(0f);
                body.applyLinearImpulse(((0.1f * xDiff) / rad2), (0.1f * yDiff) / rad2, body.getPosition().x, body.getPosition().y, true);
            }
        }
    }
    public void resetRestitution(Body body) {
        body.getFixtureList().get(0).setRestitution(1f);
    }

    @Override
    public boolean keyDown(int keycode) {
        if (Gdx.input.isKeyJustPressed(player.getAuraKey())){
            if (aura.getAuraStatus())
                activateAura(aura, false);
            else
                activateAura(aura, true);
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

    @Override
    public void onCreate() {
        auraView.createAnimation();
    }

    @Override
    public void onRender() {
        keyDown(0);
        if(aura.getAuraStatus()) {
            auraView.renderAnimation(batch, player);
            activateMagnet();
        }
    }

    public Aura getAura() {
        return aura;
    }
}

