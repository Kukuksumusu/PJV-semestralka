package castlewars.ai;

import castlewars.Castle;
import castlewars.Deck;
import castlewars.playable.Card;
import castlewars.playable.Playable;
import java.util.List;

/**
 * Base class for AI
 * @author Kukuksumusu
 */
public abstract class AI {
    protected Deck deck;
    protected List<Playable> hand;
    
    public void draw() {
        hand.add(deck.draw());
    }
    public abstract Playable chooseCard(Castle aiCastle, Castle playerCastle);
    public abstract Playable chooseDiscard(Castle aiCastle, Castle playerCastle);
}
