
package castlewars.playable;

import castlewars.Castle;

/**
 * Abstract class representing a card that just builds player castle wall
 * @author Kukuksumusu
 */
public abstract class WallBuildCard extends Card{
    protected final int points;

    public WallBuildCard(String name, String description, Card.Cost cost, int points) {
        super(name, description, cost);
        this.points = points;
    }
    
    @Override
    public void makeEffect(Castle playerCastle, Castle opponentCastle) {
        playerCastle.changeWallHp(points);
    }
    
}