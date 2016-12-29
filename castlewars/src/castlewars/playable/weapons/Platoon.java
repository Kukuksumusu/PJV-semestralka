
package castlewars.playable.weapons;

import castlewars.playable.AttackCard;

/**
 * Platoon
 *  -effect: deal 6 damage
 *  -cost: 4 weapons
 * @author Kukuksumusu
 */
public class Platoon extends AttackCard {

    public Platoon() {
        super("Platoon", "Attack 6", new Cost(0, 4, 0), 6);
    }
    
}
