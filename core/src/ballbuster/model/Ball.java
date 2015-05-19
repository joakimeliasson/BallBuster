package ballbuster.model;

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
    private float mana, maximumMana;

    private Aura aura;

    private float y;
    private float x;

    private float x2;
    private float y2;

    private float radius;

    private float speed;
    private boolean hasPowerUp = false;

    public Ball(float x, float y) {
        this.x = x;
        this.y = y;
        this.aura = new Aura();

        speed = 0.50f;
        shield = 100;
        mana = 100;
        maximumMana = 100;

    }

    public void shieldDamage(double damage) {
         shield = shield -damage;
    }

    public double getShield() {
        return shield;
    }

    public float getMana() {
        return mana;
    }
    public float getMaximumMana() {
        return maximumMana;
    }

    public void changeMana(float mana){
        this.mana = this.mana + mana;
    }
    public void addHealthToShield(int health){
        if (shield+health < 100)
            this.shield = shield+health;
        else
            this.shield = 100;
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }
    public void setRadius(float radius) {
        this.radius = radius;
    }
    public float getRadius() {
        return radius;
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
    public float getSpeed() {
        return speed;
    }
    public void setSpeed(float speed) {
        this.speed = speed;
    }
    public boolean hasPowerUp() {
        return hasPowerUp;
    }
    public void setHasPowerUp(boolean b) {
        hasPowerUp = b;
    }
}
