package ballbuster.controller;

import ballbuster.model.Player;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;

/**
 * Created by Johan Segerlund on 2015-05-11.
 */


public class AIController extends BallController {
    private ArrayList<Player> enemyPlayers = new ArrayList<>();

    public AIController(Player player, SpriteBatch batch, Texture texture, World world, Texture shieldTexture, ArrayList<Player> players) {
        super(player, batch, texture, world, shieldTexture);
        for (Player p : players) {
            if (p == player) {
                continue;
            }
            enemyPlayers.add(p);
        }
    }

    @Override
    public boolean keyDown(int keycode) {
        Player closestEnemy = getClosetEnemy();

        if (closestEnemy.getBall().getX() < ball.getX()) {                  //MOVE LEFT
            ballView.moveLeft(body.getPosition().x, body.getPosition().y);
        } else {                                                            //MOVE RIGHT
            ballView.moveRight(body.getPosition().x, body.getPosition().y);
        }

        if (closestEnemy.getBall().getY() < ball.getY()) {                  //MOVE DOWN
            ballView.moveDown(body.getPosition().x, body.getPosition().y);
        } else {                                                            //MOVE UP
            ballView.moveUp(body.getPosition().x, body.getPosition().y);
        }

        return false;
    }

    private Player getClosetEnemy() {
        float distanceBetweenPlayer = -1;
        Player closestPlayer = null;
        for (Player p : enemyPlayers) {
            float x = p.getBall().getX();
            float y = p.getBall().getY();
            if (distanceBetweenPlayer < 0) {
                distanceBetweenPlayer = ball.getX() - x + ball.getY() - y;
                closestPlayer = p;
            } else {
                if (distanceBetweenPlayer < (ball.getX() - x + ball.getY() - y)) {
                    distanceBetweenPlayer = ball.getX() - x + ball.getY() - y;
                    closestPlayer = p;
                }
            }
        }
        return closestPlayer;
    }

    @Override
    public void onRender() {
        ballView.setPosition(ball);
        keyDown(0);
        ballView.renderBall(batch);
    }
}
