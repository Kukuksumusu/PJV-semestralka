
package castlewars.playable.crystals;

import castlewars.Castle;
import castlewars.playable.Card;

/**
 * Destroy bricks
 *  -effect: enemy bricks -8
 *  -cost: 4 crystals
 * @author Kukuksumusu
 */
public class DestroyBricks extends Card {

    public DestroyBricks() {
        super("Destroy bricks", "Enemy bricks -8", new Cost(0,0,4));
    }

    @Override
    protected void makeEffect(Castle playerCastle, Castle opponentCastle) throws GameEnd {
        opponentCastle.changeBricks(-8);
    }
    
}
