package castlewars.playable.crystals;

import castlewars.Castle;
import castlewars.playable.Card;

/**
 * Destroy crystals
 *  -effect: enemy crystals -8
 *  -cost: 4 crystals
 * @author Kukuksumusu
 */
public class DestroyCrystals extends Card {

    public DestroyCrystals() {
        super("Destroy crystals", "Enemy crystals -8", new Cost(0,0,4));
    }

    @Override
    protected void makeEffect(Castle playerCastle, Castle opponentCastle) throws GameEnd {
        opponentCastle.changeCrystals(-8);
    }
    
}