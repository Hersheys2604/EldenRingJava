package game.items.weapons;

import game.engine.actions.Action;
import game.engine.actors.Actor;
import game.engine.weapons.WeaponItem;
import game.actions.skills.SpinningAttackAction;
import game.items.trading.Purchasable;
import game.items.trading.Sellable;
import game.trader.TradingStatus;

/**
 * Scimitar is a weapon that can be purchased and sold from MerchantKale
 * 118 damage and 88% hit rate
 * @author Ho Seng
 * Modified by: Harshath Muruganantham, Ziheng Liao
 * @see WeaponItem
 * @see Sellable
 * @see Purchasable
 */
public class Scimitar extends WeaponItem implements Sellable, Purchasable{

    /**
     * The buy amount of the weapon
     */
    private int sellAmount;

    /**
     * The sell amount of the weapon
     */
    private int buyAmount;

    /**
     * Constructor.
     */
    public Scimitar() {
        super("Scimitar", 's', 118, "hits", 88);
        this.addCapability(TradingStatus.SELLABLE);
        this.addCapability(TradingStatus.PURCHASABLE);
        setBuyAmount(600);
        setSellAmount(100);
    }

    /**
     * Get the skill of the weapon which is spinnningAttackAction
     * @param target the target the weapon is going to attack
     * @param direction the direction of the weapon
     * @return the action of the weapon
     */
    @Override
    public Action getSkill(Actor target, String direction) {
        return new SpinningAttackAction(this);
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
