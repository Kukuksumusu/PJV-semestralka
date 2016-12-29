package castlewars.playable;

import castlewars.Castle;

/**
 * Abstract class representing a card that just builds player castle
 * @author Kukuksumusu
 */
public abstract class CastleBuildCard extends Card{
    protected final int points;

    public CastleBuildCard(String name, String description, Card.Cost cost, int points) {
        super(name, description, cost);
        this.points = points;
    }
    
    @Override
    public void makeEffect(Castle playerCastle, Castle opponentCastle) throws GameEnd {
        try {
            playerCastle.changeHp(points);
        } catch (Castle.GameLost ex) {
            throw new GameEnd(true);
        }
    }
    
}