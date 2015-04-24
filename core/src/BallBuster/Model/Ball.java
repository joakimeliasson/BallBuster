package BallBuster.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import java.awt.*;

/**
 * Created by Jacob Lundberg on 2015-03-30.
 */
public class Ball {

    private double shield;

    private Aura aura;
    private Map map;

    private float y;
    private float x;

    private float x2;
    private float y2;

    public Ball(float x, float y, Aura aura, Map map) {
        this.aura = aura;
        this.map = map;
        this.x = x;
        this.y = y;

        shield = 100;

    }

    public void shieldDamage(double damage) {
         shield = shield -damage;
    }
    public double getShield() {
        return shield;
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getY() {
        return y;
    }

    public float getX() {
        return x;
    }

    public Aura getAura() {
        return aura;
    }

    public void setBodyPosition(float x, float y) {
        x2 = x;
        y2 = y;
    }
    public float getX2() {
        return x2;
    }
    public float getY2() {
        return y2;
    }
}
