package ballBuster.controller;

import ballBuster.view.BallView;
import ballBuster.view.TileView;
import com.badlogic.gdx.physics.box2d.*;

import java.util.ArrayList;

/**
 * Created by Joakim on 2015-04-20.
 */
public class CollisionController implements ContactListener{

    private ArrayList<TileView> tileList;
    private ArrayList<BallView> ballList;

    public CollisionController(ArrayList<TileView> tileList, ArrayList<BallView> ballList){
        this.tileList = tileList;
        this.ballList = ballList;

    }

    @Override
    public void beginContact(Contact contact) {
        Body a = contact.getFixtureA().getBody();
        Body b = contact.getFixtureB().getBody();

        for (int k = 0; k < ballList.size(); k++) {
            for (int i = 0; i < tileList.size(); i++) {
                 if (a == tileList.get(i).getBody() && b == ballList.get(k).getBody()) {
                    ballList.get(k).getBall().shieldDamage(damage(ballList.get(k)));
                    System.out.println("Shield: "+ballList.get(k).getBall().getShield());
                }
            }
        }

    }

    public double damage(BallView ballView){
        double x = Math.pow(2, Math.abs(ballView.getBody().getLinearVelocity().x));
        double y = Math.pow(2, Math.abs(ballView.getBody().getLinearVelocity().y));
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
