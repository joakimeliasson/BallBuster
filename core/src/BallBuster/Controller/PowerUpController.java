package BallBuster.Controller;

import BallBuster.Model.Ball;
import BallBuster.Model.PowerUp;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by jacobth on 2015-04-25.
 */
public class PowerUpController{
    private Timer timer;
    private Timer powerUpTimer;
    private Random random;

    public PowerUpController() {
        int random = (int )(Math.random() * 20 + 10);
        timer = new Timer(random);
        powerUpTimer = new Timer(5f);
        System.out.println(random);
    }
    public void powerUpSet(PowerUp powerUp, ArrayList<Ball> ballList, Sprite sprite, float delta, SpriteBatch batch) {
        timer.update(delta);
        powerUpTimer.update(delta);
        showSprite(sprite);
        Ball ball = getHitBall(ballList, sprite);
        if(timer.hasTimeElapsed()) {
            draw(sprite, batch);
            if (ball != null) {
                int random = (int )(Math.random() * 20 + 10);
                timer.reset(random);
                System.out.println(random);
                hideSprite(sprite);
                switch (powerUp.getPowerUp().toString()) {
                    case "speedUp":
                        ball.setSpeed(0.4f);
                        break;
                    case "slowDown":
                        ball.setSpeed(0.02f);
                        break;
                }
            }
        }
        resetBall(ballList, delta);
    }
    public void resetBall(ArrayList<Ball> ballList, float delta) {
        powerUpTimer.update(delta);
        for(Ball ball : ballList){
            if(ball.hasPowerUp()) {
                if(powerUpTimer.hasTimeElapsed()) {
                    ball.setSpeed(0.1f);
                    ball.setHasPowerUp(false);
                }
            }

        }

    }

    private Ball getHitBall(ArrayList<Ball> ballList, Sprite sprite) {
        for(Ball ball : ballList) {
            if (sprite.getBoundingRectangle().contains(ball.getX(), ball.getY())) {
                ball.setHasPowerUp(true);
                powerUpTimer.reset();
                return ball;
            }
        }
        return null;
    }
    private void hideSprite(Sprite sprite) {
        sprite.setSize(0,0);
    }
    private void showSprite(Sprite sprite) {
        sprite.setSize(sprite.getTexture().getWidth(), sprite.getTexture().getHeight());
    }

    private void draw(Sprite sprite, SpriteBatch batch) {
        batch.begin();
        batch.draw(sprite, sprite.getX(), sprite.getY(), sprite.getOriginX(), sprite.getOriginY(),
                sprite.getWidth(), sprite.getHeight(), sprite.getScaleX(), sprite.getScaleY(), sprite.getRotation());
        batch.end();
    }

    private class Timer {
        private float remaining;
        private float interval;

        public Timer(float interval) {
            this.interval = interval;
            this.remaining = interval;
        }
        public boolean hasTimeElapsed() {
            return remaining<0.0f;
        }
        public void reset() {
            remaining = interval;
        }
        public void reset(float interval) {
            this.interval = interval;
            this.remaining = interval;
        }
        public void update(float delta) {
            remaining = remaining - delta;
        }
    }
}
