
package castlewars.playable.bricks;

import castlewars.Castle;
import castlewars.playable.Card;

/**
 * School
 *  -effect: +1 builder
 *  -cost: 8 bricks
 * @author Kukuksumusu
 */
public class School extends Card{

    public School() {
        super("School", "Builders +1", new Cost(8, 0, 0));
    }

    @Override
    protected void makeEffect(Castle playerCastle, Castle opponentCastle) throws GameEnd {
        playerCastle.changeBuilders(1);
    }

}
