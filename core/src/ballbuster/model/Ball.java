package ballbuster.model;


/**
 * Created by Jacob Lundberg on 2015-03-30.
 */
public class Ball {

    private double shield, maximumShield;
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
        maximumShield = 100;
    }

    public void shieldDamage(double damage) {
        shield = shield - damage;
    }

    public double getShield() {
        return shield;
    }

    public float getMana() {
        return mana;
    }

    public void setMana(float mana) {
        this.mana = mana;
    }

    public float getMaximumMana() {
        return maximumMana;
    }

    public double getMaximumShield() {
        return maximumShield;
    }

    public void changeMana(float mana) {
        if (this.mana + mana < 0) {
            this.mana = 0;
        } else if (this.mana + mana > getMaximumMana()) {
            this.mana = getMaximumMana();
        } else {
            this.mana = this.mana + mana;
        }
    }

    public void addHealthToShield(int health) {
        if (shield + health >= getMaximumShield())
            this.shield = getMaximumShield();
        else
            this.shield = shield + health;
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
