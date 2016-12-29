
package castlewars.playable.bricks;

import castlewars.playable.WallBuildCard;

/**
 * Wall
 *  -effect: wall +6
 *  -cost: 3 bricks
 * @author Kukuksumusu
 */
public class Defence extends WallBuildCard {

    public Defence() {
        super("Defence", "Wall +6", new Cost(3,0,0), 6);
    }
 
}
