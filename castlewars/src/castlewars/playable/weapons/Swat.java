
package castlewars.playable.weapons;

import castlewars.Castle;
import castlewars.playable.Card;

/**
 *
 * @author Kukuksumusu
 */
public class Swat extends Card {

    public Swat() {
        super("Swat", "Opponent castle -10", new Cost(0,18,0));
    }

    @Override
    protected void makeEffect(Castle playerCastle, Castle opponentCastle) throws GameEnd {
        try {
            opponentCastle.changeHp(-10);
        } catch (Castle.GameLost ex) {
            throw new GameEnd(true);
        } catch (Castle.GameWon ex) {
            throw new GameEnd(false);
        }
    }

}
