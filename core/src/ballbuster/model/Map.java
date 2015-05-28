package ballbuster.model;


import java.util.ArrayList;

/**
 * Created by Johan Segerlund on 2015-03-30.
 */
public class Map {
    private ArrayList<Tile> tiles;

    public Map() {
        tiles = new ArrayList<>();
    }

    public ArrayList<Tile> getTiles(){
        return tiles;
    }

    public void addTile(Tile tile) {
        tiles.add(tile);
    }
}
