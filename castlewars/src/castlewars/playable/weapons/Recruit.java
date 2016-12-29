
package castlewars.playable.weapons;

import castlewars.Castle;
import castlewars.playable.Card;

/**
 * Recruit
 *  -effect: +1 weaponsmiths
 *  -cost: 8 weapons
 * @author Kukuksumusu
 */
public class Recruit extends Card {

    public Recruit() {
        super("Recruit", "Weaponsmiths +1", new Cost(0,8,0));
    }

    @Override
    public void makeEffect(Castle playerCastle, Castle opponentCastle) throws GameEnd {
        playerCastle.changeWeaponsmiths(1);
    }

}
