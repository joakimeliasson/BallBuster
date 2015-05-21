package ballbuster.view;

import ballbuster.controller.MenuController;
import ballbuster.model.BBMenuButton;
import ballbuster.model.Map;
import ballbuster.model.Player;
import com.badlogic.gdx.*;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by jacobth on 2015-05-08.
 */
public class MenuView{


    private List<Label> bindLabelList;
    private List<String> bindPrefixList;
    private List<Sprite> mapSprites;
    private List<String> mapList;
    private List<Player> playerList;
    private List<Integer> keyList;
    private List<BBMenuButton> bindButtonList;
    private BBMenuButton playButton;
    private BBMenuButton exitButton;
    private BBMenuButton cycleRightButton;
    private BBMenuButton cycleLeftButton;
    private Sprite currentMap;
    private BitmapFont font;
    private SpriteBatch batch;
    private Sprite background;
    private Stage stage;
    private int mapState;
    private boolean isInFocus;
    private OrthographicCamera camera;
    private final static float DEFAULT_ALPHA = 1f;
    private final static int NUMBER_OF_PLAYERS = 2;
    private final static float SCREEN_PARITION = 2.2f;

    public MenuView(){



        stage = new Stage();
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.getViewport().setCamera(camera);

        playerList = new LinkedList<>();
        keyList = new LinkedList<>();
        bindLabelList = new LinkedList<>();
        bindPrefixList = new LinkedList<>();
        mapSprites = new LinkedList<>();
        mapList = new LinkedList<>();
        bindButtonList = new LinkedList<>();


        try{
            BufferedReader reader = new BufferedReader(new FileReader("core/res/maps.txt"));
            String line;
            while((line=reader.readLine())!=null){
                if(line.indexOf('.')==0){
                    mapList.add(line.substring(1));
                }else if(line.indexOf(':')==0){
                    mapSprites.add(new Sprite(new Texture(line.substring(1))));
                }
            }
            reader.close();

        }catch(IOException e){
            System.out.println("map file missing");
        }


        mapState = 0;
        currentMap = mapSprites.get(mapState);
        currentMap.setCenterX(0);
        currentMap.setCenterY((float) (-camera.viewportHeight * Math.pow(SCREEN_PARITION, -1.5)));
        //math pow -1.1 screen part viewport height

        isInFocus = true;
        font = new BitmapFont(Gdx.files.internal("core/images/test.fnt"));
        font.setScale(0.5f, 0.5f);


        batch = new SpriteBatch();
        FileHandle backFileHandle = Gdx.files.internal("core/images/background3.png");
        Texture backgroundTexture = new Texture(backFileHandle);
        background = new Sprite(backgroundTexture);
        final Drawable playDrawable = new TextureRegionDrawable(new TextureRegion(
                new Texture(Gdx.files.internal("core/images/play.png"))));
        final Drawable cycleRightDrawable = new TextureRegionDrawable(new TextureRegion(
                new Texture(Gdx.files.internal("core/images/tempRight.png"))));
        final Drawable cycleLeftDrawable = new TextureRegionDrawable(new TextureRegion(
                new Texture(Gdx.files.internal("core/images/tempLeft.png"))));
        final Drawable exitDrawable = new TextureRegionDrawable(new TextureRegion(
                new Texture(Gdx.files.internal("core/images/exit.png"))));
        final Drawable leftBindButtonDrawable = new TextureRegionDrawable(new TextureRegion(
                new Texture(Gdx.files.internal("core/images/leftbind.png"))));
        final Drawable rightBindButtonDrawable = new TextureRegionDrawable(new TextureRegion(
                new Texture(Gdx.files.internal("core/images/rightbind.png"))));
        final Drawable upBindButtonDrawable = new TextureRegionDrawable(new TextureRegion(
                new Texture(Gdx.files.internal("core/images/upbind.png"))));
        final Drawable downBindButtonDrawable = new TextureRegionDrawable(new TextureRegion(
                new Texture(Gdx.files.internal("core/images/downbind.png"))));
        final Drawable auraBindButtonDrawable = new TextureRegionDrawable(new TextureRegion(
                new Texture(Gdx.files.internal("core/images/aurabind.png"))));
        final Drawable missingDrawable = new TextureRegionDrawable(new TextureRegion
                (new Texture(Gdx.files.internal("core/images/tempbind.png"))));

        //Default keys for players
        keyList = new LinkedList<>();
        //Player 1 keys
        keyList.add(Input.Keys.D);
        keyList.add(Input.Keys.A);
        keyList.add(Input.Keys.W);
        keyList.add(Input.Keys.S);
        keyList.add(Input.Keys.ALT_LEFT);
        keyList.add(Input.Keys.Q);

        //Player 2 keys
        keyList.add(Input.Keys.DPAD_RIGHT);
        keyList.add(Input.Keys.DPAD_LEFT);
        keyList.add(Input.Keys.DPAD_UP);
        keyList.add(Input.Keys.DPAD_DOWN);
        keyList.add(Input.Keys.SPACE);
        keyList.add(Input.Keys.M);

        bindPrefixList = new LinkedList<>();
        bindPrefixList.add("RightKey:");
        bindPrefixList.add("LeftKey:");
        bindPrefixList.add("UpKey:");
        bindPrefixList.add("DownKey:");
        bindPrefixList.add("AuraKey:");
        bindPrefixList.add("SpeedKey:");
        bindPrefixList.addAll(bindPrefixList);
        bindLabelList = new LinkedList<>();



        //playButton
        playButton = new BBMenuButton(playDrawable);
        playButton.setPosition(-playButton.getWidth() / 2, playButton.getHeight() / 2);
        playButton.setBounds(playButton.getX(), playButton.getY(), playButton.getWidth(), playButton.getHeight());
        stage.addActor(playButton);


        //cycleLeftButton
        cycleLeftButton = new BBMenuButton(cycleLeftDrawable);
        cycleLeftButton.setPosition((float) (-camera.viewportWidth / Math.pow(SCREEN_PARITION, 1.5)), currentMap.getY() + currentMap.getHeight() / 2);
        stage.addActor(cycleLeftButton);


        //cycleRightButton
        cycleRightButton = new BBMenuButton(cycleRightDrawable);
        cycleRightButton.setPosition((float) (camera.viewportWidth / Math.pow(SCREEN_PARITION, 1.6)), currentMap.getY() + currentMap.getHeight() / 2);
        stage.addActor(cycleRightButton);


        //exitButton
        exitButton = new BBMenuButton(exitDrawable);
        exitButton.setPosition(playButton.getX(), playButton.getY() - exitButton.getHeight());
        exitButton.setBounds(exitButton.getX(), exitButton.getY(), exitButton.getWidth(), exitButton.getHeight());
        stage.addActor(exitButton);


        //Rebind Buttons
        for(int i = 0; i < NUMBER_OF_PLAYERS*bindPrefixList.size()/2; i++) {
            Drawable drawable;
            switch(i%(bindPrefixList.size()/2)) {
                case 0:
                    drawable = rightBindButtonDrawable;
                    break;
                case 1:
                    drawable = leftBindButtonDrawable;
                    break;
                case 2:
                    drawable = upBindButtonDrawable;
                    break;
                case 3:
                    drawable = downBindButtonDrawable;
                    break;
                case 4:
                    drawable = auraBindButtonDrawable;
                    break;
                case 5:
                    drawable = auraBindButtonDrawable;
                    break;
                default:
                    drawable = missingDrawable;
            }
            BBMenuButton bindButton = new BBMenuButton(drawable, i);


            Label bindLabel = new Label(bindPrefixList.get(i) + Input.Keys.toString(keyList.get(i)), new Label.LabelStyle(font, Color.WHITE));
            if(i < bindPrefixList.size()/2) {
                bindButton.setPosition(-camera.viewportWidth /SCREEN_PARITION,
                        camera.viewportHeight/SCREEN_PARITION-(i+1)* (bindButton.getHeight()));
                bindLabel.setPosition(-camera.viewportWidth /SCREEN_PARITION + bindButton.getWidth(),
                        camera.viewportHeight/SCREEN_PARITION-(i+1)* (bindButton.getHeight()));

            }else if(i < bindPrefixList.size()) {
                bindButton.setPosition((float) (camera.viewportWidth * Math.pow(SCREEN_PARITION, -1.5)),
                        camera.viewportHeight/SCREEN_PARITION-((i%(bindPrefixList.size()/2)+1)* (bindButton.getHeight())));
                bindLabel.setPosition((float) (camera.viewportWidth * Math.pow(SCREEN_PARITION,-1.5) + bindButton.getWidth()),
                        camera.viewportHeight/SCREEN_PARITION-((i%(bindPrefixList.size()/2)+1)* (bindButton.getHeight())));
            }

            bindButton.setBounds(bindButton.getX(),bindButton.getY(),bindButton.getWidth(),bindButton.getHeight());
            stage.addActor(bindButton);
            stage.addActor(bindLabel);
            bindButtonList.add(bindButton);
            bindLabelList.add(bindLabel);
        }

        int jump = 0;
        for(int i = 0; i<NUMBER_OF_PLAYERS; i++) {
            Label playerLabel = new Label("Player " + (i+1), new Label.LabelStyle(font, Color.WHITE));
            stage.addActor(playerLabel);
            playerLabel.setPosition(bindButtonList.get(i+jump).getX(), bindButtonList.get(i+jump).getY()+bindButtonList.get(i+jump).getHeight());
            jump = jump+(bindPrefixList.size()/2-1);
        }

        //Measurement
        /*

        final Drawable measurementDrawable = new TextureRegionDrawable(new TextureRegion(
                new Texture(Gdx.files.internal("core/images/Measurement.png"))));
        for(int i = 0; i < 40; i++){
            BBMenuButton measurement = new BBMenuButton(measurementDrawable);
            BBMenuButton invMeasurement = new BBMenuButton(measurementDrawable);
            measurement.setPosition(i*bindButtonList.get(0).getHeight(),-camera.viewportHeight/2);
            invMeasurement.setPosition(-i*bindButtonList.get(0).getHeight(),-camera.viewportHeight/2);
            thisStage.addActor(measurement);
            thisStage.addActor(invMeasurement);
        }
        */
    }


