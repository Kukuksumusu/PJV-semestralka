
package castlewars.playable.bricks;

import castlewars.playable.CastleBuildCard;

/**
 * Base
 *  -effect: castle +2
 *  -cost: 1 bricks
 * @author Kukuksumusu
 */
public class Base extends CastleBuildCard {

    public Base() {
        super("Base", "Castle +2", new Cost(1,0,0), 2);
    }
 
}
