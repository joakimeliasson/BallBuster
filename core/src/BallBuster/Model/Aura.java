package BallBuster.Model;

public class Aura {
    private boolean auraStatus;

    public Aura(){
        this.auraStatus = false;
    }

    public void setAuraStatus(boolean b){
        auraStatus = b;
    }

    public boolean getAuraStatus(){
        return auraStatus;
    }

}
