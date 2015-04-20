package BallBuster.Controller;

import BallBuster.Model.Ball;

/**
 * Created by jacobth on 2015-04-20.
 */
public class MoveController {

    private Ball ball;

    public MoveController(Ball ball) {
        this.ball = ball;
    }
    public void moveLeft() {
        ball.moveLeft();
    }
    public void moveRight() {
        ball.moveRight();
    }
    public void moveUp() {
        ball.moveUp();
    }
    public void moveDown() {
        ball.moveDown();
    }
}
