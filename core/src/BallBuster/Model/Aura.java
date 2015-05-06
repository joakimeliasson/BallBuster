package ballBuster.model;

public class Aura {

    private boolean auraStatus = false;
    Ball ball;
    private float x;
    private float y;


    public Aura(){
    }
    public Aura(Ball ball){
        this.ball = ball;
    }

    public void setAuraStatus(boolean b){
        this.auraStatus = b;
    }

    public boolean getAuraStatus(){
        return auraStatus;
    }


    public Ball getBall() {
        return ball;
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
