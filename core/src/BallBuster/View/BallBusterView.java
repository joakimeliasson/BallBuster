package BallBuster.View;

import BallBuster.Controller.GameController;
import BallBuster.Model.Ball;
import BallBuster.Model.Tile.BlockTile;
import BallBuster.Model.Tile.Tile;
import BallBuster.Model.Tile.WallTile;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import java.util.ArrayList;

public class BallBusterView extends ApplicationAdapter {

    private GameController gameController;

    @Override
    public void create() {
        gameController = new GameController();
        gameController.create();
    }

    @Override
    public void render() {
        gameController.render();
    }
}
