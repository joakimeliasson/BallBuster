package ballBuster.model;

/**
 * Created by Joakim on 2015-04-24.
 */
public class PowerUp {

    private String powerUp;

    public PowerUp(String powerUp){
        this.powerUp = powerUp;
    }

    public String getPowerUp(){
        return this.powerUp;
    }

    public void setPowerUp(String powerUp){
        this.powerUp = powerUp;
    }
}
