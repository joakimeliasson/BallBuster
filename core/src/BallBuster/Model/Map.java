package BallBuster.Model;

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

    private float blackTileSize;

    public Map() {}

    public Map(String mapLocation, World world, float SCALE) {

        //tiledMap = new TmxMapLoader().load("core/res/TiledMaps/dummyMap.tmx");
        tiledMap = new TmxMapLoader().load(mapLocation);

        this.world = world;

        /*
         *  Create Box2d physics from blackboxs here
         */
        TiledMapTileLayer layer = (TiledMapTileLayer) tiledMap.getLayers().get("BlackBlock");
        this.blackTileSize = layer.getTileWidth();
        for(int row = 0; row < layer.getHeight(); row++) {
            for(int col = 0; col < layer.getWidth(); col++) {
                TiledMapTileLayer.Cell cell = layer.getCell(col, row);

                if(cell == null) {continue; }
                if(cell.getTile() == null) {continue; }

                BodyDef bodyDef = new BodyDef();
                bodyDef.type = BodyDef.BodyType.StaticBody;

                bodyDef.position.set(((col* blackTileSize) + blackTileSize /2)/SCALE, (blackTileSize /2 + row*(blackTileSize))/SCALE);

                Body body = world.createBody(bodyDef);

                //Create the body as a box
                PolygonShape shape = new PolygonShape();

                shape.setAsBox(blackTileSize /2/SCALE, blackTileSize /2 /SCALE);

                //Set physical attributes to the body
                FixtureDef fixtureDef = new FixtureDef();
                fixtureDef.shape = shape;
                fixtureDef.density = 7f;
                fixtureDef.friction = 1f;
                body.createFixture(fixtureDef);
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
