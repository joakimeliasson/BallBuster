package ballbuster.model;

/**
 * Created by jacobth on 2015-05-06.
 */
public class Timer {
    private float remaining;
    private float interval;

    public Timer(float interval) {
        this.interval = interval;
        this.remaining = interval;
    }
    public boolean hasTimeElapsed() {
        return remaining<0.0f;
    }
    public void reset() {
        remaining = interval;
    }
    public void reset(float interval) {
        this.interval = interval;
        this.remaining = interval;
    }
    public void update(float delta) {
        remaining = remaining - delta;
    }
    public float getRemaining() {
        return remaining;
    }
}
