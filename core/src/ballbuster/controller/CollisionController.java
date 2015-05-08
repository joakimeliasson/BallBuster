package ballbuster.controller;

import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.AudioDevice;
import com.badlogic.gdx.audio.AudioRecorder;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.physics.box2d.*;
import java.util.ArrayList;

/**
 * Created by Joakim on 2015-04-20.
 */
public class CollisionController implements ContactListener{

    private ArrayList<Body> tileList;
    private ArrayList<BallController> ballList;
    private Sound sound;

    public CollisionController(ArrayList<Body> tileList, ArrayList<BallController> ballList){
        this.tileList = tileList;
        this.ballList = ballList;
        FileHandle collisionFileHandle = Gdx.files.internal("core/sounds/collision.mp3");
        sound = Gdx.audio.newSound(collisionFileHandle);
    }

    @Override
    public void beginContact(Contact contact) {
        Body a = contact.getFixtureA().getBody();
        Body b = contact.getFixtureB().getBody();
        for (int k = 0; k < ballList.size(); k++) {
            for (int i = 0; i < tileList.size(); i++) {
                 if (a == tileList.get(i) && b == ballList.get(k).getBody() && !ballList.get(k).getPlayer().hasSpeedUp()) {
                         ballList.get(k).getBall().shieldDamage(damage(ballList.get(k)));
                         sound.play();
                         System.out.println("Shield: " + ballList.get(k).getBall().getShield());
                 }
                else
                     sound.play();
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
