package castlewars.playable;

import castlewars.Castle;

/**
 * Abstract class that represents a card
 * @author Kukuksumusu
 */
public abstract class Card implements Playable{
    private final Cost cost;
    private final String name;
    private final String description;

    public Card(String name, String description, Cost cost) {
        this.name = name;
        this.description = description;
        this.cost = cost;
    }

    @Override
    public String getName() {
        return name;
    }
    @Override
    public String getDescription() {
        return description;
    }
    @Override
    public Cost getCost() {
        return cost;
    }
    @Override
    public boolean canPlay(Castle castle) {
        return (castle.getBricks() >= getCost().getBricks()) && 
                (castle.getWeapons() >= getCost().getBricks()) &&
                (castle.getWeapons() >= getCost().getWeapons());
    }
    /**
     * Expends resources needed to play this card
     * @param castle from which you want to deduce the cost
     * @throws castlewars.playable.Playable.CanNotPlayException if the castle can not afford to play this card
     */
    protected void deductCost(Castle castle) throws CanNotPlayException {
        if (!canPlay(castle)) {
            throw new CanNotPlayException();
        }
        castle.changeBricks(getCost().getBricks());
        castle.changeWeapons(getCost().getWeapons());
        castle.changeCrystals(getCost().getCrystals());
    }
    
    /**
     * Class representing card cost
     */
    public static class Cost {
        private final int bricks;
        private final int weapons;
        private final int crystals;

        public Cost(int bricks, int weapons, int crystals) {
            this.bricks = bricks;
            this.weapons = weapons;
            this.crystals = crystals;
        }

        public int getBricks() {
            return bricks;
        }

        public int getWeapons() {
            return weapons;
        }

        public int getCrystals() {
            return crystals;
        }
        
    }
}
