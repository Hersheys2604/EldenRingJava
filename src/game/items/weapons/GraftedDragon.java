package game.items.weapons;

import game.engine.actions.Action;
import game.engine.actors.Actor;
import game.engine.weapons.WeaponItem;
import game.actions.skills.SpinningAttackAction;
import game.items.trading.Sellable;

/**
 * A weapon used by Godrick The Grafted with a damage os 89 and hitrate of 90%
 * @author Harshath Muruganantham
 * @see WeaponItem
 * @see Sellable
 */
public class GraftedDragon extends WeaponItem implements Sellable{

    /**
     * The amount of money the weapon can be sold for
     */
    private int sellAmount;

    /**
     * Constructor.
     */
    public GraftedDragon() {
        super("Grafted Dragon", 'N', 89, "hits", 90);
        setSellAmount(200);
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
}
