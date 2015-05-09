package ballbuster.controller;

import ballbuster.model.Player;
import ballbuster.model.Timer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.*;
import java.util.ArrayList;

/**
 * Created by Joakim on 2015-04-20.
 */
public class CollisionController implements ContactListener{

    private ArrayList<Body> tileList;
    private ArrayList<BallController> ballList;
    private Sound sound;
    private BitmapFont font;
  //  private SpriteBatch batch;
    //private PrintFont printFont;
    private Player player;
    private String message;
    private Timer timer;

    public CollisionController(ArrayList<Body> tileList, ArrayList<BallController> ballList){
        this.tileList = tileList;
        this.ballList = ballList;
//        this.batch = batch;
        //timer = new Timer(5f);

        FileHandle collisionFileHandle = Gdx.files.internal("core/sounds/collision.mp3");
        sound = Gdx.audio.newSound(collisionFileHandle);

        //font = new BitmapFont(Gdx.files.internal("core/images/test.fnt"));
        //font.setColor(1,1,1,1);
      //  printFont = new PrintFont(batch, font, player);
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
                    // System.out.println("Shield: " + ballList.get(k).getBall().getShield());

          //           player = ballList.get(k).getPlayer();
                     //printFont.setPosition(player.getBall().getX(), player.getBall().getY());
                  //   int damage = (int)damage(ballList.get(k));
                //     String message = "-" + damage +" Damage!";
            //         System.out.println("Damage " +message);
              //       this.message = message;
                     //printFont.resetTimer();
                 }
                else {
                     sound.play();
                 }
            }
        }
    }

    public double damage(BallController ballController){
        double x = Math.pow(2, Math.abs(ballController.getBody().getLinearVelocity().x));
        double y = Math.pow(2, Math.abs(ballController.getBody().getLinearVelocity().y));
        double dmg = Math.sqrt(x+y);
        System.out.println("Damage: "+dmg+"\n");
        return dmg/2;
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
/*
    @Override
    public void onCreate() {

    }

    @Override
    public void onRender() {
        if(message != "" && message!=null)
            timer.update(Gdx.graphics.getDeltaTime());
        if(timer.hasTimeElapsed())
            message ="";

        if(message!=null && player!=null) {
            batch.begin();
            font.draw(batch, message, player.getBall().getX(), player.getBall().getY());
            batch.end();
        }
    }*/
}
