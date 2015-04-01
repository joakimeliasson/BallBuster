package BallBuster.Model;

/**
 @author Joakim
 */
public class PlayerFactory {
    public Player addPlayer(int playerId, String playerName, Ball ball){return new Player(playerId,playerName, ball);}
}
