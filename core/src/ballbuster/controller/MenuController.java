package ballbuster.controller;

import ballbuster.model.BBMenuButton;
import ballbuster.model.Player;
import ballbuster.view.MenuView;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Matthias on 2015-05-19.
 */
public class MenuController implements ApplicationListener, InputProcessor {


    private List<Player> playerList;
    private List<Integer> keyList;
    private List<String> keyPrefixList;
    private BallBuster ballBuster;
    private final int NO_INDEX = -1;
    private final MenuController thisController = this;
    private MenuView menuView;
    private int currentBindIndex = NO_INDEX;
    private boolean isInFocus = true;
    private boolean isAIActive = false;
    private final int NUMBER_OF_PLAYERS = 2;





    @Override
    public void create() {


        //Default keys for players
        keyList = new LinkedList<>();
        //Player 1 keys
        keyList.add(Input.Keys.W);
        keyList.add(Input.Keys.A);
        keyList.add(Input.Keys.S);
        keyList.add(Input.Keys.D);
        keyList.add(Input.Keys.ALT_LEFT);
        keyList.add(Input.Keys.Q);

        //Player 2 keys
        keyList.add(Input.Keys.DPAD_UP);
        keyList.add(Input.Keys.DPAD_LEFT);
        keyList.add(Input.Keys.DPAD_DOWN);
        keyList.add(Input.Keys.DPAD_RIGHT);
        keyList.add(Input.Keys.SPACE);
        keyList.add(Input.Keys.M);

        keyPrefixList = new LinkedList<>();
        keyPrefixList.add("UpKey:");
        keyPrefixList.add("LeftKey:");
        keyPrefixList.add("DownKey:");
        keyPrefixList.add("RightKey:");
        keyPrefixList.add("AuraKey:");
        keyPrefixList.add("SpeedKey:");
        keyPrefixList.addAll(keyPrefixList);

        menuView = new MenuView(NUMBER_OF_PLAYERS,keyPrefixList, keyList);

        Gdx.input.setInputProcessor(menuView.getStage());

        addListeners();

    }

    public void addListeners(){
        menuView.getPlayButton().addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                /*
                 * Starts the game by creating a new ballBuster object,
                 * and assigning keys to the players.
                 */
                if (isInFocus) {
                    ballBuster = new BallBuster(menuView.getMapFilePath(),isAIActive);
                    ballBuster.create();
                    playerList = ballBuster.getPlayers();
                    int bindNbr = 0;

                    for(Player p: playerList) {
                        p.setKeys(keyList.get(bindNbr), keyList.get(bindNbr + 1), keyList.get(bindNbr + 2),
                                keyList.get(bindNbr + 3), keyList.get(bindNbr + 4), keyList.get(bindNbr + 5));
                        bindNbr = bindNbr + keyList.size() / NUMBER_OF_PLAYERS;
                    }
                    isInFocus = false;
                }
            }
        });

        menuView.getCycleLeftButton().addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if(isInFocus) {
                    menuView.cycleLeft();
                }
                return true;
            }
        });

        menuView.getCycleRightButton().addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if(isInFocus) {
                    menuView.cycleRight();
                }
                return true;
            }
        });

        menuView.getExitButton().addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (isInFocus) {
                    Gdx.app.exit();
                }
                return true;
            }
        });

        menuView.getCheckAIButton().addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                if(isInFocus){
                    isAIActive = !isAIActive;
                    menuView.toggleCheckBoxDrawable(isAIActive);
                }
                return true;
            }
        });


        for(int i = 0; i < menuView.getBindButtons().size(); i++) {
            BBMenuButton bindButton = menuView.getBindButtons().get(i);
            bindButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    if (isInFocus) {
                        currentBindIndex = bindButton.getIndex();
                        Gdx.input.setInputProcessor(thisController);
                    }
                }
            });
        }

    }

    /*
     * Renders the menu if ballbuster is inactive and the menu is in focus,
     * renders the game if ballbuster is active, and the menu is not in focus,
     * disposes the current ballbuster session if the menu regains focus
     */
    @Override
    public void render() {
        if (ballBuster == null) {
            menuView.update();
        }else if (!ballBuster.getIsGameOver()) {
            ballBuster.render();
        }else{
            isInFocus = true;
            ballBuster = null;
        }
    }

    @Override
    public void pause () {}
    @Override
    public void resume () {}
    @Override
    public void dispose () {}


    /*
     * Sets the keybind of the current index to the key of the pressed button
     */
    @Override
    public boolean keyDown(int keycode) {
        if(isInFocus && currentBindIndex!=NO_INDEX){
            keyList.remove(currentBindIndex);
            keyList.add(currentBindIndex, keycode);
            menuView.setBindLabel(currentBindIndex,keyPrefixList.get(currentBindIndex) ,keycode);
            Gdx.input.setInputProcessor(menuView.getStage());
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
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
    public void resize(int width, int height) {
        
    }
}
