package ballbuster.view;

import ballbuster.controller.BallBuster;
import ballbuster.model.Player;
import com.badlogic.gdx.*;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import javafx.scene.text.Text;

import javax.swing.text.Position;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static com.badlogic.gdx.Input.Keys.*;

/**
 * Created by jacobth on 2015-05-08.
 */
public class MenuView implements ApplicationListener{

    //TODO should this be input processor?
    private Sprite background;
    private Sprite currentMap;
    private SpriteBatch batch;
    private BBMenuButton playButton;
    private Stage thisStage;
    private ArrayList<Texture> mapList;
    private BallBuster ballBuster;
    private List<Player> playerList;
    private List<Integer> keyList;
    private List<Label> bindLabelList;
    private List<String> bindPrefixList;
    private List<Sprite> mapSprites;
    private int mapState;
    private boolean isInFocus;
    private final float DEFAULT_ALPHA = 1f;
    private final float MOUSEOVER_ALPHA = 0.75f;
    private final float CLICKED_ALPHA = 0.5f;
    private final int NUMBER_OF_PLAYERS = 2;


    private class BBMenuButton extends ImageButton implements InputProcessor{

        private int buttonIndex;
        private float alpha = 1f;
        private boolean pressed = false;

        BBMenuButton(Drawable imageup){
            super(imageup);
        }

        BBMenuButton(Drawable imageup, Integer buttonIndex){
            super(imageup);
            this.buttonIndex = buttonIndex;

        }

        public void setAlpha(float alpha){
            this.alpha = alpha;
        }

        public float getAlpha(){
            return alpha;
        }

        @Override
        public boolean keyUp(int keycode) {
            keyList.remove(buttonIndex);
            keyList.add(buttonIndex, keycode);
            bindLabelList.get(buttonIndex).setText(bindPrefixList.get(buttonIndex) + KeyCodeMap.valueOf(keyList.get(buttonIndex)).getHumanName());
            Gdx.input.setInputProcessor(thisStage);
            return true;
        }

        @Override
        public boolean keyDown(int keycode) {return false;}
        @Override
        public boolean keyTyped(char character) {return false;}
        @Override
        public boolean touchDown(int screenX, int screenY, int pointer, int button) {return false;}
        @Override
        public boolean touchUp(int screenX, int screenY, int pointer, int button) {return false;}
        @Override
        public boolean touchDragged(int screenX, int screenY, int pointer) {return false;}
        @Override
        public boolean mouseMoved(int screenX, int screenY) {return false;}
        @Override
        public boolean scrolled(int amount) {return false;}
    }


    private BitmapFont bindFont;


