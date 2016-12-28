
package castlewars.ai;

import castlewars.Castle;
import castlewars.Deck;
import castlewars.playable.Archer;
import castlewars.playable.Playable;
import java.util.LinkedList;
import java.util.Random;

/**
 * AI that performs basically random actions
 * @author Kukuksumusu
 */
public class BeginnerAI extends AI{

    public BeginnerAI() {
        super();
        this.deck = new Deck();
        this.hand = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            deck.addCard(new Archer());
        }
        deck.shuffle();
        
        for (int i = 0; i < 5; i++) {
            hand.add(deck.draw());
        }
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
    public Playable chooseDiscard(Castle aiCastle, Castle playerCastle) {
        Playable result = null;
        Random r = new Random();
        for (Playable card : hand) {
            if (!card.canPlay(aiCastle)) {
                if (result == null || r.nextBoolean()) {
                    result = card;
                }
            }
        }
        return result;
    }
}
