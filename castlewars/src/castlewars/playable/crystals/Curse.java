
package castlewars.playable.crystals;

import castlewars.Castle;
import castlewars.playable.Card;

/**
 *
 * @author Kukuksumusu
 */
public class Curse extends Card{

    public Curse() {
        super("Curse", "All yours +1\nAll opponents -1", new Cost(0,0,45));
    }
    

    @Override
    protected void makeEffect(Castle playerCastle, Castle opponentCastle) throws GameEnd {
        playerCastle.changeBricks(1);
        playerCastle.changeBuilders(1);
        playerCastle.changeCrystals(1);
        try {
            playerCastle.changeHp(1);
        } catch (Castle.GameLost ex) {
            throw new GameEnd(false);
        } catch (Castle.GameWon ex) {
            throw new GameEnd(true);
        }
        playerCastle.changeMages(1);
        playerCastle.changeWallHp(1);
        playerCastle.changeWeapons(1);
        playerCastle.changeWeaponsmiths(1);
        
        opponentCastle.changeBricks(-1);
        opponentCastle.changeBuilders(-1);
        opponentCastle.changeCrystals(-1);
        try {
            opponentCastle.changeHp(-1);
        } catch (Castle.GameLost ex) {
            throw new GameEnd(true);
        } catch (Castle.GameWon ex) {
            throw new GameEnd(false);
        }
        opponentCastle.changeMages(-1);
        opponentCastle.changeWallHp(-1);
        opponentCastle.changeWeapons(-1);
        opponentCastle.changeWeaponsmiths(-1);
        
    }

}
