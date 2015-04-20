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
                if (a == ballList.get(k).getBody() && b == tileList.get(i).body) {
                    ballList.get(k).shieldDamage(10);
                    System.out.println(ballList.get(k).getShield());
                } else if (a == tileList.get(i).body && b == ballList.get(k).getBody()) {
                    ballList.get(k).shieldDamage(10);
                    System.out.println(ballList.get(k).getShield());
                }
            }
        }

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
