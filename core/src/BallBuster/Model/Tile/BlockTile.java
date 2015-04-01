package BallBuster.Model.Tile;

/**
 * Created by Matthias on 2015-03-30.
 */
public class BlockTile extends Tile {

    private String color;

    public BlockTile(int x, int y, String color) {
        super(x, y);
        this.color = color;
    }

    public String getColor(){
        return color;
    }

    public void setColor(String color){
        this.color = color;
    }

}
