package BallBuster.Model;

import java.awt.*;

/**
 * Created by Joakim on 2015-03-30.
 */
public class Ball {

    private int shield;

    private Player player;
    private Aura aura;
    private Map map;
    private Point position;

    public Ball(Player player, Aura aura, Map map) {
        this.player = player;
        this.aura = aura;
        this.map = map;

        shield = 100;
        position = new Point(0,0);
    }

    public void accelerateBall() {

    }

    public void slowBall() {

    }

    public void changePosition(int x, int y) {
        position.x = x;
        position.y = y;
    }

    public void wallCollision() {

    }

    public int shieldDamage() {
        return 0;
    }

}
