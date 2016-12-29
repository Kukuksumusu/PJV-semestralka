
package castlewars.playable.crystals;

import castlewars.Castle;
import castlewars.playable.Card;

/**
 * Destroy weapons
 *  -effect: enemy weapons -8
 *  -cost: 4 crystals
 * @author Kukuksumusu
 */
public class DestroyWeapons extends Card {

    public DestroyWeapons() {
        super("Destroy weapons", "Enemy weapons -8", new Cost(0,0,4));
    }

    @Override
    protected void makeEffect(Castle playerCastle, Castle opponentCastle) throws GameEnd {
        opponentCastle.changeWeapons(-8);
    }
    
}
