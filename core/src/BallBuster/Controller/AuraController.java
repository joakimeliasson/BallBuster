package BallBuster.Controller;

import BallBuster.Model.Aura;
import BallBuster.Model.Ball;
import BallBuster.Model.Tile.BlockTile;
import BallBuster.View.AuraView;
import BallBuster.View.BallView;
import BallBuster.View.BlockTileView;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;

import java.util.ArrayList;

/**
 * Created by jacobth on 2015-04-21.
 */
public class AuraController {

    private Aura aura;

    public AuraController(Aura aura) {
        this.aura = aura;
    }
    public void renderAura(Aura aura ,ArrayList<BlockTileView> list, BallView ballView, AuraView auraView, SpriteBatch batch) {
            for (int i = 0; i < list.size(); i++) {
                activateMagnet(list.get(i).getBody(), ballView.getBody());
            }
            auraView.render();
    }

    public void activateAura(boolean b){
            aura.setAuraStatus(b);
    }
    public void activateMagnet(Body blockBody, Body body) {
        float xDiff = blockBody.getPosition().x - body.getPosition().x;
        float yDiff = blockBody.getPosition().y - body.getPosition().y;
        float rad2 = xDiff*xDiff + yDiff*yDiff;
        double tmp = (double)rad2;

        if(Math.sqrt(tmp) < 20f) {

            body.getFixtureList().get(0).setRestitution(0f);
            body.applyLinearImpulse(((1f * xDiff) / rad2), (1f * yDiff) / rad2, body.getPosition().x, body.getPosition().y, true);
        }
    }
    public void resetRestitution(Body body) {
        body.getFixtureList().get(0).setRestitution(1f);
    }
}

