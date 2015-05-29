package ballbuster.model;

/**
 * Created by Matthias on 2015-03-30.
 */

public class Tile {

    private float x;
    private float y;

    public Tile(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}

