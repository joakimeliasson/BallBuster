package ballbuster.controller;

import ballbuster.model.BBMenuButton;
import ballbuster.model.Player;
import ballbuster.view.MenuView;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
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
 * Created by Matthias on 2015-05-19.
 */
public class MenuController implements ApplicationListener, IMenuController {

    private BallBuster ballBuster;
    private List<Player> playerList;
    private List<Integer> keyList;
    private List<Label> bindLabelList;
    private List<String> bindPrefixList;
    private List<Sprite> mapSprites;
    private List<String> mapList;
    private MenuView menuView;
    private int mapState;
    private boolean isInFocus;
    private Sprite currentMap;
    private BitmapFont font;
    private SpriteBatch batch;
    private Sprite background;
    private final static int NUMBER_OF_PLAYERS = 2;
    private final static float SCREEN_PARITION = 2.2f;


    @Override
    public void create() {

        playerList = new LinkedList<>();
        keyList = new LinkedList<>();
        bindLabelList = new LinkedList<>();
        bindPrefixList = new LinkedList<>();
        mapSprites = new LinkedList<>();
        mapList = new LinkedList<>();

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



        menuView = new MenuView();
        mapState = 0;
        currentMap = mapSprites.get(mapState);
        currentMap.setCenterX(0);
        currentMap.setCenterY((float) (-menuView.getStage().getCamera().viewportHeight*Math.pow(SCREEN_PARITION, -1.5)));
        //math pow -1.1 screen part viewport height

        isInFocus = true;
        font = new BitmapFont(Gdx.files.internal("core/images/test.fnt"));
        font.setScale(0.5f, 0.5f);
        Gdx.input.setInputProcessor(menuView.getStage());

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
        List<BBMenuButton> bindButtonList = new LinkedList<>();

        //playButton
        BBMenuButton playButton = new BBMenuButton(playDrawable, this);
        playButton.setPosition(-playButton.getWidth() / 2, -playButton.getHeight() / 2);
        playButton.setBounds(playButton.getX(), playButton.getY(), playButton.getWidth(), playButton.getHeight());
        menuView.getStage().addActor(playButton);
        playButton.addListener(new ClickListener() {
            @Override public void clicked(InputEvent event, float x, float y){
                if(isInFocus) {
                    ballBuster = new BallBuster(mapList.get(mapState));
                    ballBuster.create();
                    int bindNbr = 0;
                    playerList = ballBuster.getPlayers();
                    //Should not loop more than once, as there are only 2 players
                    for (int i = bindNbr; i < playerList.size(); i++) {
                        playerList.get(i).setKeys(keyList.get(bindNbr), keyList.get(bindNbr + 1), keyList.get(bindNbr + 2), keyList.get(bindNbr + 3), keyList.get(bindNbr + 4), keyList.get(bindNbr + 5));
                        bindNbr = bindNbr + bindPrefixList.size()/2;
                    }
                    isInFocus = false;
                }
            }
        });

        //cycleLeftButton
        BBMenuButton cycleLeftButton = new BBMenuButton(cycleLeftDrawable, this);
        cycleLeftButton.setPosition((float) (-menuView.getStage().getCamera().viewportWidth/Math.pow(SCREEN_PARITION, 1.5)), currentMap.getY()+ currentMap.getHeight()/2);
        menuView.getStage().addActor(cycleLeftButton);
        cycleLeftButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if(isInFocus) {
                    mapState--;
                    if(mapState < 0) {mapState = mapSprites.size() - 1;}
                    mapSprites.get(mapState).setCenterX(currentMap.getX()+currentMap.getWidth()/2);
                    mapSprites.get(mapState).setCenterY(currentMap.getY() +currentMap.getHeight()/2);
                    currentMap = mapSprites.get(mapState);
                }
                return true;
            }
        });

        //cycleRightButton
        BBMenuButton cycleRightButton = new BBMenuButton(cycleRightDrawable, this);
        cycleRightButton.setPosition((float) (menuView.getStage().getCamera().viewportWidth/Math.pow(SCREEN_PARITION, 1.6)), currentMap.getY()+ currentMap.getHeight()/2);
        menuView.getStage().addActor(cycleRightButton);
        cycleRightButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if(isInFocus) {
                    mapState++;
                    mapState = mapState%mapSprites.size();
                    mapSprites.get(mapState).setCenterX(currentMap.getX()+currentMap.getWidth()/2);
                    mapSprites.get(mapState).setCenterY(currentMap.getY() +currentMap.getHeight()/2);
                    currentMap = mapSprites.get(mapState);
                }
                return true;
            }
        });

        //exitButton
        BBMenuButton exitButton = new BBMenuButton(exitDrawable, this);
        exitButton.setPosition(playButton.getX(), playButton.getY() - exitButton.getHeight());
        exitButton.setBounds(exitButton.getX(), exitButton.getY(), exitButton.getWidth(), exitButton.getHeight());
        menuView.getStage().addActor(exitButton);
        exitButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (isInFocus) {
                    Gdx.app.exit();
                }
                return true;
            }
        });


        //Rebind Buttons
        for(int i = 0; i < NUMBER_OF_PLAYERS*bindPrefixList.size()/2; i++) {
            Drawable drawable = null;
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
            BBMenuButton bindButton = new BBMenuButton(drawable, i, this);
            bindButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    if(isInFocus) {
                        Gdx.input.setInputProcessor(bindButton);
                    }
                }
            });

            Label bindLabel = new Label(bindPrefixList.get(i) + KeyCodeMap.valueOf(keyList.get(i)).getHumanName(), new Label.LabelStyle(font, Color.WHITE));
            if(i < bindPrefixList.size()/2) {
                bindButton.setPosition(-menuView.getStage().getCamera().viewportWidth /SCREEN_PARITION,
                        menuView.getStage().getCamera().viewportHeight/SCREEN_PARITION-(i+1)* (bindButton.getHeight()));
                bindLabel.setPosition(-menuView.getStage().getCamera().viewportWidth /SCREEN_PARITION + bindButton.getWidth(),
                        menuView.getStage().getCamera().viewportHeight/SCREEN_PARITION-(i+1)* (bindButton.getHeight()));

            }else if(i < bindPrefixList.size()) {
                bindButton.setPosition((float) (menuView.getStage().getCamera().viewportWidth * Math.pow(SCREEN_PARITION, -1.5)),
                        menuView.getStage().getCamera().viewportHeight/SCREEN_PARITION-((i%(bindPrefixList.size()/2)+1)* (bindButton.getHeight())));
                bindLabel.setPosition((float) (menuView.getStage().getCamera().viewportWidth * Math.pow(SCREEN_PARITION,-1.5) + bindButton.getWidth()),
                        menuView.getStage().getCamera().viewportHeight/SCREEN_PARITION-((i%(bindPrefixList.size()/2)+1)* (bindButton.getHeight())));
            }

            bindButton.setBounds(bindButton.getX(),bindButton.getY(),bindButton.getWidth(),bindButton.getHeight());
            menuView.getStage().addActor(bindButton);
            menuView.getStage().addActor(bindLabel);
            bindButtonList.add(bindButton);
            bindLabelList.add(bindLabel);
        }

        int jump = 0;
        for(int i = 0; i<NUMBER_OF_PLAYERS; i++) {
            Label playerLabel = new Label("Player " + (i+1), new Label.LabelStyle(font, Color.WHITE));
            menuView.getStage().addActor(playerLabel);
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

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void render() {
        if (ballBuster == null) {
            menuView.update(batch, background, currentMap);
        }
        if (ballBuster != null) {
            ballBuster.render();
        }
    }

        @Override
        public void pause () {

        }

        @Override
        public void resume () {

        }

        @Override
        public void dispose () {

        }

    @Override
    public void sendKey(int keycode, int index) {
        keyList.remove(index);
        keyList.add(index, keycode);
        bindLabelList.get(index).setText(bindPrefixList.get(index) + KeyCodeMap.valueOf(keyList.get(index)).getHumanName());
        Gdx.input.setInputProcessor(menuView.getStage());
    }


    /**
     * Key map from com.badlogic.gdx.Input.Keys to custom enumeration;
     * @author Marek Halmo (c) Maniacs Software
     * Got the code from gdx file and used replace
     * (.*)(Keys.(.*), "(.*)"),
     * $1(Keys.$1, "$1"),
     */
    public enum KeyCodeMap {
        ANY_KEY(Input.Keys.ANY_KEY, "N/A"),
        NUM_0(Input.Keys.NUM_0, "NUM_0"),
        NUM_1(Input.Keys.NUM_1, "NUM_1"),
        NUM_2(Input.Keys.NUM_2, "NUM_2"),
        NUM_3(Input.Keys.NUM_3, "NUM_3"),
        NUM_4(Input.Keys.NUM_4, "NUM_4"),
        NUM_5(Input.Keys.NUM_5, "NUM_5"),
        NUM_6(Input.Keys.NUM_6, "NUM_6"),
        NUM_7(Input.Keys.NUM_7, "NUM_7"),
        NUM_8(Input.Keys.NUM_8, "NUM_8"),
        NUM_9(Input.Keys.NUM_9, "NUM_9"),
        A(Input.Keys.A, "A"),
        ALT_LEFT(Input.Keys.ALT_LEFT, "ALT_LEFT"),
        ALT_RIGHT(Input.Keys.ALT_RIGHT, "ALT_RIGHT"),
        APOSTROPHE(Input.Keys.APOSTROPHE, "APOSTROPHE"),
        AT(Input.Keys.AT, "AT"),
        B(Input.Keys.B, "B"),
        BACK(Input.Keys.BACK, "BACK"),
        BACKSLASH(Input.Keys.BACKSLASH, "BACKSLASH"),
        C(Input.Keys.C, "C"),
        CALL(Input.Keys.CALL, "CALL"),
        CAMERA(Input.Keys.CAMERA, "CAMERA"),
        CLEAR(Input.Keys.CLEAR, "CLEAR"),
        COMMA(Input.Keys.COMMA, "COMMA"),
        D(Input.Keys.D, "D"),
        DEL(Input.Keys.DEL, "DEL"),
        BACKSPACE(Input.Keys.BACKSPACE, "BACKSPACE"),
        FORWARD_DEL(Input.Keys.FORWARD_DEL, "FORWARD_DEL"),
        DPAD_CENTER(Input.Keys.DPAD_CENTER, "DPAD_CENTER"),
        DPAD_DOWN(Input.Keys.DPAD_DOWN, "DPAD_DOWN"),
        DPAD_LEFT(Input.Keys.DPAD_LEFT, "DPAD_LEFT"),
        DPAD_RIGHT(Input.Keys.DPAD_RIGHT, "DPAD_RIGHT"),
        DPAD_UP(Input.Keys.DPAD_UP, "DPAD_UP"),
        CENTER(Input.Keys.CENTER, "CENTER"),
        DOWN(Input.Keys.DOWN, "DOWN"),
        LEFT(Input.Keys.LEFT, "LEFT"),
        RIGHT(Input.Keys.RIGHT, "RIGHT"),
        UP(Input.Keys.UP, "UP"),
        E(Input.Keys.E, "E"),
        ENDCALL(Input.Keys.ENDCALL, "ENDCALL"),
        ENTER(Input.Keys.ENTER, "ENTER"),
        ENVELOPE(Input.Keys.ENVELOPE, "ENVELOPE"),
        EQUALS(Input.Keys.EQUALS, "EQUALS"),
        EXPLORER(Input.Keys.EXPLORER, "EXPLORER"),
        F(Input.Keys.F, "F"),
        FOCUS(Input.Keys.FOCUS, "FOCUS"),
        G(Input.Keys.G, "G"),
        GRAVE(Input.Keys.GRAVE, "GRAVE"),
        H(Input.Keys.H, "H"),
        HEADSETHOOK(Input.Keys.HEADSETHOOK, "HEADSETHOOK"),
        HOME(Input.Keys.HOME, "HOME"),
        I(Input.Keys.I, "I"),
        J(Input.Keys.J, "J"),
        K(Input.Keys.K, "K"),
        L(Input.Keys.L, "L"),
        LEFT_BRACKET(Input.Keys.LEFT_BRACKET, "LEFT_BRACKET"),
        M(Input.Keys.M, "M"),
        MEDIA_FAST_FORWARD(Input.Keys.MEDIA_FAST_FORWARD, "MEDIA_FAST_FORWARD"),
        MEDIA_NEXT(Input.Keys.MEDIA_NEXT, "MEDIA_NEXT"),
        MEDIA_PLAY_PAUSE(Input.Keys.MEDIA_PLAY_PAUSE, "MEDIA_PLAY_PAUSE"),
        MEDIA_PREVIOUS(Input.Keys.MEDIA_PREVIOUS, "MEDIA_PREVIOUS"),
        MEDIA_REWIND(Input.Keys.MEDIA_REWIND, "MEDIA_REWIND"),
        MEDIA_STOP(Input.Keys.MEDIA_STOP, "MEDIA_STOP"),
        MENU(Input.Keys.MENU, "MENU"),
        MINUS(Input.Keys.MINUS, "MINUS"),
        MUTE(Input.Keys.MUTE, "MUTE"),
        N(Input.Keys.N, "N"),
        NOTIFICATION(Input.Keys.NOTIFICATION, "NOTIFICATION"),
        NUM(Input.Keys.NUM, "NUM"),
        O(Input.Keys.O, "O"),
        P(Input.Keys.P, "P"),
        PERIOD(Input.Keys.PERIOD, "PERIOD"),
        PLUS(Input.Keys.PLUS, "PLUS"),
        POUND(Input.Keys.POUND, "POUND"),
        POWER(Input.Keys.POWER, "POWER"),
        Q(Input.Keys.Q, "Q"),
        R(Input.Keys.R, "R"),
        RIGHT_BRACKET(Input.Keys.RIGHT_BRACKET, "RIGHT_BRACKET"),
        S(Input.Keys.S, "S"),
        SEARCH(Input.Keys.SEARCH, "SEARCH"),
        SEMICOLON(Input.Keys.SEMICOLON, "SEMICOLON"),
        SHIFT_LEFT(Input.Keys.SHIFT_LEFT, "SHIFT_LEFT"),
        SHIFT_RIGHT(Input.Keys.SHIFT_RIGHT, "SHIFT_RIGHT"),
        SLASH(Input.Keys.SLASH, "SLASH"),
        SOFT_LEFT(Input.Keys.SOFT_LEFT, "SOFT_LEFT"),
        SOFT_RIGHT(Input.Keys.SOFT_RIGHT, "SOFT_RIGHT"),
        SPACE(Input.Keys.SPACE, "SPACE"),
        STAR(Input.Keys.STAR, "STAR"),
        SYM(Input.Keys.SYM, "SYM"),
        T(Input.Keys.T, "T"),
        TAB(Input.Keys.TAB, "TAB"),
        U(Input.Keys.U, "U"),
        UNKNOWN(Input.Keys.UNKNOWN, "UNKNOWN"),
        V(Input.Keys.V, "V"),
        VOLUME_DOWN(Input.Keys.VOLUME_DOWN, "VOLUME_DOWN"),
        VOLUME_UP(Input.Keys.VOLUME_UP, "VOLUME_UP"),
        W(Input.Keys.W, "W"),
        X(Input.Keys.X, "X"),
        Y(Input.Keys.Y, "Y"),
        Z(Input.Keys.Z, "Z"),
        /*META_ALT_LEFT_ON(Keys.META_ALT_LEFT_ON, "META_ALT_LEFT_ON"),
        META_ALT_ON(Keys.META_ALT_ON, "META_ALT_ON"),
        META_ALT_RIGHT_ON(Keys.META_ALT_RIGHT_ON, "META_ALT_RIGHT_ON"),
        META_SHIFT_LEFT_ON(Keys.META_SHIFT_LEFT_ON, "META_SHIFT_LEFT_ON"),
        META_SHIFT_ON(Keys.META_SHIFT_ON, "META_SHIFT_ON"),
        META_SHIFT_RIGHT_ON(Keys.META_SHIFT_RIGHT_ON, "META_SHIFT_RIGHT_ON"),
        META_SYM_ON(Keys.META_SYM_ON, "META_SYM_ON"),*/
        CONTROL_LEFT(Input.Keys.CONTROL_LEFT, "CONTROL_LEFT"),
        CONTROL_RIGHT(Input.Keys.CONTROL_RIGHT, "CONTROL_RIGHT"),
        ESCAPE(Input.Keys.ESCAPE, "ESCAPE"),
        END(Input.Keys.END, "END"),
        INSERT(Input.Keys.INSERT, "INSERT"),
        PAGE_UP(Input.Keys.PAGE_UP, "PAGE_UP"),
        PAGE_DOWN(Input.Keys.PAGE_DOWN, "PAGE_DOWN"),
        PICTSYMBOLS(Input.Keys.PICTSYMBOLS, "PICTSYMBOLS"),
        SWITCH_CHARSET(Input.Keys.SWITCH_CHARSET, "SWITCH_CHARSET"),
        BUTTON_CIRCLE(Input.Keys.BUTTON_CIRCLE, "BUTTON_CIRCLE"),
        BUTTON_A(Input.Keys.BUTTON_A, "BUTTON_A"),
        BUTTON_B(Input.Keys.BUTTON_B, "BUTTON_B"),
        BUTTON_C(Input.Keys.BUTTON_C, "BUTTON_C"),
        BUTTON_X(Input.Keys.BUTTON_X, "BUTTON_X"),
        BUTTON_Y(Input.Keys.BUTTON_Y, "BUTTON_Y"),
        BUTTON_Z(Input.Keys.BUTTON_Z, "BUTTON_Z"),
        BUTTON_L1(Input.Keys.BUTTON_L1, "BUTTON_L1"),
        BUTTON_R1(Input.Keys.BUTTON_R1, "BUTTON_R1"),
        BUTTON_L2(Input.Keys.BUTTON_L2, "BUTTON_L2"),
        BUTTON_R2(Input.Keys.BUTTON_R2, "BUTTON_R2"),
        BUTTON_THUMBL(Input.Keys.BUTTON_THUMBL, "BUTTON_THUMBL"),
        BUTTON_THUMBR(Input.Keys.BUTTON_THUMBR, "BUTTON_THUMBR"),
        BUTTON_START(Input.Keys.BUTTON_START, "BUTTON_START"),
        BUTTON_SELECT(Input.Keys.BUTTON_SELECT, "BUTTON_SELECT"),
        BUTTON_MODE(Input.Keys.BUTTON_MODE, "BUTTON_MODE"),

        //BACKTICK(Keys.BACKTICK, "BACKTICK"),
        //TILDE(Keys.TILDE, "TILDE"),
        //UNDERSCORE(Keys.UNDERSCORE, "UNDERSCORE"),
        //DOT(Keys.DOT, "DOT"),
        //BREAK(Keys.BREAK, "BREAK"),
        //PIPE(Keys.PIPE, "PIPE"),
        //EXCLAMATION(Keys.EXCLAMATION, "EXCLAMATION"),
        //QUESTIONMARK(Keys.QUESTIONMARK, "QUESTIONMARK"),

        //` | VK_BACKTICK
        //~ | VK_TILDE
        //: | VK_COLON
        //_ | VK_UNDERSCORE
        //. | VK_DOT
        //(break) | VK_BREAK
        //| | VK_PIPE
        //! | VK_EXCLAMATION
        //? | VK_QUESTION

        COLON(Input.Keys.COLON, "COLON"),
        F1(Input.Keys.F1, "F1"),
        F2(Input.Keys.F2, "F2"),
        F3(Input.Keys.F3, "F3"),
        F4(Input.Keys.F4, "F4"),
        F5(Input.Keys.F5, "F5"),
        F6(Input.Keys.F6, "F6"),
        F7(Input.Keys.F7, "F7"),
        F8(Input.Keys.F8, "F8"),
        F9(Input.Keys.F9, "F9"),
        F10(Input.Keys.F10, "F10"),
        F11(Input.Keys.F11, "F11"),
        F12(Input.Keys.F12, "F12"),

        MB_LEFT(Input.Buttons.LEFT, "MB_LEFT"),
        MB_RIGHT(Input.Buttons.RIGHT, "MB_RIGHT"),
        MB_MIDDLE(Input.Buttons.MIDDLE, "MB_MIDDLE");

        // On first run this cache will be populated with all keycodes >= 0
        private static KeyCodeMap[] kyecodeCache;

        static {
            // first we determine whats the highest keycode, in current build it's 256
            int highestInt = 0;
            for (KeyCodeMap code : KeyCodeMap.values()) {
                if (code.getKeyCode() > highestInt) {
                    highestInt = code.keyCode;
                }
            }

            // we allocate keycodeCache which is direct link - keyCode as index gives value
            kyecodeCache = new KeyCodeMap[highestInt + 1];

            // populate keycode cache with all the codes
            for (KeyCodeMap code : KeyCodeMap.values()) {
                if (code.getKeyCode() >= 0)
                    kyecodeCache[code.getKeyCode()] = code;
            }
        }


        private int keyCode;
        private String humanName;

        KeyCodeMap(int keyCode, String humanName) {
            this.keyCode = keyCode;
            this.humanName = humanName;
        }

        /**
         * Returns KeyCodeMap enum based on the code from input.Keys
         *
         * @param intCode
         * @return
         */
        public static KeyCodeMap valueOf(int intCode) {
            if (intCode == -1)
                return ANY_KEY;

            if (intCode < 0 || intCode > values().length
                    || kyecodeCache[intCode] == null)
                return UNKNOWN;

            return kyecodeCache[intCode];
        }

        /**
         * Returns the original keycode from input.Keys
         *
         * @return
         */
        public int getKeyCode() {
            return keyCode;
        }

        /**
         * Returns the human name of this key code
         * you can also use .toString() but this value is fully customizable!
         *
         * @return
         */
        public String getHumanName() {
            return humanName;
        }

        /**
         * Allows you to change the human name/text returned by getHumanName()
         *
         * @param newHumanName
         */
        public void setHumanName(String newHumanName) {
            this.humanName = newHumanName;
        }

    }
}
