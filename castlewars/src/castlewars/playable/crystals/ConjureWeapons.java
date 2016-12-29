
package castlewars.playable.crystals;

import castlewars.Castle;
import castlewars.playable.Card;

/**
 * Conjure weapons
 *  -effect: weapons +8
 *  -cost: 4 crystals
 * @author Kukuksumusu
 */
public class ConjureWeapons extends Card{

    public ConjureWeapons() {
        super("Conjure weapons", "Weapons +8", new Cost(0,0,4));
    }
 
    @Override
    protected void makeEffect(Castle playerCastle, Castle opponentCastle) throws GameEnd {
        playerCastle.changeWeapons(8);
    }
    
}