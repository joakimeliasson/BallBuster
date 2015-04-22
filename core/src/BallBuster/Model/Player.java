package BallBuster.Model;
/*
@author Joakim
*/
public class Player {
    private final int playerId;
    private String playerName;
    private final Ball ball;

    public Player(int playerId, String playerName, Ball ball){
        this.playerName = playerName;
        this.playerId = playerId;
        this.ball = ball;
    }
    public int getPlayerId(){
        return this.playerId;
    }

    public String getPlayerName() { return this.playerName; }

    public void setPlayerName(String playerName){
        this.playerName = playerName;
    }

    public Ball getPlayerBall() { return this.ball; }
}
