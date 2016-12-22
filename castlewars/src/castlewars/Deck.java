package castlewars;

import castlewars.playable.Card;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Class representing Deck of cards
 * @author Jakub
 */
public class Deck {
    List<Card> cards;

    public Deck() {
        cards = new LinkedList<>();
    }
    
    public void addCard(Card card) {
        cards.add(card);
    }
    
    public void shuffle() {
        Collections.shuffle(cards);
    }
    
    public Card draw() {
        return cards.remove(0);
    }
}
