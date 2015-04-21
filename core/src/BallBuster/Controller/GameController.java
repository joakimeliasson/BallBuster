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
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import java.util.ArrayList;

/**
 * Created by Joakim on 2015-03-30.
 */
public class GameController {

    private MoveController moveController;
    private MoveController moveController2;

    private AuraController auraController;
    private AuraController auraController2;

    private SpriteBatch batch;

    private ArrayList<Sprite> spriteList;

    private OrthographicCamera camera;
    private World world;
    private TiledMap map;

    //SCALE due to speed issues
    private final float SCALE = 100f;
    private float tileSize;

    private Box2DDebugRenderer debugRenderer;
    private OrthogonalTiledMapRenderer mapRenderer;
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

    private ArrayList<Tile> wallList;
    private ArrayList<Ball> ballList;

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

        ballList = new ArrayList<Ball>();
        ballList.add(ball);
        ballList.add(ball2);

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

        wallList = new ArrayList<Tile>();
        wallList.add(groundWall);
        wallList.add(upperWall);
        wallList.add(leftWall);
        wallList.add(rightWall);

        spriteList.add(groundWall.getSprite());
        spriteList.add(upperWall.getSprite());
        spriteList.add(leftWall.getSprite());
        spriteList.add(rightWall.getSprite());

        aura = new Aura(ball);
        aura2 = new Aura(ball2);

        aura.createAnimation();
        aura2.createAnimation();

        ArrayList<BlockTile> blockTiles = new ArrayList<BlockTile>();
        blockTiles.add(rightBox);
        blockTiles.add(leftBox);

        auraController = new AuraController(aura, blockTiles, ball);

        ArrayList<BlockTile> blockTiles2 = new ArrayList<BlockTile>();
        blockTiles2.add(groundBox);

        auraController2 = new AuraController(aura2, blockTiles2, ball2);

        //spriteList.add(aura.getAuraSprite());


        //Load TileMap
        //https://www.youtube.com/watch?v=IwM-LSwZCfw
        map = new TmxMapLoader().load("core/res/TiledMaps/dummyMap.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map);

        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get("BlackBlock");

        tileSize = layer.getTileWidth();

        for(int row = 0; row < layer.getHeight(); row++) {
            for(int col = 0; col < layer.getWidth(); col++) {
                TiledMapTileLayer.Cell cell = layer.getCell(col, row);

                if(cell == null) {
                    continue;
                }
                if(cell.getTile() == null) {
                    continue;
                }

                BodyDef bodyDef = new BodyDef();
                bodyDef.type = BodyDef.BodyType.StaticBody;

                bodyDef.position.set(((col*tileSize) + tileSize/2)/SCALE, (tileSize/2 + row*( tileSize ))/SCALE);

                Body body = world.createBody(bodyDef);

                //Create the body as a box
                PolygonShape shape = new PolygonShape();

                shape.setAsBox(tileSize/2/SCALE, tileSize/2 /SCALE);

                //Set physical attributes to the body
                FixtureDef fixtureDef = new FixtureDef();
                fixtureDef.shape = shape;
                fixtureDef.density = 7f;
                fixtureDef.friction = 1f;
                body.createFixture(fixtureDef);
            }
        }

        /** set camera to map focus map here
        int mapHeight,mapWidth;
        mapHeight = map.getProperties().get("height", Integer.class) * map.getProperties().get("tileheight", Integer.class);
        mapWidth =  map.getProperties().get("width", Integer.class) * map.getProperties().get("tilewidth", Integer.class);

        //camera position
        camera.position.set(mapWidth/2, mapHeight/2, 0);

        //camera scale
        camera.viewportHeight = mapHeight;
        camera.viewportWidth = mapWidth;
        camera.update();*/

    }
    public void render() {
        camera.update();

        world.step(1f / 60f, 6, 2);

        batch.setProjectionMatrix(camera.combined);
        debugMatrix = batch.getProjectionMatrix().cpy().scale(SCALE, SCALE, 0);

        move();

        world.setContactListener(new CollisionController(wallList, ballList));

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Draw TileMap
        mapRenderer.setView(camera);
        mapRenderer.render();

        batch.begin();
        auraController.renderAura(batch);
        auraController2.renderAura(batch);
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
