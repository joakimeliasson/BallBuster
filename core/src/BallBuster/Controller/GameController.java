package BallBuster.Controller;

import BallBuster.Model.Aura;
import BallBuster.Model.Ball;
import BallBuster.Model.Tile.BlockTile;
import BallBuster.Model.Tile.Tile;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;

/**
 * Created by Joakim on 2015-03-30.
 */
public class GameController {

    private MoveController moveController;
    private MoveController moveController2;

    private SpriteBatch batch;

    private ArrayList<Sprite> spriteList;

    private OrthographicCamera camera;
    private World world;

    //SCALE due to speed issues
    private final float SCALE = 100f;

    private Box2DDebugRenderer debugRenderer;
    private Matrix4 debugMatrix;

    private Ball ball;
    private Ball ball2;

    private Tile groundWall;
    private Tile upperWall;
    private Tile leftWall;
    private Tile rightWall;
    private BlockTile leftBox;
    private BlockTile rightBox;
    private BlockTile groundBox;

    private Aura aura;
    private Aura aura2;

    public GameController() {
    }
    public void create() {
        world = new World(new Vector2(0, 0), true);

        debugRenderer = new Box2DDebugRenderer();

        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        Gdx.gl.glClearColor(106f/255f, 165f/255f, 255f/255f, 1f);

        batch = new SpriteBatch();

        spriteList = new ArrayList<Sprite>();

        FileHandle ballFileHandle = Gdx.files.internal("core/images/leftBall.png");
        Texture ballTexture = new Texture(ballFileHandle);

        FileHandle ball2FileHandle = Gdx.files.internal("core/images/rightBall.png");
        Texture ball2Texture = new Texture(ball2FileHandle);

        FileHandle boxFileHandle = Gdx.files.internal("core/images/normal.png");
        Texture boxTexture = new Texture(boxFileHandle);

        FileHandle horizontalFileHandle = Gdx.files.internal("core/images/wallHorizontal.png");
        Texture horizontalTexture = new Texture(horizontalFileHandle);

        FileHandle verticalFileHandle = Gdx.files.internal("core/images/wallVertical.png");
        Texture verticalTexture = new Texture(verticalFileHandle);

        FileHandle rightFileHandle = Gdx.files.internal("core/images/rightBox.png");
        Texture rightTexture = new Texture(rightFileHandle);

        FileHandle leftFileHandle = Gdx.files.internal("core/images/leftBox.png");
        Texture leftTexture = new Texture(leftFileHandle);

        FileHandle groundFileHandle = Gdx.files.internal("core/images/groundBox.png");
        Texture groundTexture = new Texture(groundFileHandle);

        FileHandle auraFileHandle = Gdx.files.internal("core/images/shield.png");
        Texture auraTexture = new Texture(auraFileHandle);


        ball = new Ball(-camera.viewportWidth/2+verticalTexture.getWidth(), -camera.viewportHeight/2+horizontalTexture.getHeight(), null,null, world, ballTexture);
        ball2 = new Ball(600f, camera.viewportHeight/2-horizontalTexture.getHeight()*2, null,null,world, ball2Texture);

        spriteList.add(ball.getBallSprite());
        spriteList.add(ball2.getBallSprite());

        moveController = new MoveController(ball);
        moveController2 = new MoveController(ball2);

        rightBox = new BlockTile(300f, 20f, world, rightTexture);
        groundBox = new BlockTile(-100f, -300f, world, groundTexture);
        leftBox = new BlockTile(-400, 20f, world, leftTexture);

        spriteList.add(rightBox.getSprite());
        spriteList.add(groundBox.getSprite());
        spriteList.add(leftBox.getSprite());

        groundWall = new Tile(-camera.viewportWidth/2, -camera.viewportHeight/2, world, horizontalTexture);
        upperWall = new Tile(-camera.viewportWidth/2, camera.viewportHeight/2-horizontalTexture.getHeight(), world, horizontalTexture);
        leftWall = new Tile(-camera.viewportWidth/2, -camera.viewportHeight/2, world, verticalTexture);
        rightWall = new Tile(camera.viewportWidth/2-verticalTexture.getWidth(), -camera.viewportHeight/2, world, verticalTexture);

        spriteList.add(groundWall.getSprite());
        spriteList.add(upperWall.getSprite());
        spriteList.add(leftWall.getSprite());
        spriteList.add(rightWall.getSprite());

        aura = new Aura(ball);
        aura2 = new Aura(ball2);

        spriteList.add(aura.getAuraSprite());
    }
    public void render() {
        camera.update();

        world.step(1f / 60f, 6, 2);

        batch.setProjectionMatrix(camera.combined);
        debugMatrix = batch.getProjectionMatrix().cpy().scale(SCALE, SCALE, 0);

        move();
        if (aura.getAuraStatus()) {
            if (!spriteList.contains(aura.getAuraSprite()))
                spriteList.add(aura.getAuraSprite());
            aura.setAuraPosition();
            leftBox.activateMagnet(ball.getBody());
            rightBox.activateMagnet(ball.getBody());
        } else {
            if (spriteList.contains(aura.getAuraSprite())){
                spriteList.remove(aura.getAuraSprite());
            }
        }
        if (aura2.getAuraStatus()) {
            if (!spriteList.contains(aura2.getAuraSprite()))
                spriteList.add(aura2.getAuraSprite());
            aura2.setAuraPosition();
            groundBox.activateMagnet(ball2.getBody());
        } else {
            if (spriteList.contains(aura2.getAuraSprite())){
                spriteList.remove(aura2.getAuraSprite());
            }
        }

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        for(Sprite sprite : spriteList) {
            batch.draw(sprite, sprite.getX(), sprite.getY(), sprite.getOriginX(), sprite.getOriginY(),
                    sprite.getWidth(), sprite.getHeight(), sprite.getScaleX(), sprite.getScaleY(), sprite.getRotation());
        }
        batch.end();

        debugRenderer.render(world,debugMatrix);
    }
    private void move() {


        ball.setPosition();
        ball2.setPosition();

        if(Gdx.input.isKeyPressed(Input.Keys.DPAD_LEFT))
            moveController2.moveLeft();
        if(Gdx.input.isKeyPressed(Input.Keys.DPAD_RIGHT))
            moveController2.moveRight();
        if(Gdx.input.isKeyPressed(Input.Keys.DPAD_UP))
            moveController2.moveUp();
        if(Gdx.input.isKeyPressed(Input.Keys.DPAD_DOWN))
            ball2.moveDown();
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            if (!aura2.getAuraStatus()) {
                aura2.setAuraStatus(true);
            } else {
                aura2.setAuraStatus(false);
            }
        }else
            groundBox.resetRestitution(ball2.getBody());

        if(Gdx.input.isKeyPressed(Input.Keys.A))
            moveController.moveLeft();
        if(Gdx.input.isKeyPressed(Input.Keys.D))
            moveController.moveRight();
        if(Gdx.input.isKeyPressed(Input.Keys.W))
            moveController.moveUp();
        if(Gdx.input.isKeyPressed(Input.Keys.S))
            moveController.moveDown();
        if (Gdx.input.isKeyJustPressed(Input.Keys.ALT_LEFT)) {
            if (!aura.getAuraStatus()) {
                aura.setAuraStatus(true);
            } else {
                aura.setAuraStatus(false);
            }
        } else {
            rightBox.resetRestitution(ball.getBody());
            leftBox.resetRestitution(ball.getBody());
        }
    }
}
