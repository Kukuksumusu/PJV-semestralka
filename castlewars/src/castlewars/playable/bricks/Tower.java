
package castlewars.playable.bricks;

import castlewars.playable.CastleBuildCard;

/**
 * Tower
 *  -effect: castle +5
 *  -cost: 5 bricks
 * @author Kukuksumusu
 */
public class Tower extends CastleBuildCard {

    public Tower() {
        super("Tower", "Castle +5", new Cost(5,0,0), 5);
    }
 
}