package ballbuster.model;

public class Aura {

    private boolean auraStatus = false;
    private float x;
    private float y;


    public Aura(){
    }

    public void setAuraStatus(boolean b){
        this.auraStatus = b;
    }

    public boolean getAuraStatus(){
        return auraStatus;
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
