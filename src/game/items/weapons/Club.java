package game.items.weapons;

import game.engine.actions.Action;
import game.engine.actors.Actor;
import game.engine.weapons.WeaponItem;
import game.items.trading.Purchasable;
import game.items.trading.Sellable;
import game.trader.TradingStatus;

/**
 * A simple weapon that can be used to attack the enemy.
 * It deals 103 damage with 80% hit rate
 * @author Adrian Kristanto
 * Modified by: Ziheng Liao, Harshath Muruganantham
 * @see WeaponItem
 * @see Sellable
 * @see Purchasable
 */
public class Club extends WeaponItem implements Purchasable, Sellable{

    /**
     * The buy amount of the weapon
     */
    private int buyAmount;

    /**
     * The sell amount of the weapon
     */
    private int sellAmount;

    /**
     * Constructor
     */
    public Club() {
        super("Club", '!', 103, "bonks", 80);
        setBuyAmount(600);
        setSellAmount(100);
        this.addCapability(TradingStatus.SELLABLE);
    }

    /**
     * Get the skill of the weapon
     * @param target the target the weapon is going to attack
     * @param direction the direction of the weapon
     * @return null because the weapon does not have any skill
     */
    @Override
    public Action getSkill(Actor target, String direction) {
        return null;
    }

    /**
     * Get the buy amount of the weapon
     * @return the buy amount of the weapon
     */
    @Override
    public int getBuyAmount() {
        return buyAmount;
    }

    /**
     * Set the buy amount of the weapon
     * @param buyAmount the buy amount of the weapon
     */
    @Override
    public void setBuyAmount(int buyAmount) {
        this.buyAmount = buyAmount;
    }

    /**
     * Get the sell amount of the weapon
     * @return the sell amount of the weapon
     */
    @Override
    public int getSellAmount() {
        return sellAmount;
    }

    /**
     * Set the sell amount of the weapon
     * @param sellAmount the sell amount of the weapon
     */
    @Override
    public void setSellAmount(int sellAmount) {
        this.sellAmount = sellAmount;
    }
}
