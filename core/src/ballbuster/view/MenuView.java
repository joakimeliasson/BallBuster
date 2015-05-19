package ballbuster.view;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.*;

/**
 * Created by jacobth on 2015-05-08.
 */
public class MenuView{

    private Stage thisStage;
    private OrthographicCamera camera;
    private final static float DEFAULT_ALPHA = 1f;

    public MenuView(){
        thisStage = new Stage();
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        thisStage.getViewport().setCamera(camera);
    }


    public Stage getStage(){
        return thisStage;
    }


    public void update(SpriteBatch batch, Sprite background, Sprite currentMap) {
        //setMapSprite();
        batch.begin();
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.draw(background, -camera.viewportWidth / 2, -camera.viewportHeight / 2);

        for(Actor a: thisStage.getActors()){
            a.draw(batch, DEFAULT_ALPHA);
        }
        currentMap.draw(batch);
        batch.end();
    }

}


