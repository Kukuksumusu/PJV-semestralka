package castlewars.playable;

import castlewars.Castle;

/**
 * Abstract class representing a card that just deals damage
 * @author Kukuksumusu
 */
public abstract class AttackCard extends Card{
    protected final int dmg;

    public AttackCard(String name, Cost cost, int dmg) {
        super(name, cost);
        this.dmg = dmg;
    }
    
    @Override
    public void play(Castle playerCastle, Castle opponentCastle) throws GameEnd, CanNotPlayException {
        deductCost(playerCastle);
        try {
            opponentCastle.takeDamage(dmg);
        } catch (Castle.GameLost ex) {
            throw new GameEnd(true);
        }
    }
    
}
