package castlewars;

/**
 * Class representing castle
 * @author Kukuksumusu
 */
public class Castle {
    private int hp;
    private int wallHp;
    private int builders;
    private int weaponsmiths;
    private int mages;
    private int bricks;
    private int weapons;
    private int crystals;

    public Castle(int hp, int wallHp, int builders, int weaponsmiths, int mages, int wood, int weapons, int crystals) {
        this.hp = hp;
        this.wallHp = wallHp;
        this.builders = builders;
        this.weaponsmiths = weaponsmiths;
        this.mages = mages;
        this.bricks = wood;
        this.weapons = weapons;
        this.crystals = crystals;
    } 
    /**
     * 
     * @return hp of this castle's wall
     */
    public int getWallHp() {
        return wallHp;
    }
    /**
     * change hp of this castle's wall
     * @param change 
     */
    public void changeWallHp(int change) {
        wallHp += change;
        if (wallHp < 0) {
            wallHp = 0;
        }
    }
    /**
     * 
     * @return amount of builders this castle has
     */
    public int getBuilders() {
        return builders;
    }
    /**
     * change amount of builders this castle has
     * @param change 
     */
    public void changeBuilders(int change) {
        builders += change;
        if (builders < 0) {
            builders = 0;
        }
    }
    /**
     * 
     * @return amount of weaponsmiths this castle has
     */
    public int getWeaponsmiths() {
        return weaponsmiths;
    }
    /**
     * change amount of weaponsmiths this castle has
     * @param change 
     */
    public void changeWeaponsmiths(int change) {
        weaponsmiths += change;
        if (weaponsmiths < 0) {
            weaponsmiths = 0;
        }
    }
    /**
     * 
     * @return amount of mages this castle has
     */
    public int getMages() {
        return mages;
    }
    /**
     * changes amount of mages this castle has
     * @param change 
     */
    public void changeMages(int change) {
        mages += change;
        if (mages < 0) {
            mages = 0;
        }
    }
    /**
     * 
     * @return amount of bricks this castle has
     */
    public int getBricks() {
        return bricks;
    }
    /**
     * changes amount of bricks this castle has
     * @param change 
     */
    public void changeBricks(int change) {
        bricks += change;
        if (bricks < 0) {
            bricks = 0;
        }
    }
    /**
     * 
     * @return amount of weapons this castle has
     */
    public int getWeapons() {
        return weapons;
    }
    /**
     * changes amount of weapons this castle has
     * @param change 
     */
    public void changeWeapons(int change) {
        weapons += change;
        if (weapons < 0) {
            weapons = 0;
        }
    }
    /**
     * 
     * @return amount of crystals this castle has
     */
    public int getCrystals() {
        return crystals;
    }
    /**
     * changes amount of crystals this castle has
     * @param change 
     */
    public void changeCrystals(int change) {
        crystals += change;
        if (crystals < 0) {
            crystals = 0;
        }
    }
    /**
     * 
     * @return amount of hitpoints this castle has
     */
    public int getHp() {
        return hp;
    }
    /**
     * changes amount of hitpoints this castle has
     * @param change
     * @throws castlewars.Castle.GameLost 
     */
    public void changeHp(int change) throws GameLost {
        hp += change;
        if (hp <= 0) {
            throw new GameLost();
        }
    }
    /**
     * moves castle to next turn (adds resources accordingly)
     */
    public void nextTurn() {
        changeBricks(builders);
        changeWeapons(weaponsmiths);
        changeCrystals(mages);
    }
    /**
     * Deals damage to this castle
     * @param damage how much damage is supposed to be dealt (positive int)
     * @throws castlewars.Castle.GameLost 
     */
    public void takeDamage(int damage) throws GameLost {
        if (damage < 0) {
            throw new IllegalArgumentException("Damage can't be negative (that's not how healing works here)");
        }
        if (damage > wallHp) {
            changeHp(-(damage - wallHp));
            changeWallHp(-wallHp);
        } else {
            changeWallHp(-damage);
        }
    }
    /**
     * Exception signalizing that this castle (and it's owner) has lost (hp <= 0)
     */
    public static class GameLost extends Exception {

        public GameLost() {
        }
    }
}
