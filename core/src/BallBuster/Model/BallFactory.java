package BallBuster.Model;

import com.badlogic.gdx.physics.box2d.World;

import java.awt.*;

/**
 * Created by jacobth on 2015-03-30.
 */
public class BallFactory {
    private Player player;
    private Aura aura;
    private Map map;
    private World world;


    public BallFactory(Aura aura, Map map, World world) {
        this.player = player;
        this.aura = aura;
        this.map = map;
        this.world = world;
    }


    public Ball createBall() {
        return new Ball(aura, map, world);
    }
}
