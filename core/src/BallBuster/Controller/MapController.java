package BallBuster.Controller;

import BallBuster.Model.Map;
import BallBuster.View.BlockTileView;
import BallBuster.View.MapView;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;

/**
 * Created by jacobth on 2015-04-28.
 */
public class MapController implements IController{

    private MapView mapView;
    private World world;
    private TiledMap tiledMap;
    private OrthogonalTiledMapRenderer mapRenderer;
    private ArrayList<BlockTileView> tiles = new ArrayList<>();
    private OrthographicCamera camera;
    private OrthographicCamera renderCamera;
    private Map mapModel;
    private ArrayList<Body> bodyListPlayer1;
    private ArrayList<Body> bodyListPlayer2;

    public MapController(World world, OrthographicCamera camera) {
        this.world = world;
        this.camera = camera;
        mapView = new MapView("core/res/TiledMaps/dummy64BigMap.tmx",world, camera);
    }
    @Override
    public void onCreate() {

    }

    @Override
    public void onRender() {
        mapView.getMapRenderer().setView(mapView.getRenderCamera());
        mapView.getMapRenderer().render();
    }
    public ArrayList<Body> getBodyListPlayer1() {
        return mapView.getBodyListPlayer1();
    }
    public ArrayList<Body> getBodyListPlayer2() {
        return mapView.getBodyListPlayer2();
    }
}