    public Stage getStage(){
        return stage;
    }

    public String getMap() {
        return mapList.get(mapState);
    }

    public List<Integer> getKeys(){
        return keyList;
    }

    public List<BBMenuButton> getBindButtons(){
        return bindButtonList;
    }

    public BBMenuButton getPlayButton(){
        return playButton;
    }

    public BBMenuButton getExitButton(){
        return exitButton;
    }

    public BBMenuButton getCycleRightButton(){
        return playButton;
    }

    public BBMenuButton getCycleLeftButton(){
        return playButton;
    }

    public void setBindButton(int index, int keyCode){
        keyList.remove(index);
        keyList.add(index, keyCode);
        bindLabelList.get(index).setText(bindPrefixList.get(index) + Input.Keys.toString(keyList.get(index)));
        //bindLabelList.get(index).setText(bindPrefixList.get(index) + KeyCodeMap.valueOf(keyList.get(index)).getHumanName());


    }

    public void cycleRight(){
        mapState--;
        if(mapState < 0) {mapState = mapSprites.size() - 1;}
        mapSprites.get(mapState).setCenterX(currentMap.getX()+currentMap.getWidth()/2);
        mapSprites.get(mapState).setCenterY(currentMap.getY() +currentMap.getHeight()/2);
        currentMap = mapSprites.get(mapState);
    }

    public void cycleLeft(){
        mapState++;
        mapState = mapState%mapSprites.size();
        mapSprites.get(mapState).setCenterX(currentMap.getX()+currentMap.getWidth()/2);
        mapSprites.get(mapState).setCenterY(currentMap.getY() +currentMap.getHeight()/2);
        currentMap = mapSprites.get(mapState);
    }


    public void update() {

        batch.begin();
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.draw(background, -camera.viewportWidth / 2, -camera.viewportHeight / 2);

        for(Actor a: stage.getActors()){
            a.draw(batch, DEFAULT_ALPHA);
        }
        currentMap.draw(batch);
        batch.end();
    }

}


