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
    private final List<Card> originalCards;
    private List<Card> cards;

    public Deck() {
        cards = new LinkedList<>();
        originalCards = new LinkedList<>();
    }
    /**
     * Adds card to the original deck AND to current cards in the deck.
     * That means, that added card will appear in every deck cycle.
     * Note: the card is inserted on the bottom of the deck.
     * @param card 
     */
    public void addCard(Card card) {
        originalCards.add(card);
        cards.add(card);
    }
    
    public void shuffle() {
        Collections.shuffle(cards);
    }
    
    public Card draw() {
        if (cards.isEmpty()) {
            cards = originalCards; //this duplicates cards in hand...TODO: fix
            shuffle();
        }
        return cards.remove(0);
    }
}
