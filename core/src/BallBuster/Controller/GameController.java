package ballBuster.controller;

/**
 * Created by Joakim on 2015-03-30.
 */
public class GameController {
/*
    private MoveController moveController;

    private AuraController auraController;

    private SpriteBatch batch;

    private ArrayList<Sprite> spriteList;
    private ArrayList<BlockTile> blockTiles;
    private ArrayList<BlockTile> blockTiles2;

    private OrthographicCamera camera;
    private World world;
    private TiledMap map;
    private Map mapModel;

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

        moveController = new MoveController();

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

        blockTiles = new ArrayList<BlockTile>();
        blockTiles.add(rightBox);
        blockTiles.add(leftBox);

        auraController = new AuraController();

        blockTiles2 = new ArrayList<BlockTile>();
        blockTiles2.add(groundBox);

        //Load TileMap
        this.mapModel = new Map("core/res/TiledMaps/dummyMap.tmx", world);
        //map = new TmxMapLoader().load("core/res/TiledMaps/dummyMap.tmx");

        mapRenderer = new OrthogonalTiledMapRenderer(mapModel.getTileMap());
        //mapRenderer = new OrthogonalTiledMapRenderer(map);
        mapRenderer.setView(camera);

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
        //mapRenderer.setView(camera); //Not sure if this needs to be here
        mapRenderer.render(); //TODO Move this to view

        batch.begin();
        auraController.renderAura(aura,blockTiles,batch);
        auraController.renderAura(aura2,blockTiles2,batch);
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
            moveController.moveLeft(ball2);
        if(Gdx.input.isKeyPressed(Input.Keys.DPAD_RIGHT))
            moveController.moveRight(ball2);
        if(Gdx.input.isKeyPressed(Input.Keys.DPAD_UP))
            moveController.moveUp(ball2);
        if(Gdx.input.isKeyPressed(Input.Keys.DPAD_DOWN))
            moveController.moveDown(ball2);
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            if (!aura2.getAuraStatus()) {
                aura2.setAuraStatus(true);
            } else {
                aura2.setAuraStatus(false);
            }
        }else
            groundBox.resetRestitution(ball2.getBody());

        if(Gdx.input.isKeyPressed(Input.Keys.A))
            moveController.moveLeft(ball);
        if(Gdx.input.isKeyPressed(Input.Keys.D))
            moveController.moveRight(ball);
        if(Gdx.input.isKeyPressed(Input.Keys.W))
            moveController.moveUp(ball);
        if(Gdx.input.isKeyPressed(Input.Keys.S))
            moveController.moveDown(ball);
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
    }*/
}
