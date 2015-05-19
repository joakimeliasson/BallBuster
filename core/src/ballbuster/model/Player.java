package ballbuster.model;
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
    private int speedKey;

    private boolean invertedKeys;
    private boolean speedUp;

    public Player(int playerId, String playerName, float startPosX, float startPosY){
        this.playerName = playerName;
        this.playerId = playerId;
        this.ball = new Ball(startPosX, startPosY);
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

    public void setKeys(int rightKey, int leftKey, int upKey, int downKey, int auraKey, int speedKey) {
        this.rightKey = rightKey;
        this.leftKey = leftKey;
        this.upKey = upKey;
        this.downKey = downKey;
        this.auraKey = auraKey;
        this.speedKey = speedKey;
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
    public int getSpeedKey(){ return speedKey; }

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
