package castlewars.playable;

import castlewars.Castle;

/**
 * Abstract class representing a card that just deals damage
 * @author Kukuksumusu
 */
public abstract class AttackCard extends Card{
    protected final int dmg;

    public AttackCard(String name, String description, Cost cost, int dmg) {
        super(name, description, cost);
        this.dmg = dmg;
    }
    
    @Override
    public void makeEffect(Castle playerCastle, Castle opponentCastle) throws GameEnd {
        try {
            opponentCastle.takeDamage(dmg);
        } catch (Castle.GameLost ex) {
            throw new GameEnd(true);
        }
    }
    
}
