package castlewars.playable.weapons;

import castlewars.playable.AttackCard;


/**
 * Archer 
 *  -effect: deal 2 damage
 *  -cost: 1 weapons
 * @author Kukuksumusu
 */
public class Archer extends AttackCard {
  
    public Archer() {
        super("Archer", "Attack 2", new Cost(0, 1, 0), 2);
    }
}
