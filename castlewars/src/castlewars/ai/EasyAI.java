
package castlewars.ai;

import castlewars.Castle;
import castlewars.playable.Playable;
import java.sql.Connection;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kukuksumusu
 */
public class EasyAI extends AI {

    public EasyAI(Connection connection) {
        super(AI.getDefaultDeck(connection));
    }

    @Override
    public Playable chooseCard(Castle aiCastle, Castle playerCastle) {
        Playable result = null;
        int max = Integer.MIN_VALUE;
        for (Playable card : hand) {
            if (card.canPlay(aiCastle)) {
                Castle myCastle = aiCastle.deepCopy();
                Castle opponentCastle = playerCastle.deepCopy();
                try {
                    card.play(myCastle, opponentCastle);
                } catch (Playable.GameEnd ex) {
                    if (ex.isWinner()) {
                        return card;
                    } else {
                        continue;
                    }
                } catch (Playable.CanNotPlayException ex) {
                    Logger.getLogger(EasyAI.class.getName()).log(Level.SEVERE, null, ex);
                }
                int newValue = evaluateCastles(myCastle, opponentCastle);
                if (max < newValue || result == null) {
                    max = newValue;
                    result = card;
                }
            }
        }
        return result;
    }

    @Override
    public Playable chooseAndDiscard(Castle aiCastle, Castle playerCastle) {
        //TODO do not make this random please => choose a card with highest cost
        Playable result = null;
        Random r = new Random();
        for (Playable card : hand) {
            if (!card.canPlay(aiCastle)) {
                if (result == null || r.nextBoolean()) {
                    result = card;
                }
            }
        }
        hand.remove(result);
        return result;
    }

    private int evaluateCastles(Castle myCastle, Castle opponentCastle) {
        int result = evaluateCastle(myCastle);
        result -= evaluateCastle(opponentCastle);
        return result;
    }

    private int evaluateCastle(Castle castle) {
        int result = 0;
        result += castle.getHp()*2;
        result += Math.sqrt(castle.getBricks() + castle.getCrystals() + castle.getWeapons());
        result += castle.getWallHp();
        result += (castle.getBuilders() + castle.getWeaponsmiths() + castle.getMages()) * 4;    
        return result;
    }
}
