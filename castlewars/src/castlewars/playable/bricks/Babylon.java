
package castlewars.playable.bricks;

import castlewars.playable.CastleBuildCard;

/**
 * Babylon
 *  -effect: castle +40
 *  -cost: 35 bricks
 * @author Kukuksumusu
 */
public class Babylon extends CastleBuildCard {

    public Babylon() {
        super("Babylon", "Castle +40", new Cost(35,0,0), 40);
    }
 
}