package BallBuster.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.awt.*;

/**
 * Created by Joakim on 2015-03-30.
 */
public class Ball {

    private int shield;
    private int ballSpeed;

    private Texture ballTexture;
    private Texture shieldTexture;

    private Aura aura;
    private Map map;
    private Point position;

    private Sprite ball;
    private Sprite shieldSprite;

    public Ball(Aura aura, Map map) {
        this.aura = aura;
        this.map = map;

        shield = 100;
        position = new Point(0,0);
        ballSpeed = 0;

        FileHandle ballFileHandle = Gdx.files.internal("core/images/ball.png");
        ballTexture = new Texture(ballFileHandle);
        ball = new Sprite(ballTexture,position.x,position.y,ballTexture.getWidth(),ballTexture.getHeight());

        FileHandle shieldFileHandle = Gdx.files.internal("core/images/shield.png");
        shieldTexture = new Texture(shieldFileHandle);
        shieldSprite = new Sprite(shieldTexture,position.x,position.y,shieldTexture.getWidth(),shieldTexture.getHeight());
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
    public int getBallSpeed() {
        return ballSpeed;
    }
    public int getX() {
        return position.x;
    }
    public int getY() {
        return position.y;
    }

    public Sprite getBallSprite() {
        return ball;
    }
    public Sprite getShieldSprite() {
        return shieldSprite;
    }

}
