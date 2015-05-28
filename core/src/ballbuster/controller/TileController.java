package ballbuster.controller;

import ballbuster.model.Tile;
import ballbuster.view.TileView;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import java.util.ArrayList;

/**
 * Created by Jacob Lundberg on 2015-04-24.
 */
public class TileController implements IController{

    private TileView tileView;
    private ArrayList<TileView> tileViewList;

    public TileController(World world, SpriteBatch batch, Camera camera) {

        FileHandle horizontalFileHandle = Gdx.files.internal("core/images/wallhorizontal.png");
        Texture horizontalTexture = new Texture(horizontalFileHandle);

        FileHandle verticalFileHandle = Gdx.files.internal("core/images/wallvertical.png");
        Texture verticalTexture = new Texture(verticalFileHandle);

        Tile downTile = new Tile(-camera.viewportWidth/2, -camera.viewportHeight/2);
        Tile upTile = new Tile(-camera.viewportWidth/2, camera.viewportHeight/2-horizontalTexture.getHeight());
        Tile leftTile = new Tile(-camera.viewportWidth/2, -camera.viewportHeight/2);
        Tile rightTile = new Tile(camera.viewportWidth/2-verticalTexture.getWidth(), -camera.viewportHeight/2);

        tileViewList = new ArrayList<>();
        tileViewList.add(new TileView(world, downTile, horizontalTexture, batch));
        tileViewList.add(new TileView(world, upTile, horizontalTexture, batch));
        tileViewList.add(new TileView(world, leftTile, verticalTexture, batch));
        tileViewList.add(new TileView(world, rightTile, verticalTexture, batch));
    }
    public TileController(World world, Tile tile,float width, float height) {
        tileView = new TileView(world, tile, width, height);
    }
    @Override
    public void onCreate() {
        for(TileView tileView : tileViewList)
            tileView.createBody();
    }

    @Override
    public void onRender() {
        for(TileView tileView : tileViewList)
            tileView.renderBody();
    }
    public Body getBody() {
        return tileView.getBody();
    }
    public ArrayList<Body> getWallList() {
        ArrayList<Body> tmp = new ArrayList<>();
        for(int i = 0; i <tileViewList.size(); i++)
            tmp.add(tileViewList.get(i).getBody());
        return tmp;
    }
}
