package castlewars.ai;

import castlewars.Castle;
import castlewars.Deck;
import castlewars.playable.Card;
import java.util.List;

/**
 * Base class for AI
 * @author Kukuksumusu
 */
public abstract class AI {
    protected Deck deck;
    protected List<Card> hand;
    
    public void draw() {
        hand.add(deck.draw());
    }
    public abstract Card chooseCard(Castle aiCastle, Castle playerCastle);
    public abstract Card chooseDiscard(Castle aiCastle, Castle playerCastle);
}
