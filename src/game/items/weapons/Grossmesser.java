package game.items.weapons;

import game.engine.actions.Action;
import game.engine.actors.Actor;
import game.engine.weapons.WeaponItem;
import game.actions.skills.SpinningAttackAction;
import game.items.trading.Sellable;
import game.trader.TradingStatus;

/**
 * a weapon used by SkeletalSwordsman. can use SpinningAttack
 * 115 damage and 85% hitrate
 *  @author Harshath Muruganantham
 *  Modified by: Ziheng Liao
 *  @see WeaponItem
 *  @see Sellable
 */
public class Grossmesser extends WeaponItem implements Sellable{

    /**
     * The amount of money the weapon can be sold for
     */
    private int sellAmount;

    /**
     * Constructor
     */
    public Grossmesser() {
        super("Grossmesser", '?', 115, "hits", 85);
        this.addCapability(TradingStatus.SELLABLE);
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
     * Get the amount of money the weapon can be sold for
     * @return the amount of money the weapon can be sold for
     */
    @Override
    public int getSellAmount() {
        return sellAmount;
    }

    /**
     * Set the amount of money the weapon can be sold for
     * @param sellAmount the amount of money the weapon can be sold for
     */
    @Override
    public void setSellAmount(int sellAmount) {
        this.sellAmount = sellAmount;
    }
}
