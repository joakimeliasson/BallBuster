package ballbuster.controller;

import ballbuster.model.Aura;
import ballbuster.model.Player;
import ballbuster.view.AuraView;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;

import java.util.ArrayList;

/**
 * Created by jacobth on 2015-04-21.
 */
public class AuraController implements InputProcessor, IController {

    private AuraView auraView;

    private Player player;
    private Aura aura;

    private SpriteBatch batch;

    public AuraController(Player player, Body body, ArrayList<Body> bodyList, SpriteBatch batch) {
        this.player = player;
        this.aura = player.getBall().getAura();
        this.batch = batch;
        auraView = new AuraView(player, body, bodyList, batch);
    }

    @Override
    public boolean keyDown(int keycode) {
        if (Gdx.input.isKeyJustPressed(player.getAuraKey())) {
            if (aura.getAuraStatus())
                auraView.activateAura(aura, false);
            else
                auraView.activateAura(aura, true);
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
        if (aura.getAuraStatus()) {
            auraView.renderAnimation(batch, player);
            auraView.activateMagnet();
        }
    }

}

