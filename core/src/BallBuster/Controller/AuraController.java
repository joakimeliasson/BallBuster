package BallBuster.Controller;

import BallBuster.Model.Aura;
import BallBuster.Model.Ball;
import BallBuster.Model.Tile.BlockTile;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

/**
 * Created by jacobth on 2015-04-21.
 */
public class AuraController {
    private Aura aura;
    private ArrayList<BlockTile> list;
    private Ball ball;

    public AuraController(Aura aura, ArrayList<BlockTile> list, Ball ball) {
        this.aura=aura;
        this.list = list;
        this.ball = ball;
    }
    public void renderAura(SpriteBatch batch) {
        if (aura.getAuraStatus()) {
            for (int i = 0; i < list.size(); i++) {
                list.get(i).activateMagnet(ball.getBody());
            }
            aura.renderAnimation(batch);
        }
    }
}
