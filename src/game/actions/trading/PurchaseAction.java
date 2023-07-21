package game.actions.trading;

import game.engine.actions.Action;
import game.engine.actors.Actor;
import game.engine.positions.GameMap;
import game.engine.weapons.WeaponItem;
import game.items.rune.RuneManager;

/**
 * PurchaseAction is an action that allows the actor to purchase a weapon item from the shop.
 * The actor must have enough runes to purchase the weapon item.
 * The actor will gain the weapon item and lose the amount of runes required to purchase the weapon item.
 * @author Ziheng Liao
 * Modified By: Harshath Muruganantham
 * @see Action
 */
public class PurchaseAction extends Action {

    /**
     * The weapon item to be purchased
     */
    private WeaponItem weaponItem;

    /**
     * The amount of runes required to purchase the weapon item
     */
    private int buyAmount;

    /**
     * Constructor.
     *
     * @param weaponItem the weapon item to be purchased
     * @param buyAmount the amount of runes required to purchase the weapon item
     */
    public PurchaseAction(WeaponItem weaponItem, int buyAmount) {
        setWeaponItem(weaponItem);
        setBuyAmount(buyAmount);
    }

    /**
     * Perform the Action of purchasing the weapon item.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of what happened that can be displayed to the user.
     */
    public String execute(Actor actor, GameMap map) {

        RuneManager runeManager = RuneManager.getInstance();

        if (runeManager.getRuneAmount(actor) < getBuyAmount()) {
            String result = actor + " does not have enough runes to purchase " + getWeaponItem();
            return result;
        } else {
            runeManager.storeRuneAmount(actor, runeManager.getRuneAmount(actor) - getBuyAmount());
            // actor gains weapon
            actor.addWeaponToInventory(getWeaponItem());

            String result = actor + " has purchased " + getWeaponItem() + " for " + getBuyAmount();
            return result;
        }

    }

    /**
     * Returns a description of this PurchaseAction.
     *
     * @param actor The actor performing the action.
     * @return a String description of this PurchaseAction.
     */
    @Override
    public String menuDescription(Actor actor) {

        return actor + " purchases " + getWeaponItem() + " for " + getBuyAmount();
    }

    /**
     * Returns the weapon item to be purchased.
     *
     * @return the weapon item to be purchased
     */
    public WeaponItem getWeaponItem() {
        return weaponItem;
    }

    /**
     * Sets the weapon item to be purchased.
     *
     * @param weaponItem the weapon item to be purchased
     */
    public void setWeaponItem(WeaponItem weaponItem) {
        this.weaponItem = weaponItem;
    }

    /**
     * Returns the amount of runes required to purchase the weapon item.
     *
     * @return the amount of runes required to purchase the weapon item
     */
    public int getBuyAmount() {
        return buyAmount;
    }

    /**
     * Sets the amount of runes required to purchase the weapon item.
     *
     * @param buyAmount the amount of runes required to purchase the weapon item
     */
    public void setBuyAmount(int buyAmount) {
        this.buyAmount = buyAmount;
    }
}
