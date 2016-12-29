
package castlewars.playable.bricks;

import castlewars.playable.CastleBuildCard;

/**
 * Fort
 *  -effect: castle +20
 *  -cost: 18 bricks
 * @author Kukuksumusu
 */
public class Fort extends CastleBuildCard {

    public Fort() {
        super("Fort", "Castle +20", new Cost(18,0,0), 20);
    }
 
}
