package BallBuster.Model;

import java.awt.*;

/**
 * Created by jacobth on 2015-03-30.
 */
public class BallFactory {
    private Player player;
    private Aura aura;
    private Map map;

    public BallFactory(Player player, Aura aura, Map map) {
        this.player = player;
        this.aura = aura;
        this.map = map;
    }

    public Ball createBall() {
        return new Ball(player, aura, map);
    }
}
