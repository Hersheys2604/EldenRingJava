package game.items.weapons;

import game.engine.actions.Action;
import game.engine.actors.Actor;
import game.engine.weapons.WeaponItem;
import game.actions.skills.UnsheatheAction;
import game.items.trading.Purchasable;
import game.items.trading.Sellable;
import game.trader.TradingStatus;


/**
 * Uchigatana is a weapon that can be purchased and sold from MerchantKale
 * 115 damage and 80% hit rate
 * @author Ziheng Liao
 * Modified by: Harshath Muruganantham
 * @see WeaponItem
 * @see Sellable
 * @see Purchasable
 */
public class Uchigatana extends WeaponItem implements Purchasable, Sellable {

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

    public Uchigatana() {
        super("Uchigatana", ')', 115, "sliced", 80);
        setSellAmount(500);
        setBuyAmount(5000);
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
        return new UnsheatheAction(this, target);
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
