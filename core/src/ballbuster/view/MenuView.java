package ballbuster.view;

import ballbuster.controller.BallBuster;
import ballbuster.model.Player;
import com.badlogic.gdx.*;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import javafx.scene.text.Text;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static com.badlogic.gdx.Input.Keys.*;

/**
 * Created by jacobth on 2015-05-08.
 */
public class MenuView implements ApplicationListener, InputProcessor, EventListener{

    private Sprite background;
    private SpriteBatch batch;
    private ImageButton playButton;
    private ImageButton cycleRightButton;
    private ImageButton cycleLeftButton;
    private ImageButton exitButton;
    //Bindbuttons p1
    private ImageButton p1UpButton;
    private ImageButton p1DownButton;
    private ImageButton p1LeftButton;
    private ImageButton p1RightButton;
    private ImageButton p1AuraButton;
    //bindbuttons p2
    private ImageButton p2UpButton;
    private ImageButton p2DownButton;
    private ImageButton p2LeftButton;
    private ImageButton p2RightButton;
    private ImageButton p2AuraButton;

    private ArrayList<Texture> mapList;
    private BallBuster ballBuster;
    private List<Player> playerList;
    private List<Integer> keyList;
    private int bindState;
    private int mapState = 0;
    private float alpha;
    private Sprite mapSprite;

    private BitmapFont font1;

    //TODO activate drawsprite and fix drawables with filepaths

    @Override
    public void create() {
        font1 = new BitmapFont(Gdx.files.internal("core/images/test.fnt"));

        alpha = 1;

        batch = new SpriteBatch();

        FileHandle backFileHandle = Gdx.files.internal("core/images/background3.png");
        Texture backgroundTexture = new Texture(backFileHandle);

        background = new Sprite(backgroundTexture);

        final Drawable playDrawable = new TextureRegionDrawable(new TextureRegion(
                new Texture(Gdx.files.internal("core/images/play.png"))));
        /*
        final Drawable cycleRightDrawable = new TextureRegionDrawable(new TextureRegion(
                new Texture(Gdx.files.internal("core/images/cycleright.png"))));
        final Drawable cycleLeftDrawable = new TextureRegionDrawable(new TextureRegion(
                new Texture(Gdx.files.internal("core/images/cycleleft.png"))));
        final Drawable exitDrawable = new TextureRegionDrawable(new TextureRegion(
                new Texture(Gdx.files.internal("core/images/exit.png"))));
        final Drawable bindDrawable = new TextureRegionDrawable(new TextureRegion(
                new Texture(Gdx.files.internal("core/images/bind.png"))));
*/
        /*final Drawable drawable = new TextureRegionDrawable(new TextureRegion(
                new Texture(Gdx.files.internal("core/images/play.png"))));*/


        playButton = new ImageButton(playDrawable);
        playButton.addListener(this);
  /*      cycleLeftButton = new ImageButton(cycleLeftDrawable);
        cycleLeftButton.addListener(this);
        cycleRightButton = new ImageButton(cycleRightDrawable);
        cycleRightButton.addListener(this);
        exitButton = new ImageButton(exitDrawable);
        exitButton.addListener(this);
        p1UpButton = new ImageButton(bindDrawable);
        p1UpButton.addListener(this);
        p1DownButton = new ImageButton(bindDrawable);
        p1DownButton.addListener(this);
        p1LeftButton = new ImageButton(bindDrawable);
        p1LeftButton.addListener(this);
        p1RightButton = new ImageButton(bindDrawable);
        p1RightButton.addListener(this);
        p1AuraButton = new ImageButton(bindDrawable);
        p1AuraButton.addListener(this);
        p2UpButton = new ImageButton(bindDrawable);
        p2UpButton.addListener(this);
        p2DownButton = new ImageButton(bindDrawable);
        p2DownButton.addListener(this);
        p2LeftButton = new ImageButton(bindDrawable);
        p2LeftButton.addListener(this);
        p2RightButton = new ImageButton(bindDrawable);
        p2RightButton.addListener(this);
        p2AuraButton = new ImageButton(bindDrawable);
        p2AuraButton.addListener(this);
*/



        playButton.setPosition(Gdx.graphics.getWidth() / 2 - playButton.getWidth() / 2, Gdx.graphics.getHeight() / 2 - playButton.getHeight() / 2);
        Gdx.input.setInputProcessor(this);

        //Default keys for players
        keyList = new LinkedList<Integer>();

        //Player 1 keys
        keyList.add(Keys.W);
        keyList.add(Keys.S);
        keyList.add(Keys.A);
        keyList.add(Keys.D);
        keyList.add(Keys.ALT_LEFT);

        //Player 2 keys
        keyList.add(Keys.DPAD_UP);
        keyList.add(Keys.DPAD_DOWN);
        keyList.add(Keys.DPAD_LEFT);
        keyList.add(Keys.DPAD_RIGHT);
        keyList.add(Keys.SPACE);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render() {
        if(ballBuster==null) {
            //setMapSprite();
            batch.begin();
            batch.draw(background, 0, 0);
            playButton.draw(batch, alpha);
            batch.end();
        }

        if(ballBuster!=null) {
            int bindNbr = 0;
            playerList = ballBuster.getPlayers();

            //Should not loop more than once, as there are only 2 players
            for (int i = bindNbr; i < playerList.size(); i++) {
                playerList.get(i).setKeys(keyList.get(bindNbr),keyList.get(bindNbr+1),keyList.get(bindNbr+2),keyList.get(bindNbr+3),keyList.get(bindNbr+4));
                bindNbr = bindNbr+5;
            }
            ballBuster.render();
        }
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        keyList.remove(bindState);
        keyList.add(bindState,keycode);

        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        alpha = 0.5f;
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        ballBuster = new BallBuster();
        ballBuster.create();
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    @Override
    public boolean handle(Event event) {
        return false;
    }

    private void setMapSprite(){
        mapSprite.setTexture(mapList.get(mapState));
    }
}
