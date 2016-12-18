package castlewars.playable;


/**
 * Archer 
 *  -effect: deal 5 damage.
 *  -cost: 3 weapons.
 * @author Kukuksumusu
 */
public class Archer extends AttackCard {
  
    public Archer() {
        super("Archer", "Deal 5 damage", new Cost(0, 3, 0), 5);
    }
}
