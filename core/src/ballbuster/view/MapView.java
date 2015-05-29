package ballbuster.view;

import ballbuster.model.Tile;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;

/**
 * Created by Johan Segerlund on 2015-04-27.
 */
public class MapView {

    private World world;
    private TiledMap tiledMap;
    private OrthogonalTiledMapRenderer mapRenderer;
    private ArrayList<BlockTileView> tiles = new ArrayList<>();
    private ArrayList<BlockTileView> tileLocations;
    private OrthographicCamera camera;
    private OrthographicCamera renderCamera;
    private ArrayList<Body> bodyListPlayer1;
    private ArrayList<Body> bodyListPlayer2;

    public MapView(String mapLocation, World world, OrthographicCamera camera) {
        this.tiledMap = new TmxMapLoader().load(mapLocation); // "core/res/TiledMaps/dummyMap.tmx"
        this.world = world;
        this.camera = camera;
        this.bodyListPlayer1 = new ArrayList<>();
        this.bodyListPlayer2 = new ArrayList<>();
        this.mapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        this.mapRenderer.setView(camera);

        //RenderCamera moves the map so that is located where our camera is.
        renderCamera = new OrthographicCamera(camera.viewportWidth, camera.viewportHeight);
        renderCamera.setToOrtho(false, camera.viewportWidth, camera.viewportHeight);

        /*
         *  Create Box2d physics from blacktiles here
         */
        TiledMapTileLayer layer = (TiledMapTileLayer) tiledMap.getLayers().get("BlackBlock");
        for(int row = 0; row < layer.getHeight(); row++) {
            for(int col = 0; col < layer.getWidth(); col++) {
                TiledMapTileLayer.Cell cell = layer.getCell(col, row);

                if(cell == null) {continue; }
                if(cell.getTile() == null) {continue; }

                Tile tile = new Tile(col * layer.getTileWidth() - camera.viewportWidth/2, row * layer.getTileHeight() -camera.viewportHeight/2);

                BlockTileView tileView = new BlockTileView(world, tile, layer.getTileHeight(), layer.getTileHeight());
                tileView.createBody();
                tiles.add(tileView);
                tileLocations = tiles;
                bodyListPlayer1.add(tileView.getBody());
            }
        }


        /*
         *  Create Box2d physics from whitetiles here
         */
        layer = (TiledMapTileLayer) tiledMap.getLayers().get("WhiteBlock");
        for(int row = 0; row < layer.getHeight(); row++) {
            for(int col = 0; col < layer.getWidth(); col++) {
                TiledMapTileLayer.Cell cell = layer.getCell(col, row);

                if(cell == null) {continue; }
                if(cell.getTile() == null) {continue; }

                Tile tile = new Tile(col * layer.getTileWidth()- camera.viewportWidth/2,row * layer.getTileHeight()- camera.viewportHeight/2);

                BlockTileView tileView = new BlockTileView(world, tile, layer.getTileHeight(), layer.getTileHeight());
                tileView.createBody();
                tiles.add(tileView);
                tileLocations = tiles;
                bodyListPlayer2.add(tileView.getBody());
            }
        }

    }

    public ArrayList<Body> getBodyListPlayer1() {
        return bodyListPlayer1;
    }

    public ArrayList<Body> getBodyListPlayer2() {
        return bodyListPlayer2;
    }

    public OrthographicCamera getRenderCamera() {
        return renderCamera;
    }
    public OrthogonalTiledMapRenderer getMapRenderer() {
        return  mapRenderer;
    }
    public ArrayList<BlockTileView> getTileLocations() { return tileLocations; }
}
