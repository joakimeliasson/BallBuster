package BallBuster.Controller;

import BallBuster.Model.Ball;
import BallBuster.Model.Player;
import BallBuster.Model.Tile.BlockTile;
import BallBuster.Model.Tile.Tile;
import BallBuster.View.BallView;
import BallBuster.View.BlockTileView;
import BallBuster.View.TileView;
import com.badlogic.gdx.physics.box2d.*;

import java.util.ArrayList;

/**
 * Created by Joakim on 2015-04-20.
 */
public class CollisionController implements ContactListener{

    private ArrayList<Body> tileList;
    private ArrayList<BallController> ballList;

    public CollisionController(ArrayList<Body> tileList, ArrayList<BallController> ballList){
        this.tileList = tileList;
        this.ballList = ballList;

    }

    @Override
    public void beginContact(Contact contact) {
        Body a = contact.getFixtureA().getBody();
        Body b = contact.getFixtureB().getBody();
        for (int k = 0; k < ballList.size(); k++) {
            for (int i = 0; i < tileList.size(); i++) {
                 if (a == tileList.get(i) && b == ballList.get(k).getBody()) {
                     if (!ballList.get(k).getPlayer().hasSpeedUp()) {
                         ballList.get(k).getBall().shieldDamage(damage(ballList.get(k)));
                         System.out.println("Shield: " + ballList.get(k).getBall().getShield());
                     }
                 }
            }
        }

    }

    public double damage(BallController ballController){
        double x = Math.pow(2, Math.abs(ballController.getBody().getLinearVelocity().x));
        double y = Math.pow(2, Math.abs(ballController.getBody().getLinearVelocity().y));
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
