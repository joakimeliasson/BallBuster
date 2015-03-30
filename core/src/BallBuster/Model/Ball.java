package BallBuster.Model;

import com.badlogic.gdx.Gdx;

import java.awt.*;

/**
 * Created by Joakim on 2015-03-30.
 */
public class Ball {

    private int shield;
    private float ballSpeed;

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

    public void moveRight() {
        position.x += Gdx.graphics.getDeltaTime() * ballSpeed;
    }
    public void moveLeft() {
        position.x -= Gdx.graphics.getDeltaTime() * ballSpeed;
    }
    public void moveUp() {
        position.y += Gdx.graphics.getDeltaTime() * ballSpeed;
    }
    public void moveDown() {
        position.y -= Gdx.graphics.getDeltaTime() * ballSpeed;
    }

    public void accelerateBall() {
            ballSpeed++;
    }

    public void slowBall() {
        ballSpeed--;
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
