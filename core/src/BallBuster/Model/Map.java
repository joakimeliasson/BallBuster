package BallBuster.Model;

import BallBuster.Model.Tile.BlackTile;
import BallBuster.Model.Tile.Tile;
import BallBuster.Model.Tile.WhiteTile;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.physics.box2d.*;

import java.util.ArrayList;

/**
 * Created by Johan Segerlund on 2015-03-30.
 * //https://www.youtube.com/watch?v=IwM-LSwZCfw
 */
public class Map {

    private World world;
    private TiledMap tiledMap;
    private ArrayList<Tile> tiles = new ArrayList<>();

    public Map() {}

    public Map(String mapLocation, World world) {
        this.tiledMap = new TmxMapLoader().load(mapLocation); // "core/res/TiledMaps/dummyMap.tmx"
        this.world = world;

        /*
         *  Create Box2d physics from blackboxs here
         */
        TiledMapTileLayer layer = (TiledMapTileLayer) tiledMap.getLayers().get("BlackBlock");
        for(int row = 0; row < layer.getHeight(); row++) {
            for(int col = 0; col < layer.getWidth(); col++) {
                TiledMapTileLayer.Cell cell = layer.getCell(col, row);

                if(cell == null) {continue; }
                if(cell.getTile() == null) {continue; }

                BlackTile tile = new BlackTile(col * layer.getTileWidth(),row * layer.getTileHeight(),world ,layer.getTileWidth(),layer.getTileHeight());
                tiles.add(tile);
            }
        }

        /*
         *  Create Box2d physics from whiteboxs here
         */
        layer = (TiledMapTileLayer) tiledMap.getLayers().get("WhiteBlock");
        for(int row = 0; row < layer.getHeight(); row++) {
            for(int col = 0; col < layer.getWidth(); col++) {
                TiledMapTileLayer.Cell cell = layer.getCell(col, row);

                if(cell == null) {continue; }
                if(cell.getTile() == null) {continue; }

                WhiteTile tile = new WhiteTile(col * layer.getTileWidth(),row * layer.getTileHeight(),world ,layer.getTileWidth(),layer.getTileHeight());
                tiles.add(tile);
            }
        }


        /** set camera to map focus map here
        int mapHeight,mapWidth;
        mapHeight = map.getProperties().get("height", Integer.class) * map.getProperties().get("tileheight", Integer.class);
        mapWidth =  map.getProperties().get("width", Integer.class) * map.getProperties().get("tilewidth", Integer.class);

        //camera position
        camera.position.set(mapWidth/2, mapHeight/2, 0);

        //camera scale
        camera.viewportHeight = mapHeight;
        camera.viewportWidth = mapWidth;
        camera.update();*/

    }

    public TiledMap getTileMap() {
        return tiledMap;
    }
}
