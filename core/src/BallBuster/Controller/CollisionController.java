package BallBuster.Controller;

import BallBuster.Model.Ball;
import BallBuster.Model.Tile.BlockTile;
import BallBuster.Model.Tile.Tile;
import com.badlogic.gdx.physics.box2d.*;

import java.util.ArrayList;

/**
 * Created by Joakim on 2015-04-20.
 */
public class CollisionController implements ContactListener{

    private ArrayList<Tile> tileList;
    private ArrayList<Ball> ballList;

    public CollisionController(ArrayList<Tile> tileList, ArrayList<Ball> ballList){
        this.tileList = tileList;
        this.ballList = ballList;

    }

    @Override
    public void beginContact(Contact contact) {
        Body a = contact.getFixtureA().getBody();
        Body b = contact.getFixtureB().getBody();

        for (int k = 0; k < ballList.size(); k++) {
            for (int i = 0; i < tileList.size(); i++) {
                 if (a == tileList.get(i).body && b == ballList.get(k).getBody()) {
                    ballList.get(k).shieldDamage(damage(ballList.get(k)));
                    System.out.println("Shield: "+ballList.get(k).getShield());
                }
            }
        }

    }

    public double damage(Ball ball){
        double x = Math.pow(2, Math.abs(ball.getBody().getLinearVelocity().x));
        double y = Math.pow(2, Math.abs(ball.getBody().getLinearVelocity().y));
        double dmg = Math.sqrt(x+y);
        System.out.println("Damage: "+dmg+"\n");
        return dmg;
    }


    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
