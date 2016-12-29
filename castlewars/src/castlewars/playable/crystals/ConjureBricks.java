
package castlewars.playable.crystals;

import castlewars.Castle;
import castlewars.playable.Card;

/**
 * Conjure bricks
 *  -effect: bricks +8
 *  -cost: 4 crystals
 * @author Kukuksumusu
 */
public class ConjureBricks extends Card{

    public ConjureBricks() {
        super("Conjure bricks", "Bricks +8", new Cost(0,0,4));
    }
 
    @Override
    protected void makeEffect(Castle playerCastle, Castle opponentCastle) throws GameEnd {
        playerCastle.changeBricks(8);
    }
    
}
