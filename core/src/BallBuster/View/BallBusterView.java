package BallBuster.View;

import BallBuster.Controller.GameController;
import com.badlogic.gdx.*;


public class BallBusterView extends Game {

    private GameController gameController;

    @Override
    public void create() {
        gameController = new GameController();
        gameController.create();
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void resume() {
        super.resume();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }


    @Override
    public void render() {
        super.render();
        gameController.render();
    }
}
