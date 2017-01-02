
package castlewars.ai;

import castlewars.Castle;
import castlewars.playable.Playable;
import java.sql.Connection;
import java.util.Random;

/**
 * AI that performs basically random actions
 * @author Kukuksumusu
 */
public class BeginnerAI extends AI{

    public BeginnerAI(Connection connection) {
        super(AI.getDefaultDeck(connection));
    }

    @Override
    public Playable chooseCard(Castle aiCastle, Castle playerCastle) {
        Playable result = null;
        Random r = new Random();
        for (Playable card : hand) {
            if (card.canPlay(aiCastle)) {
                if (result == null || r.nextBoolean()) {
                    result = card;
                }
            }
        }
        return result;
    }

    @Override
    public Playable chooseAndDiscard(Castle aiCastle, Castle playerCastle) {
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
}
