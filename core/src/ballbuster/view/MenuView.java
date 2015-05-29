package ballbuster.view;

import ballbuster.model.BBMenuButton;
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
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.io.*;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by jacobth on 2015-05-08.
 */
public class MenuView {


    private List<Label> bindLabelList;
    private final List<Sprite> mapSprites;
    private final List<String> mapList;
    private final List<BBMenuButton> bindButtonList;
    private BBMenuButton playButton;
    private BBMenuButton exitButton;
    private BBMenuButton cycleRightButton;
    private BBMenuButton cycleLeftButton;
    private BBMenuButton checkAIButton;
    private Sprite currentMap;
    private final BitmapFont font;
    private final SpriteBatch batch;
    private final Sprite background;
    private final Stage stage;
    private int mapState;
    private final OrthographicCamera camera;
    private final static float DEFAULT_ALPHA = 1f;
    private final static float SCREEN_PARITION = 2.2f;
    private final static int[] SCREEN_RESOLUTION = {1920, 1080};

    /**
     * The only constructor for MenuView.
     * In the current version, using a higher number of players than 2 is inadvisable, as there are no designated
     * positions for additional actors on the stage.
     *
     * @Ensure that the size of the list of prefixes, and the size of the list of keys are the same.
     */
    public MenuView(int nbrOfPlayers, List<String> bindPrefixList, List<Integer> keyList ){
        stage = new Stage();
        camera = new OrthographicCamera(SCREEN_RESOLUTION[0], SCREEN_RESOLUTION[1]);
        stage.getViewport().setCamera(camera);
        bindLabelList = new LinkedList<>();
        mapSprites = new LinkedList<>();
        mapList = new LinkedList<>();
        bindButtonList = new LinkedList<>();
        readMapFile();

        mapState = 0;
        currentMap = mapSprites.get(mapState);
        currentMap.setCenterX(0);
        currentMap.setCenterY((float) (-camera.viewportHeight * Math.pow(SCREEN_PARITION, -1.5)));
        font = new BitmapFont(Gdx.files.internal("test.fnt"));
        font.setScale(0.5f, 0.5f);


        batch = new SpriteBatch();
        FileHandle backFileHandle = Gdx.files.internal("background3.png");
        Texture backgroundTexture = new Texture(backFileHandle);
        background = new Sprite(backgroundTexture);
        bindLabelList = new LinkedList<>();


        createUnindexedButtons();
        createIndexedButtons(nbrOfPlayers,bindPrefixList,keyList);

        /*
         * Places a label with the players name, for example "Player 1",
         * over the players keybinding area.
         * This is done simply by looping through the number of players,
         * assigning a position based on the number of keys in the list of keys divided by the number of players,
         * and then placing the label above the position of the corresponding button.
         * The button in question should always be the first button in each players lists
         *
         *
         */
        float buttonHeight = bindButtonList.get(0).getHeight();
        for(int i = 0; i<nbrOfPlayers; i++) {
            Label playerLabel = new Label("Player " + (i+1), new Label.LabelStyle(font, Color.WHITE));
            stage.addActor(playerLabel);
            playerLabel.setPosition(bindButtonList.get(i*bindPrefixList.size()/nbrOfPlayers).getX(),
                    bindButtonList.get(i*bindPrefixList.size()/nbrOfPlayers).getY()+buttonHeight);
        }

        //Adds label next to AI button
        Label checkAILabel = new Label("AI", new Label.LabelStyle(font, Color.DARK_GRAY));
        checkAILabel.setFontScale(1.5f);
        checkAILabel.setPosition(checkAIButton.getX() - 1.2f * checkAIButton.getWidth(), checkAIButton.getY() + checkAILabel.getHeight());
        stage.addActor(checkAILabel);

        //Adds a title to the screen
        Label titleLabel = new Label("BallBuster", new Label.LabelStyle(font, Color.DARK_GRAY));
        stage.addActor(titleLabel);
        //titleLabel.setAlignment(Align.center);
        titleLabel.setPosition(0-titleLabel.getWidth()/2, (float) (camera.viewportHeight*Math.pow(SCREEN_PARITION,-2)));

        //Measurements for adjusting the screen, update code as needed

        /*
        final Drawable measurementDrawable = new TextureRegionDrawable(new TextureRegion(
                new Texture(Gdx.files.internal("Measurement.png"))));
        for(int i = 0; i < 40; i++){
            BBMenuButton measurement = new BBMenuButton(measurementDrawable);
            BBMenuButton invMeasurement = new BBMenuButton(measurementDrawable);
            measurement.setPosition(i*bindButtonList.get(0).getHeight(),-camera.viewportHeight/2);
            invMeasurement.setPosition(-i*bindButtonList.get(0).getHeight(),-camera.viewportHeight/2);
            stage.addActor(measurement);
            stage.addActor(invMeasurement);
        }
        */

    }

