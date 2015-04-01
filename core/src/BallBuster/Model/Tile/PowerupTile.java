package BallBuster.Model.Tile;

/**
 * Created by Matthias on 2015-03-30.
 */
public class PowerupTile extends Tile {

    private String powerup;


    public PowerupTile(int x, int y) {
        super(x, y);
    }

    //@ensure String is valid
    public void setPowerup(String powerup){
        this.powerup = powerup;
    }

    public String getPowerup(){
        return powerup;
    }


}
