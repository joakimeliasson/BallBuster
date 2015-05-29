package ballbuster.controller;

import ballbuster.model.Player;
import ballbuster.model.Timer;
import ballbuster.view.CollisionView;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.*;
import java.util.ArrayList;

/**
 * Created by Joakim on 2015-04-20.
 */
public class CollisionController implements ContactListener, IController {

    private ArrayList<Body> tileList;
    private ArrayList<BallController> ballList;
    private Sound sound;
    private Player player;
    private String message;
    private SpriteBatch batch;
    private CollisionView collisionView;
    private Timer timer;

    public CollisionController(ArrayList<Body> tileList, ArrayList<BallController> ballList, SpriteBatch batch) {
        this.tileList = tileList;
        this.ballList = ballList;
        this.batch = batch;
        this.batch = batch;
        timer = new Timer(4f);
        collisionView = new CollisionView();

        FileHandle collisionFileHandle = Gdx.files.internal("sounds/collision.mp3");
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
                    player = ballList.get(k).getPlayer();
                    int damage = (int) damage(ballList.get(k));
                    String message = "-" + damage + " Damage!";
                    this.message = message;
                } else {
                    sound.play();
                }
            }
        }
    }

    public double damage(BallController ballController) {
        double x = Math.pow(2, Math.abs(ballController.getBody().getLinearVelocity().x));
        double y = Math.pow(2, Math.abs(ballController.getBody().getLinearVelocity().y));
        double dmg = Math.sqrt(x + y);
        System.out.println("Damage: " + dmg + "\n");
        return dmg / 2;
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

    @Override
    public void onCreate() {
    }

    @Override
    public void onRender() {
        if (message != "" && message != null)
            timer.update(Gdx.graphics.getDeltaTime());

        if (timer.hasTimeElapsed()) {
            message = "";
            timer.reset();
        }
        collisionView.renderFont(player, message, batch, timer);
    }
}
