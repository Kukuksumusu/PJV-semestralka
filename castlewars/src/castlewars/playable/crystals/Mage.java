
package castlewars.playable.crystals;

import castlewars.Castle;
import castlewars.playable.Card;

/**
 * Mage
 *  -effect: mages +1
 *  -cost: 8 crystals
 * @author Kukuksumusu
 */
public class Mage extends Card{

    public Mage() {
        super("Mage", "Mages +1", new Cost(0,0,8));
    }

    @Override
    protected void makeEffect(Castle playerCastle, Castle opponentCastle) throws GameEnd {
        playerCastle.changeMages(1);
    }
    
}
