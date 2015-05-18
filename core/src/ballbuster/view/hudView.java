package ballbuster.view;

import ballbuster.model.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Johan Segerlund on 2015-05-15.
 */
public class HudView {

    private SpriteBatch batch;
    private Player player1;
    private Player player2;
    private Sprite leftBar;
    private Sprite rightBar;
    private Sprite hpBarLeftPlayer;
    private Sprite manaBarLeftPlayer;
    private Sprite manaBarRightPlayer;
    private Sprite hpBarRightPlayer;

    private final double player1MaxHp, player2MaxHp;
    private final float player1MaxMp, player2MaxMp;


    public HudView(Player player1, Player player2, SpriteBatch batch, OrthographicCamera camera) {
        this.player1 = player1;
        this.player2 = player2;
        this.player1MaxHp = player1.getBall().getShield();
        this.player2MaxHp = player2.getBall().getShield();
        this.player1MaxMp = player1.getBall().getMana();
        this.player2MaxMp = player2.getBall().getMana();
        this.batch = batch;

        leftBar = new Sprite(new Texture(Gdx.files.internal("core/images/healthBar/leftBar.png")));
        rightBar = new Sprite(new Texture(Gdx.files.internal("core/images/healthBar/leftBar.png")));

        hpBarLeftPlayer = new Sprite(new Texture(Gdx.files.internal("core/images/healthBar/hpbar.png")));
        manaBarLeftPlayer = new Sprite(new Texture(Gdx.files.internal("core/images/healthBar/manabar.png")));

        hpBarRightPlayer = new Sprite(new Texture(Gdx.files.internal("core/images/healthBar/hpbar.png")));
        manaBarRightPlayer = new Sprite(new Texture(Gdx.files.internal("core/images/healthBar/manabar.png")));

        int distanceFromScreenEdge = 32;
        float leftBarPositionX  = -camera.viewportWidth/2 + distanceFromScreenEdge;
        float leftBarPositionY  =  camera.viewportHeight/2 - leftBar.getHeight() - distanceFromScreenEdge;
        float rightBarPositionX = camera.viewportWidth/2 - distanceFromScreenEdge - rightBar.getWidth();
        float rightBarPositionY = camera.viewportHeight/2 - leftBar.getHeight() - distanceFromScreenEdge;

        leftBar.setPosition(leftBarPositionX,leftBarPositionY);
        rightBar.setPosition(rightBarPositionX, rightBarPositionY);

        hpBarLeftPlayer.setPosition((leftBarPositionX) + 34,leftBarPositionY + 42);
        manaBarLeftPlayer.setPosition((leftBarPositionX) + 34,leftBarPositionY +11);

        hpBarRightPlayer.setPosition((rightBarPositionX) + 34, rightBarPositionY + 42);
        manaBarRightPlayer.setPosition((rightBarPositionX) + 34, rightBarPositionY + 11);
    }

    public void render() {

        //calculate the percentage of mana and shield of each players so it can be displayed.
        float player1HP = (float) (player1.getBall().getShield() / player1MaxHp);
        if (player1HP < 0 ) {
            player1HP = 0;
        }
        float player1MP = ((float)player1.getBall().getMana() / (float)player1MaxMp);
        if(player1MP < 0) {
            player1MP = 0;
        }

        float player2HP = (float) (player2.getBall().getShield() / player2MaxHp);
        if (player2HP < 0 ) {
            player2HP = 0;
        }

        float player2MP =  ((float)player2.getBall().getMana() / (float)player2MaxMp) ;
        if (player2MP < 0) {
            player2MP = 0;
        }

        batch.begin();
        //Player1
        batch.draw(leftBar, leftBar.getX(), leftBar.getY());
        batch.draw(hpBarLeftPlayer,hpBarLeftPlayer.getX(),hpBarLeftPlayer.getY(),hpBarLeftPlayer.getWidth()*player1HP,hpBarLeftPlayer.getHeight());
        batch.draw(manaBarLeftPlayer,manaBarLeftPlayer.getX(),manaBarLeftPlayer.getY(),manaBarLeftPlayer.getWidth()*player1MP,manaBarLeftPlayer.getHeight());


        //Player2
        batch.draw(rightBar, rightBar.getX(), rightBar.getY());
        batch.draw(hpBarRightPlayer, hpBarRightPlayer.getX(),hpBarRightPlayer.getY(),hpBarRightPlayer.getWidth()*player2HP,hpBarRightPlayer.getHeight() );
        batch.draw(manaBarRightPlayer, manaBarRightPlayer.getX(),manaBarRightPlayer.getY(),manaBarRightPlayer.getWidth()*player2MP,manaBarRightPlayer.getHeight() );

        batch.end();

    }

}
