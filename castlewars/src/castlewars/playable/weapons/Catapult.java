
package castlewars.playable.weapons;

import castlewars.playable.AttackCard;

/**
 * Catapult
 *  -effect: deal 12 damage
 *  -cost: 10 weapons
 * @author Kukuksumusu
 */
public class Catapult extends AttackCard {

    public Catapult() {
        super("Catapult", "Attack 12", new Cost(0, 10, 0), 12);
    }
    

}
