package ballbuster.view;

import ballbuster.controller.*;
import ballbuster.model.Player;
import ballbuster.model.PowerUp;
import com.badlogic.gdx.*;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import java.util.ArrayList;


public class BallBusterView{

    private OrthographicCamera camera;
    private World world;

    public static final float SCALE = 100f;

    private Box2DDebugRenderer debugRenderer;
    private Matrix4 debugMatrix;

    private SpriteBatch batch;

    private Texture backgroundTexture;

    private Sprite background;

    public void onCreate() {
        world = new World(new Vector2(0, 0), true);

        debugRenderer = new Box2DDebugRenderer();

        camera = new OrthographicCamera(1920, 1080);

        Gdx.gl.glClearColor(106f / 255f, 165f / 255f, 255f / 255f, 1f);

        batch = new SpriteBatch();

        FileHandle backFileHandle = Gdx.files.internal("core/images/background3.png");
        backgroundTexture = new Texture(backFileHandle);

        background = new Sprite(backgroundTexture);
    }

    public void onRender() {
        camera.update();

        world.step(1f / 60f, 6, 2);

        batch.setProjectionMatrix(camera.combined);
        debugMatrix = batch.getProjectionMatrix().cpy().scale(SCALE, SCALE, 0);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(background, -camera.viewportWidth / 2, -camera.viewportHeight / 2);
        batch.end();
    }

    public World getWorld() {
        return world;
    }
    public OrthographicCamera getCamera() {
        return camera;
    }
    public SpriteBatch getBatch() {
        return batch;
    }
    public void setDebugRenderer() {
        debugRenderer.render(world, debugMatrix);
    }
}