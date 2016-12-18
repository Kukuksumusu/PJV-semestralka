package castlewars.playable;

import castlewars.Castle;
import castlewars.playable.Card.Cost;

/**
 * Interface for cards
 * @author Kukuksumusu
 */
public interface Playable {
    /**
     * @param castle
     * @return true if this can be played by given castle
     */
    public boolean canPlay(Castle castle);
    /**
     * execute card effect on given castles
     * @param playerCastle
     * @param opponentCastle 
     * @throws castlewars.playable.Playable.GameEnd if the game ends
     * @throws castlewars.playable.Playable.CanNotPlayException if the card can't be played
     */
    public void play(Castle playerCastle, Castle opponentCastle) throws GameEnd, CanNotPlayException;
    /**
     * @return cost of the card
     */
    public Cost getCost();
    /**
     * @return name of the card
     */
    public String getName();
    
    /**
     * Exception signalizing that game ended
     */
    public static class GameEnd extends Exception {
        private final boolean winner;
  
        /**
         * @param winner should be true if the current player (the one that played the card) won
         */
        public GameEnd(boolean winner) {
            this.winner = winner;
        }
        /**
         * @return true if the player playing the card won, false otherwise
         */
        public boolean isWinner() {
            return winner;
        }
    }
    
    /**
     * Exception signalizing that playerCastle can't play this card (e.g. not enough resources)
     */
    public static class CanNotPlayException extends Exception {

        public CanNotPlayException() {
        }
    }
}
