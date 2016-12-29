
package castlewars.playable.weapons;

import castlewars.Castle;
import castlewars.playable.Card;

/**
 * Thief
 *  -effect: steal up to 6 of each resource from enemy castle
 *  -cost: 15 weapons
 * @author Kukuksumusu
 */
public class Thief extends Card {
    private final int RESOURCES = 6;

    public Thief() {
        super("Thief", "Steal up to 6 resources from opponent castle", new Cost(0,15,0));
    }
    
    @Override
    protected void makeEffect(Castle playerCastle, Castle opponentCastle) throws GameEnd {
        playerCastle.changeBricks(Math.min(opponentCastle.getBricks(), RESOURCES));
        playerCastle.changeWeapons(Math.min(opponentCastle.getWeapons(), RESOURCES));
        playerCastle.changeCrystals(Math.min(opponentCastle.getCrystals(), RESOURCES));
        
        opponentCastle.changeBricks(-RESOURCES);
        opponentCastle.changeWeapons(-RESOURCES);
        opponentCastle.changeCrystals(-RESOURCES);
    }

}
