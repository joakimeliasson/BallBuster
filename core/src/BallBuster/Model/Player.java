package BallBuster.Model;
/*
@author Joakim
*/
public class Player {
    private final int playerId;
    private String playerName;
    private final Ball ball;

    private int leftKey;
    private int rightKey;
    private int upKey;
    private int downKey;
    private int auraKey;

    private String playerPowerUp;
    private boolean hasPowerUp;

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

    public Ball getBall() { return this.ball; }

    public void setKeys(int leftKey, int rightKey, int upKey, int downKey, int auraKey) {
        this.leftKey = leftKey;
        this.rightKey = rightKey;
        this.upKey = upKey;
        this.downKey = downKey;
        this.auraKey = auraKey;
    }
    public int getLeftKey() {
        return leftKey;
    }
    public int getRightKey() {
        return rightKey;
    }
    public int getUpKey() {
        return upKey;
    }
    public int getDownKey() {
        return downKey;
    }
    public int getAuraKey(){ return auraKey; }

    public String getPlayerPowerUp() {
        return this.playerPowerUp;
    }

    public void setPlayerPowerUp(String powerUp){
        this.playerPowerUp = powerUp;
    }

    public boolean playerHasPowerUp(){
        return hasPowerUp;
    }


}
