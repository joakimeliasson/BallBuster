package ballbuster.controller;

import ballbuster.model.Player;
import ballbuster.view.HudView;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Johan Segerlund on 2015-05-18.
 */
public class HudController implements IController {

    private HudView hud;

    public HudController(Player player, Player player2, SpriteBatch batch, OrthographicCamera camera) {
        this.hud = new HudView(player,player2,batch,camera);
    }
    
    @Override
    public void onCreate() {
    }

    @Override
    public void onRender() {
        hud.render();
    }
}

