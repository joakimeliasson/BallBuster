package ballbuster.model;

import java.util.ArrayList;

/*
@author Joakim
*/
public class Player {
    private final int playerId;
    private String playerName;
    private final Ball ball;

    private int upKey;
    private int leftKey;
    private int downKey;
    private int rightKey;
    private int auraKey;
    private int speedKey;

    private boolean invertedKeys;
    private boolean speedUp;

    private String message;

    public Player(int playerId, String playerName, float startPosX, float startPosY) {
        this.playerName = playerName;
        this.playerId = playerId;
        this.ball = new Ball(startPosX, startPosY);
        this.invertedKeys = false;
    }

    public int getPlayerId() {
        return this.playerId;
    }

    public String getPlayerName() {
        return this.playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Ball getBall() {
        return this.ball;
    }

    public void setKeys(int upKey, int leftKey, int downKey, int rightKey, int auraKey, int speedKey) {
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

    public int getAuraKey() {
        return auraKey;
    }

    public int getSpeedKey() {
        return speedKey;
    }

    public boolean hasInvertedKeys() {
        return invertedKeys;
    }

    public void invertKeys(boolean b) {
        this.invertedKeys = b;
    }

    public boolean hasSpeedUp() {
        return speedUp;
    }

    public void setSpeedUp(boolean b) {
        this.speedUp = b;
    }

    public void resetBall() {
        if (this.getBall().hasPowerUp()) {
            if (this.hasInvertedKeys()) {
                this.setKeys(this.getDownKey(), this.getRightKey(), this.getUpKey(), this.getLeftKey(), this.getAuraKey(), this.getSpeedKey());
                this.invertKeys(false);
            }
            this.getBall().setSpeed(0.5f);
            this.getBall().setHasPowerUp(false);
            this.setSpeedUp(false);
            message = "";
        }
    }

    public String applyPowerUp(PowerUp powerUp, ArrayList<Player> playerList) {
        switch (powerUp.getPowerUp()) {
            case "speedUp":
                this.setSpeedUp(true);
                this.getBall().setSpeed(this.getBall().getSpeed() * 2);
                message = "Obtained Faster Speed!";
                break;
            case "slowDown":
                this.getBall().setSpeed(0.02f);
                message = "Obtained Slower Speed!";
                break;
            case "invertKeys":
                this.invertKeys(true);
                this.setKeys(this.getDownKey(), this.getRightKey(), this.getUpKey(), this.getLeftKey(), this.getAuraKey(), this.getSpeedKey());
                message = "Inverted Keys!";
                break;
            case "damageOther":
                for (Player p : playerList) {
                    if (!p.equals(this)) {
                        p.getBall().shieldDamage(20);
                        message = "20 Damage to the Other Player!";
                    }
                }
                break;
            case "invertOther":
                for (Player p : playerList) {
                    if (!p.equals(this)) {
                        p.invertKeys(true);
                        p.getBall().setHasPowerUp(true);
                        p.setKeys(p.getDownKey(), p.getRightKey(), p.getUpKey(), p.getLeftKey(), p.getAuraKey(), p.getSpeedKey());
                        message = "Inverted keys for other player";
                    }
                }
                break;

        }
        return message;
    }

    public void applyHealthPack(PowerUp powerUp, ArrayList<Player> playerList) {
        if (powerUp.getPowerUp() == "healthPack") {
            this.getBall().addHealthToShield(10);
        }
    }

    public String getMessage() {
        return message;
    }

}
