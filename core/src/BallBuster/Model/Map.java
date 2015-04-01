package BallBuster.Model;

import java.util.ArrayList;

/**
 * Created by Johan Segerlund on 2015-03-30.
 */
public class Map {

    private int HEIGHT;
    private int WIDTH;
    private ArrayList entities = new ArrayList<Player>();

    public Map() {
        new Map(100, 100, 2); //just for now
    }

    public Map(int height, int width, int amountOfPlayers) {
        this.HEIGHT = height;
        this.WIDTH = width;

        //Put all entities that needs to be updated here, players, power up timer etc...
        for(int i = 0; i < amountOfPlayers; i++) {
            //entities.add(new Player(i, startlocatoin)); i = which player => player 0 uses wasd as keybinds?
        }
    }

    /**
     * Return the tile object at given position
     * @param x coordinate (pixels?)
     * @param y coordinate (pixels?)
     * @return the tile
     */
    public Object getTile(int x, int y) {
        return null;
    }

    /**
     * Updates all content on the map.
     */
    public void update() {
        for(int i = 0; i < entities.size();i++) {
            //players.get(i).update();
            //update power up timer?
        }
    }


}
