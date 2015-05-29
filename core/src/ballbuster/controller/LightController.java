package ballbuster.controller;

import ballbuster.model.Player;
import box2dLight.RayHandler;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.World;
import java.util.ArrayList;
import com.badlogic.gdx.graphics.Color;

/**
 * Created by Johan Segerlund on 2015-05-22.
 */
public class LightController implements IController {

    private float dayTime = 0;
    private RayHandler lightHandler;
    private World world;
    private OrthographicCamera camera;
    private ArrayList<Player> playerList;

    private box2dLight.PointLight lightBall1;
    private box2dLight.PointLight lightBall2;
    private Player player1;
    private Player player2;

    public LightController(World world, OrthographicCamera camera, ArrayList<Player> playerList) {
        this.world = world;
        this.camera = camera;
        this.playerList = playerList;
    }

    @Override
    public void onCreate() {
        RayHandler.setGammaCorrection(false);
        lightHandler = new RayHandler(world);

        lightHandler.setBlurNum(3);

        player1 = playerList.get(0);
        player2 = playerList.get(1);

        lightBall1 = new box2dLight.PointLight(lightHandler, 100, Color.WHITE, 100, 0, 0); //(handler, number of rays, color, "radiuseffect",xPosition, yPosition)
        lightBall2 = new box2dLight.PointLight(lightHandler, 100, Color.BLUE, 100, 0, 0);
    }

    @Override
    public void onRender() {
        if(dayTime <0.7f)
        dayTime = dayTime + 0.003f; //Speed of day will arise
        lightHandler.setAmbientLight(dayTime);

        lightBall1.setPosition(player1.getBall().getX() + player1.getBall().getRadius(), player1.getBall().getY() + player1.getBall().getRadius());
        lightBall2.setPosition(player2.getBall().getX() + player2.getBall().getRadius(), player2.getBall().getY() + player2.getBall().getRadius());

        lightHandler.setCombinedMatrix(camera.combined);
        lightHandler.updateAndRender();

    }
}