    /*
     * Utility method for the constructor
     */
    private void readMapFile(){
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("maps.txt"), "UTF-8"));
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
            System.out.println("Map file read failed");
        }
    }


    /*
     * Used by the constructor to create buttons without the use of indexes
     * Every button that does not rely on players should be created here
     */
    private void createUnindexedButtons(){

        final Drawable playDrawable = new TextureRegionDrawable(new TextureRegion(
                new Texture(Gdx.files.internal("play.png"))));
        final Drawable cycleRightDrawable = new TextureRegionDrawable(new TextureRegion(
                new Texture(Gdx.files.internal("tempRight.png"))));
        final Drawable cycleLeftDrawable = new TextureRegionDrawable(new TextureRegion(
                new Texture(Gdx.files.internal("tempLeft.png"))));
        final Drawable exitDrawable = new TextureRegionDrawable(new TextureRegion(
                new Texture(Gdx.files.internal("exit.png"))));
        final Drawable defaultToggleDrawable = new TextureRegionDrawable(new TextureRegion(
                new Texture(Gdx.files.internal("unchecked.png"))));

        playButton = new BBMenuButton(playDrawable);
        playButton.setPosition(-playButton.getWidth() / 2, playButton.getHeight() / 2);
        playButton.setBounds(playButton.getX(), playButton.getY(), playButton.getWidth(), playButton.getHeight());
        stage.addActor(playButton);
        System.out.println("Play button placed on positions X: " + playButton.getX() + ", Y: " + playButton.getY());

        cycleLeftButton = new BBMenuButton(cycleLeftDrawable);
        cycleLeftButton.setPosition((float) (-camera.viewportWidth / Math.pow(SCREEN_PARITION, 1.5)), currentMap.getY() + currentMap.getHeight() / 2);
        cycleLeftButton.setBounds(cycleLeftButton.getX(), cycleLeftButton.getY(), cycleLeftButton.getWidth(), cycleLeftButton.getHeight());
        stage.addActor(cycleLeftButton);

        cycleRightButton = new BBMenuButton(cycleRightDrawable);
        cycleRightButton.setPosition((float) (camera.viewportWidth / Math.pow(SCREEN_PARITION, 1.6)), currentMap.getY() + currentMap.getHeight() / 2);
        cycleRightButton.setBounds(cycleRightButton.getX(), cycleRightButton.getY(), cycleRightButton.getWidth(), cycleRightButton.getHeight());
        stage.addActor(cycleRightButton);

        exitButton = new BBMenuButton(exitDrawable);
        exitButton.setPosition(playButton.getX(), playButton.getY() - exitButton.getHeight());
        exitButton.setBounds(exitButton.getX(), exitButton.getY(), exitButton.getWidth(), exitButton.getHeight());
        stage.addActor(exitButton);

        checkAIButton = new BBMenuButton(defaultToggleDrawable);
        checkAIButton.setPosition(playButton.getX()+2*playButton.getWidth(), playButton.getY() +3*playButton.getHeight());
        checkAIButton.setBounds(checkAIButton.getX(), checkAIButton.getY(), 100, 100);
        stage.addActor(checkAIButton);



    }


    /*
     * Used by the constructor to create buttons with the use of indexes
     * Every button that relies on players should be created here
     */
    private void createIndexedButtons(int nbrOfPlayers, List<String> bindPrefixList, List<Integer> keyList){

        final Drawable leftBindButtonDrawable = new TextureRegionDrawable(new TextureRegion(
                new Texture(Gdx.files.internal("leftbind.png"))));
        final Drawable rightBindButtonDrawable = new TextureRegionDrawable(new TextureRegion(
                new Texture(Gdx.files.internal("rightbind.png"))));
        final Drawable upBindButtonDrawable = new TextureRegionDrawable(new TextureRegion(
                new Texture(Gdx.files.internal("upbind.png"))));
        final Drawable downBindButtonDrawable = new TextureRegionDrawable(new TextureRegion(
                new Texture(Gdx.files.internal("downbind.png"))));
        final Drawable auraBindButtonDrawable = new TextureRegionDrawable(new TextureRegion(
                new Texture(Gdx.files.internal("aurabind.png"))));
        final Drawable missingDrawable = new TextureRegionDrawable(new TextureRegion
                (new Texture(Gdx.files.internal("tempbind.png"))));


        /*
         * Creates a BBMenuButton and a Label for every keybind in the given list.
         * If more buttons are added, further drawables should be added.
         */
        for(int i = 0; i < bindPrefixList.size(); i++) {
            Drawable drawable;
            switch(i%(bindPrefixList.size()/nbrOfPlayers)) {
                case 0:
                    drawable = upBindButtonDrawable;
                    break;
                case 1:
                    drawable = leftBindButtonDrawable;
                    break;
                case 2:
                    drawable = downBindButtonDrawable;
                    break;
                case 3:
                    drawable = rightBindButtonDrawable;
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

            /*
             * Positons of the buttons are labels are set here by adding each pair below the former,
             * starting from a new position for every player.
             * Add further if cases if more players are required.
             */
            if(i < bindPrefixList.size()/2) {
                bindButton.setPosition(-camera.viewportWidth /SCREEN_PARITION,
                        camera.viewportHeight/SCREEN_PARITION-(i+1)* (bindButton.getHeight()));
                bindLabel.setPosition(-camera.viewportWidth /SCREEN_PARITION + bindButton.getWidth(),
                        camera.viewportHeight/SCREEN_PARITION-(i+1)* (bindButton.getHeight()));

            }else if(i < bindPrefixList.size()) {
                bindButton.setPosition((float) (camera.viewportWidth * Math.pow(SCREEN_PARITION, -1.5)),
                        camera.viewportHeight/SCREEN_PARITION-(i%(bindPrefixList.size()/2)+1)*bindButton.getHeight());
                bindLabel.setPosition((float) (camera.viewportWidth * Math.pow(SCREEN_PARITION,-1.5) + bindButton.getWidth()),
                        camera.viewportHeight/SCREEN_PARITION-(i%(bindPrefixList.size()/2)+1)*bindButton.getHeight());
            }
            bindButton.setBounds(bindButton.getX(),bindButton.getY(),bindButton.getWidth(),bindButton.getHeight());
            stage.addActor(bindButton);
            stage.addActor(bindLabel);
            bindButtonList.add(bindButton);
            bindLabelList.add(bindLabel);
        }

    }


    public Stage getStage(){
        return stage;
    }

    public String getMapFilePath() {
        return mapList.get(mapState);
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
        return cycleRightButton;
    }

    public BBMenuButton getCycleLeftButton(){
        return cycleLeftButton;
    }

    public BBMenuButton getCheckAIButton(){return checkAIButton; }


    public void setBindLabel(int index, String prefix, int keycode){
        bindLabelList.get(index).setText(prefix + Input.Keys.toString(keycode));
    }

    public void toggleCheckBoxDrawable(boolean isAIActive){
        if(isAIActive){
            checkAIButton.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion( new Texture("check.png")));
        }else{
            checkAIButton.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion( new Texture("unchecked.png")));
        }
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

    /*
     * Called whenever the MenuController renders
     * Used to update the view itself
     */
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


