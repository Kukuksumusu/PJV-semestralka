package castlewars.playable.weapons;

import castlewars.playable.AttackCard;

/**
 * Knight
 *  -effect: Deal 4 damage
 *  -cost: 2 weapons
 * @author Kukuksumusu
 */
public class Knight extends AttackCard {
    public Knight() {
        super("Knight", "Attack 4", new Cost(0, 2, 0), 4);
    }
}
