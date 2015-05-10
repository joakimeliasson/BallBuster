package ballbuster.controller;

import ballbuster.view.MapView;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;

/**
 * Created by jacobth on 2015-04-28.
 */
public class MapController implements IController{

    private MapView mapView;

    public MapController(World world, OrthographicCamera camera) {
        mapView = new MapView("core/res/TiledMaps/designmap.tmx",world, camera);
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