    @Override
    public void create() {

        thisStage = new Stage();

        //Add map sprites to list
        mapSprites = new LinkedList<>();
        mapSprites.add(new Sprite(new Texture("core/images/tempTexture.png")));
        mapSprites.add(new Sprite(new Texture("core/images/normal.png")));
        mapSprites.add(new Sprite(new Texture("core/images/playershield.png")));

        currentMap = mapSprites.get(0);
        currentMap.setCenterX(Gdx.graphics.getWidth() / 2);
        currentMap.setCenterY(Gdx.graphics.getHeight()/4);
        mapState = 0;





        isInFocus = true;
        bindFont = new BitmapFont(Gdx.files.internal("core/images/test.fnt"));
        bindFont.setScale(0.5f,0.5f);
        Gdx.input.setInputProcessor(thisStage);
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
        final Drawable bindButtonDrawable = new TextureRegionDrawable(new TextureRegion(
                new Texture(Gdx.files.internal("core/images/tempbind.png"))));
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
        //Default keys for players
        keyList = new LinkedList<>();
        //Player 1 keys
        keyList.add(Keys.D);
        keyList.add(Keys.A);
        keyList.add(Keys.W);
        keyList.add(Keys.S);
        keyList.add(Keys.ALT_LEFT);

        //Player 2 keys
        keyList.add(Keys.DPAD_RIGHT);
        keyList.add(Keys.DPAD_LEFT);
        keyList.add(Keys.DPAD_UP);
        keyList.add(Keys.DPAD_DOWN);
        keyList.add(Keys.SPACE);

        bindPrefixList = new LinkedList<>();
        bindPrefixList.add("RightKey:");
        bindPrefixList.add("LeftKey:");
        bindPrefixList.add("UpKey:");
        bindPrefixList.add("DownKey:");
        bindPrefixList.add("AuraKey:");
        bindPrefixList.addAll(bindPrefixList);
        bindLabelList = new LinkedList<>();
        List<BBMenuButton> bindButtonList = new LinkedList<>();

        //TODO set correct resources when available
        final int DIVIDE_SCREEN = 50;
        //playButton
        playButton = new BBMenuButton(playDrawable, -1);
        playButton.setPosition(Gdx.graphics.getWidth() / 2 - playButton.getWidth() / 2, Gdx.graphics.getHeight() / 2 - playButton.getHeight() / 2);
        ;
        //Add proper bounds value
        playButton.setBounds(playButton.getX(), playButton.getY(), 300, 400);
        thisStage.addActor(playButton);

        //Listener for playButton
        playButton.addListener(new ClickListener() {
            @Override public void clicked(InputEvent event, float x, float y){
                if(isInFocus) {
                    playButton.setAlpha(CLICKED_ALPHA);
                    ballBuster = new BallBuster();
                    ballBuster.create();
                    int bindNbr = 0;
                    playerList = ballBuster.getPlayers();
                    //Should not loop more than once, as there are only 2 players
                    for (int i = bindNbr; i < playerList.size(); i++) {
                        playerList.get(i).setKeys(keyList.get(bindNbr), keyList.get(bindNbr + 1), keyList.get(bindNbr + 2), keyList.get(bindNbr + 3), keyList.get(bindNbr + 4));
                        bindNbr = bindNbr + 5;
                    }
                    isInFocus = false;
                }
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor actor){
                playButton.setAlpha(MOUSEOVER_ALPHA);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor actor){
                playButton.setAlpha(DEFAULT_ALPHA);
            }
        });

        //cycleLeftButton
        BBMenuButton cycleLeftButton = new BBMenuButton(cycleLeftDrawable);
        cycleLeftButton.setPosition(Gdx.graphics.getWidth() / 2 - 3 * playButton.getWidth() / 2, Gdx.graphics.getHeight() / 5);
        thisStage.addActor(cycleLeftButton);
        cycleLeftButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if(isInFocus) {
                    mapState--;
                    if(mapState < 0) {mapState = mapSprites.size() - 1;}
                    currentMap = mapSprites.get(mapState);
                    currentMap.setCenterX(Gdx.graphics.getWidth() / 2);
                    currentMap.setCenterY(Gdx.graphics.getHeight()/4);
                }
                return true;
            }
        });
        //cycleRightButton


        BBMenuButton cycleRightButton = new BBMenuButton(cycleRightDrawable);
        cycleRightButton.setPosition(Gdx.graphics.getWidth() / 2 + 3 * playButton.getWidth() / 2, Gdx.graphics.getHeight() / 5);
        thisStage.addActor(cycleRightButton);
        cycleRightButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if(isInFocus) {
                    mapState++;
                    mapState = mapState%mapSprites.size();
                    currentMap = mapSprites.get(mapState);
                    currentMap.setCenterX(Gdx.graphics.getWidth() / 2);
                    currentMap.setCenterY(Gdx.graphics.getHeight()/4);
                }
                return true;
            }
        });

        //exitButton
        BBMenuButton exitButton = new BBMenuButton(exitDrawable);
        exitButton.setPosition(playButton.getX(), playButton.getY());
        System.out.println(playButton.getX()+" " + exitButton.getX());
        exitButton.setBounds(exitButton.getX(), exitButton.getY(), exitButton.getWidth(), exitButton.getHeight());
        thisStage.addActor(exitButton);
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
        for(int i = 0; i < NUMBER_OF_PLAYERS*5; i++) {
            Drawable drawable = null;
            switch(i) {
                case 0:
                    drawable = leftBindButtonDrawable;
                    break;
                case 1:
                    drawable = rightBindButtonDrawable;
                    break;
                case 2:
                    drawable = downBindButtonDrawable;
                    break;
                case 3:
                    drawable = upBindButtonDrawable;
                    break;
                case 4:
                    drawable = auraBindButtonDrawable;

            }
            BBMenuButton bindButton = new BBMenuButton(drawable, i);
            bindButton.addListener(new ClickListener() {

                @Override
                public void clicked(InputEvent event, float x, float y) {
                    if(isInFocus) {
                        bindButton.pressed = true;
                        Gdx.input.setInputProcessor(bindButton);
                        for (Integer key : keyList) {
                            System.out.println(KeyCodeMap.valueOf(key).getHumanName());
                        }
                    }
                }

            });

            Label bindLabel = new Label(bindPrefixList.get(i) + KeyCodeMap.valueOf(keyList.get(i)).getHumanName(), new Label.LabelStyle(bindFont, Color.WHITE));
            if(i < 5) {
                //TODO calculations for positions
                bindButton.setPosition(Gdx.graphics.getWidth() / DIVIDE_SCREEN, Gdx.graphics.getHeight() - i * (bindButton.getHeight()));
                bindLabel.setPosition(Gdx.graphics.getWidth() / DIVIDE_SCREEN + bindButton.getWidth(), Gdx.graphics.getHeight() - i * (bindButton.getHeight()));

            }else if(i < 10) {
                //TODO calculations for positions
                bindButton.setPosition(Gdx.graphics.getWidth() - bindButton.getWidth(), Gdx.graphics.getHeight() -(i-5)*(bindButton.getHeight()));
                bindLabel.setPosition(Gdx.graphics.getWidth() - bindButton.getWidth() + bindButton.getWidth(), Gdx.graphics.getHeight()-(i-5)*(bindButton.getHeight()));
            }
            bindButton.setBounds(bindButton.getX(),bindButton.getY(),bindButton.getWidth(),bindButton.getHeight());
            thisStage.addActor(bindButton);
            thisStage.addActor(bindLabel);
            bindButtonList.add(bindButton);
            bindLabelList.add(bindLabel);
        }




    }

    @Override
    public void resize(int width, int height) {
        for(Actor actor: thisStage.getActors()){
            actor.setBounds(actor.getX(), actor.getY(), actor.getWidth(), actor.getHeight());
        }
        batch.getProjectionMatrix().setToOrtho2D( 0, 0, width, height);
    }

    @Override
    public void render() {
        if(ballBuster==null) {
            //setMapSprite();
            batch.begin();
            batch.draw(background, 0, 0);

            for(Actor a: thisStage.getActors()){
                if(a.getClass() == BBMenuButton.class){
                    ((BBMenuButton)a).draw(batch, ((BBMenuButton)a).getAlpha());
                }
                if(a.getClass() == Label.class){
                    ((Label)a).draw(batch, DEFAULT_ALPHA);
                }
            }
            currentMap.draw(batch);
            batch.end();
        }

        if(ballBuster!=null) {

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







    /**
     * Key map from com.badlogic.gdx.Input.Keys to custom enumeration;
     * @author Marek Halmo (c) Maniacs Software
     * Got the code from gdx file and used replace
     * (.*)(Keys.(.*), "(.*)"),
     * $1(Keys.$1, "$1"),
     */
    public enum KeyCodeMap {
        ANY_KEY(Keys.ANY_KEY, "N/A"),
        NUM_0(Keys.NUM_0, "NUM_0"),
        NUM_1(Keys.NUM_1, "NUM_1"),
        NUM_2(Keys.NUM_2, "NUM_2"),
        NUM_3(Keys.NUM_3, "NUM_3"),
        NUM_4(Keys.NUM_4, "NUM_4"),
        NUM_5(Keys.NUM_5, "NUM_5"),
        NUM_6(Keys.NUM_6, "NUM_6"),
        NUM_7(Keys.NUM_7, "NUM_7"),
        NUM_8(Keys.NUM_8, "NUM_8"),
        NUM_9(Keys.NUM_9, "NUM_9"),
        A(Keys.A, "A"),
        ALT_LEFT(Keys.ALT_LEFT, "ALT_LEFT"),
        ALT_RIGHT(Keys.ALT_RIGHT, "ALT_RIGHT"),
        APOSTROPHE(Keys.APOSTROPHE, "APOSTROPHE"),
        AT(Keys.AT, "AT"),
        B(Keys.B, "B"),
        BACK(Keys.BACK, "BACK"),
        BACKSLASH(Keys.BACKSLASH, "BACKSLASH"),
        C(Keys.C, "C"),
        CALL(Keys.CALL, "CALL"),
        CAMERA(Keys.CAMERA, "CAMERA"),
        CLEAR(Keys.CLEAR, "CLEAR"),
        COMMA(Keys.COMMA, "COMMA"),
        D(Keys.D, "D"),
        DEL(Keys.DEL, "DEL"),
        BACKSPACE(Keys.BACKSPACE, "BACKSPACE"),
        FORWARD_DEL(Keys.FORWARD_DEL, "FORWARD_DEL"),
        DPAD_CENTER(Keys.DPAD_CENTER, "DPAD_CENTER"),
        DPAD_DOWN(Keys.DPAD_DOWN, "DPAD_DOWN"),
        DPAD_LEFT(Keys.DPAD_LEFT, "DPAD_LEFT"),
        DPAD_RIGHT(Keys.DPAD_RIGHT, "DPAD_RIGHT"),
        DPAD_UP(Keys.DPAD_UP, "DPAD_UP"),
        CENTER(Keys.CENTER, "CENTER"),
        DOWN(Keys.DOWN, "DOWN"),
        LEFT(Keys.LEFT, "LEFT"),
        RIGHT(Keys.RIGHT, "RIGHT"),
        UP(Keys.UP, "UP"),
        E(Keys.E, "E"),
        ENDCALL(Keys.ENDCALL, "ENDCALL"),
        ENTER(Keys.ENTER, "ENTER"),
        ENVELOPE(Keys.ENVELOPE, "ENVELOPE"),
        EQUALS(Keys.EQUALS, "EQUALS"),
        EXPLORER(Keys.EXPLORER, "EXPLORER"),
        F(Keys.F, "F"),
        FOCUS(Keys.FOCUS, "FOCUS"),
        G(Keys.G, "G"),
        GRAVE(Keys.GRAVE, "GRAVE"),
        H(Keys.H, "H"),
        HEADSETHOOK(Keys.HEADSETHOOK, "HEADSETHOOK"),
        HOME(Keys.HOME, "HOME"),
        I(Keys.I, "I"),
        J(Keys.J, "J"),
        K(Keys.K, "K"),
        L(Keys.L, "L"),
        LEFT_BRACKET(Keys.LEFT_BRACKET, "LEFT_BRACKET"),
        M(Keys.M, "M"),
        MEDIA_FAST_FORWARD(Keys.MEDIA_FAST_FORWARD, "MEDIA_FAST_FORWARD"),
        MEDIA_NEXT(Keys.MEDIA_NEXT, "MEDIA_NEXT"),
        MEDIA_PLAY_PAUSE(Keys.MEDIA_PLAY_PAUSE, "MEDIA_PLAY_PAUSE"),
        MEDIA_PREVIOUS(Keys.MEDIA_PREVIOUS, "MEDIA_PREVIOUS"),
        MEDIA_REWIND(Keys.MEDIA_REWIND, "MEDIA_REWIND"),
        MEDIA_STOP(Keys.MEDIA_STOP, "MEDIA_STOP"),
        MENU(Keys.MENU, "MENU"),
        MINUS(Keys.MINUS, "MINUS"),
        MUTE(Keys.MUTE, "MUTE"),
        N(Keys.N, "N"),
        NOTIFICATION(Keys.NOTIFICATION, "NOTIFICATION"),
        NUM(Keys.NUM, "NUM"),
        O(Keys.O, "O"),
        P(Keys.P, "P"),
        PERIOD(Keys.PERIOD, "PERIOD"),
        PLUS(Keys.PLUS, "PLUS"),
        POUND(Keys.POUND, "POUND"),
        POWER(Keys.POWER, "POWER"),
        Q(Keys.Q, "Q"),
        R(Keys.R, "R"),
        RIGHT_BRACKET(Keys.RIGHT_BRACKET, "RIGHT_BRACKET"),
        S(Keys.S, "S"),
        SEARCH(Keys.SEARCH, "SEARCH"),
        SEMICOLON(Keys.SEMICOLON, "SEMICOLON"),
        SHIFT_LEFT(Keys.SHIFT_LEFT, "SHIFT_LEFT"),
        SHIFT_RIGHT(Keys.SHIFT_RIGHT, "SHIFT_RIGHT"),
        SLASH(Keys.SLASH, "SLASH"),
        SOFT_LEFT(Keys.SOFT_LEFT, "SOFT_LEFT"),
        SOFT_RIGHT(Keys.SOFT_RIGHT, "SOFT_RIGHT"),
        SPACE(Keys.SPACE, "SPACE"),
        STAR(Keys.STAR, "STAR"),
        SYM(Keys.SYM, "SYM"),
        T(Keys.T, "T"),
        TAB(Keys.TAB, "TAB"),
        U(Keys.U, "U"),
        UNKNOWN(Keys.UNKNOWN, "UNKNOWN"),
        V(Keys.V, "V"),
        VOLUME_DOWN(Keys.VOLUME_DOWN, "VOLUME_DOWN"),
        VOLUME_UP(Keys.VOLUME_UP, "VOLUME_UP"),
        W(Keys.W, "W"),
        X(Keys.X, "X"),
        Y(Keys.Y, "Y"),
        Z(Keys.Z, "Z"),
        /*META_ALT_LEFT_ON(Keys.META_ALT_LEFT_ON, "META_ALT_LEFT_ON"),
        META_ALT_ON(Keys.META_ALT_ON, "META_ALT_ON"),
        META_ALT_RIGHT_ON(Keys.META_ALT_RIGHT_ON, "META_ALT_RIGHT_ON"),
        META_SHIFT_LEFT_ON(Keys.META_SHIFT_LEFT_ON, "META_SHIFT_LEFT_ON"),
        META_SHIFT_ON(Keys.META_SHIFT_ON, "META_SHIFT_ON"),
        META_SHIFT_RIGHT_ON(Keys.META_SHIFT_RIGHT_ON, "META_SHIFT_RIGHT_ON"),
        META_SYM_ON(Keys.META_SYM_ON, "META_SYM_ON"),*/
        CONTROL_LEFT(Keys.CONTROL_LEFT, "CONTROL_LEFT"),
        CONTROL_RIGHT(Keys.CONTROL_RIGHT, "CONTROL_RIGHT"),
        ESCAPE(Keys.ESCAPE, "ESCAPE"),
        END(Keys.END, "END"),
        INSERT(Keys.INSERT, "INSERT"),
        PAGE_UP(Keys.PAGE_UP, "PAGE_UP"),
        PAGE_DOWN(Keys.PAGE_DOWN, "PAGE_DOWN"),
        PICTSYMBOLS(Keys.PICTSYMBOLS, "PICTSYMBOLS"),
        SWITCH_CHARSET(Keys.SWITCH_CHARSET, "SWITCH_CHARSET"),
        BUTTON_CIRCLE(Keys.BUTTON_CIRCLE, "BUTTON_CIRCLE"),
        BUTTON_A(Keys.BUTTON_A, "BUTTON_A"),
        BUTTON_B(Keys.BUTTON_B, "BUTTON_B"),
        BUTTON_C(Keys.BUTTON_C, "BUTTON_C"),
        BUTTON_X(Keys.BUTTON_X, "BUTTON_X"),
        BUTTON_Y(Keys.BUTTON_Y, "BUTTON_Y"),
        BUTTON_Z(Keys.BUTTON_Z, "BUTTON_Z"),
        BUTTON_L1(Keys.BUTTON_L1, "BUTTON_L1"),
        BUTTON_R1(Keys.BUTTON_R1, "BUTTON_R1"),
        BUTTON_L2(Keys.BUTTON_L2, "BUTTON_L2"),
        BUTTON_R2(Keys.BUTTON_R2, "BUTTON_R2"),
        BUTTON_THUMBL(Keys.BUTTON_THUMBL, "BUTTON_THUMBL"),
        BUTTON_THUMBR(Keys.BUTTON_THUMBR, "BUTTON_THUMBR"),
        BUTTON_START(Keys.BUTTON_START, "BUTTON_START"),
        BUTTON_SELECT(Keys.BUTTON_SELECT, "BUTTON_SELECT"),
        BUTTON_MODE(Keys.BUTTON_MODE, "BUTTON_MODE"),

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

        COLON(Keys.COLON, "COLON"),
        F1(Keys.F1, "F1"),
        F2(Keys.F2, "F2"),
        F3(Keys.F3, "F3"),
        F4(Keys.F4, "F4"),
        F5(Keys.F5, "F5"),
        F6(Keys.F6, "F6"),
        F7(Keys.F7, "F7"),
        F8(Keys.F8, "F8"),
        F9(Keys.F9, "F9"),
        F10(Keys.F10, "F10"),
        F11(Keys.F11, "F11"),
        F12(Keys.F12, "F12"),

        MB_LEFT(Input.Buttons.LEFT, "MB_LEFT"),
        MB_RIGHT(Input.Buttons.RIGHT, "MB_RIGHT"),
        MB_MIDDLE(Input.Buttons.MIDDLE, "MB_MIDDLE");

        // On first run this cache will be populated with all keycodes >= 0
        private static KeyCodeMap[] kyecodeCache;

        static {
            // first we determine whats the highest keycode, in current build it's 256
            int highestInt = 0;
            for(KeyCodeMap code: KeyCodeMap.values()) {
                if (code.getKeyCode() > highestInt) {
                    highestInt = code.keyCode;
                }
            }

            // we allocate keycodeCache which is direct link - keyCode as index gives value
            kyecodeCache = new KeyCodeMap[highestInt + 1];

            // populate keycode cache with all the codes
            for(KeyCodeMap code: KeyCodeMap.values()) {
                if(code.getKeyCode() >= 0)
                    kyecodeCache[code.getKeyCode()] = code;
            }
        }


        private int keyCode;
        private String humanName;

        private KeyCodeMap(int keyCode, String humanName) {
            this.keyCode = keyCode;
            this.humanName = humanName;
        }

        /**
         * Returns KeyCodeMap enum based on the code from input.Keys
         * @param intCode
         * @return
         */
        public static KeyCodeMap valueOf(int intCode)
        {
            if(intCode == -1)
                return ANY_KEY;

            if(intCode < 0 || intCode > values().length
                    || kyecodeCache[intCode] == null)
                return UNKNOWN;

            return kyecodeCache[intCode];
        }

        /**
         * Returns the original keycode from input.Keys
         * @return
         */
        public int getKeyCode() {
            return keyCode;
        }

        /**
         * Returns the human name of this key code
         * you can also use .toString() but this value is fully customizable!
         * @return
         */
        public String getHumanName() {
            return humanName;
        }

        /**
         * Allows you to change the human name/text returned by getHumanName()
         * @param newHumanName
         */
        public void setHumanName(String newHumanName) {
            this.humanName = newHumanName;
        }
    }
}


