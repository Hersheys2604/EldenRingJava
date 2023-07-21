package game.items.weapons;

import game.engine.actions.Action;
import game.engine.actors.Actor;
import game.engine.weapons.WeaponItem;
import game.actions.skills.QuickstepAction;
import game.items.trading.Purchasable;
import game.items.trading.Sellable;
import game.trader.TradingStatus;

/**
 * GreatKnife is a weapon with
 * 75 damage and 70% hit rate
 * @author Ziheng Liao
 * Modified by: Harshath Muruganantham
 * @see WeaponItem
 * @see Sellable
 * @see Purchasable
 */
public class GreatKnife extends WeaponItem implements Sellable, Purchasable{

    /**
     * The buy amount of the weapon
     */
    private int buyAmount;

    /**
     * The sell amount of the weapon
     */
    private int sellAmount;

    /**
     * Constructor.
     */
    public GreatKnife() {
        super("Great Knife", '/', 75, "stabs", 70);
        setBuyAmount(3500);
        setSellAmount(350);
        this.addCapability(TradingStatus.SELLABLE);
        this.addCapability(TradingStatus.PURCHASABLE);
    }

    /**
     * Get the skill of the weapon
     * @param target the target the weapon is going to attack
     * @param direction the direction of the weapon
     * @return the action of the weapon
     */
    @Override
    public Action getSkill(Actor target, String direction) {
        return new QuickstepAction(this, target);
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
