package ballbuster.controller;

import ballbuster.model.BBMenuButton;
import ballbuster.model.Player;
import ballbuster.view.MenuView;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import java.util.List;

/**
 * Created by Matthias on 2015-05-19.
 */
public class MenuController implements ApplicationListener, InputProcessor {


    private List<Player> playerList;
    private BallBuster ballBuster;
    private final MenuController thisController = this;
    private MenuView menuView;
    private int currentBind = -1;
    private boolean isInFocus = true;

    @Override
    public void create() {

        menuView = new MenuView();
        Gdx.input.setInputProcessor(menuView.getStage());

        menuView.getPlayButton().addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                if (isInFocus) {
                    ballBuster = new BallBuster(menuView.getMap());
                    ballBuster.create();
                    playerList = ballBuster.getPlayers();
                    List<Integer> keyList = menuView.getKeys();
                    int bindNbr = 0;
                    //Should not loop more than once, as there are only 2 players
                    for (int i = bindNbr; i < playerList.size(); i++) {
                        playerList.get(i).setKeys(keyList.get(bindNbr), keyList.get(bindNbr + 1), keyList.get(bindNbr + 2), keyList.get(bindNbr + 3), keyList.get(bindNbr + 4), keyList.get(bindNbr + 5));
                        bindNbr = bindNbr + keyList.size() / 2;
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


        for(int i = 0; i < menuView.getBindButtons().size(); i++) {
            BBMenuButton bindButton = menuView.getBindButtons().get(i);
            bindButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    if (isInFocus) {
                        currentBind = bindButton.getIndex();
                        Gdx.input.setInputProcessor(thisController);
                    }
                }
            });
        }
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void render() {
        if (ballBuster == null) {
            menuView.update();
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
    public boolean keyDown(int keycode) {
        if(isInFocus && currentBind!=-1){
            System.out.println("");
            menuView.setBindButton(currentBind, keycode);
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


}


