
package castlewars.playable.bricks;

import castlewars.playable.WallBuildCard;

/**
 * Wall
 *  -effect: wall +2
 *  -cost: 1 bricks
 * @author Kukuksumusu
 */
public class Wall extends WallBuildCard {
    
    public Wall() {
        super("Wall", "Wall +3", new Cost(1,0,0), 3);
    }
    
}
