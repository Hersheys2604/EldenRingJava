package game.actions.trading;

import game.engine.actions.Action;
import game.engine.actors.Actor;
import game.engine.items.Item;
import game.engine.positions.GameMap;
import game.engine.weapons.WeaponItem;
import game.items.rune.RuneManager;

/**
 * SellAction is an action that allows the actor to sell a weapon item to the shop.
 * The actor will gain the amount of runes required to sell the weapon item.
 * The actor will lose the weapon item.
 * @author Ziheng Liao
 * Modified By: Harshath Muruganantham
 * @see Action
 */
public class SellAction extends Action {

    /**
     * The weapon item to be sold
     */
    private WeaponItem weaponItem;

    /**
     * The amount of runes gained from selling the weapon item
     */
    private int sellAmount;

    private Item item;

    /**
     * Constructor.
     *
     * @param weaponItem the weapon item to be sold
     * @param sellAmount the amount of runes gained from selling the weapon item
     */
    public SellAction(WeaponItem weaponItem, int sellAmount) {
        setWeaponItem(weaponItem);
        setSellAmount(sellAmount);
    }

    /**
     * Constructor for item.
     *
     * @param item the item to be sold
     * @param sellAmount the amount of runes gained from selling the item
     */
    public SellAction(Item item, int sellAmount) {
        this.item = item;
        setSellAmount(sellAmount);
    }

    /**
     * Perform the Action of selling the weapon item.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of what happened that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        RuneManager runeManager = RuneManager.getInstance();
        runeManager.storeRuneAmount(actor, runeManager.getRuneAmount(actor) + getSellAmount());
        // actor loses weapon
        if (item != null) {
        	actor.removeItemFromInventory(item);
            return "You have sold " + item + " for " + getSellAmount() + " runes";
        }
        else {
            actor.removeWeaponFromInventory(getWeaponItem());
            return "You have sold " + getWeaponItem() + " for " + getSellAmount() + " runes";
        }
    }

    /**
     * Returns a description of this action suitable to display in the menu.
     *
     * @param actor The actor performing the action.
     * @return a String, e.g. "Player sells weapon for 10 runes"
     */
    @Override
    public String menuDescription(Actor actor) {
        if (item != null) {
            return actor + " sells " + item + " for " + getSellAmount() + " runes.";
        }
        else {
            return actor + " sells " + getWeaponItem() + " for " + getSellAmount() + " runes.";
        }
    }

    /**
     * Returns the weapon item to be sold.
     *
     * @return the weapon item to be sold
     */
    public WeaponItem getWeaponItem() {
        return weaponItem;
    }

    /**
     * Sets the weapon item to be sold.
     *
     * @param weaponItem the weapon item to be sold
     */
    public void setWeaponItem(WeaponItem weaponItem) {
        this.weaponItem = weaponItem;
    }

    /**
     * Returns the amount of runes gained from selling the weapon item.
     *
     * @return the amount of runes gained from selling the weapon item
     */
    public int getSellAmount() {
        return sellAmount;
    }

    /**
     * Sets the amount of runes gained from selling the weapon item.
     *
     * @param sellAmount the amount of runes gained from selling the weapon item
     */
    public void setSellAmount(int sellAmount) {
        this.sellAmount = sellAmount;
    }
}
