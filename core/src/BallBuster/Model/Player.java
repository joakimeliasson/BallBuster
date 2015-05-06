package BallBuster.Model;
/*
@author Joakim
*/
public class Player {
    private final int playerId;
    private String playerName;
    private final Ball ball;
    private PowerUp powerUp;

    private int leftKey;
    private int rightKey;
    private int upKey;
    private int downKey;
    private int auraKey;

    private String playerPowerUp;
    private boolean hasPowerUp;

    private boolean invertedKeys;
    private boolean speedUp;

    public Player(int playerId, String playerName, Ball ball){
        this.playerName = playerName;
        this.playerId = playerId;
        this.ball = ball;
        this.invertedKeys = false;
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
        return powerUp.getPowerUp();
    }

    public void setPlayerPowerUp(String newPowerUp){
        powerUp.setPowerUp(newPowerUp);
    }

    public boolean playerHasPowerUp(){
        return (getPlayerPowerUp() != null);
    }

    public boolean hasInvertedKeys(){
        return invertedKeys;
    }

    public void invertKeys(boolean b){
        this.invertedKeys = b;
    }

    public boolean hasSpeedUp(){
        return speedUp;
    }

    public void setSpeedUp(boolean b){
        this.speedUp = b;
    }


}
