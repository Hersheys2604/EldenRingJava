package game.actions.trading;

import game.engine.actions.Action;
import game.engine.actors.Actor;
import game.engine.items.Item;
import game.engine.positions.GameMap;
import game.engine.weapons.WeaponItem;

/**
 * An Action that allows an Actor to exchange an Item for a WeaponItem.
 * @author Harshath Muruganantham
 * @see Action
 * @see Item
 * @see WeaponItem
 */
public class ExchangeAction extends Action {

    /**
     * The Item to be exchanged.
     */
    private Item item;

    /**
     * The WeaponItem to be exchanged for.
     */
    private WeaponItem weapon;

    /**
     * Constructor.
     * @param item the Item to be exchanged
     * @param weapon the WeaponItem to be exchanged for
     */
    public ExchangeAction(Item item, WeaponItem weapon) {
        setItem(item);
        setWeapons(weapon);
    }

    /**
     * Perform the Action of exchanging the item for the chosen weapon item.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of what happened that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        actor.removeItemFromInventory(getItem());
        actor.addWeaponToInventory(getWeapon());
        return actor + " exchanged " + getItem() + " for " + getWeapon();
    }

    /**
     * Returns a description of this ExchangeAction.
     * @param actor The actor performing the action.
     * @return a String description of this ExchangeAction.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " exchanges " + getItem() + " for " + getWeapon();
    }

    /**
     * Returns the Item to be exchanged.
     * @return the Item to be exchanged
     */
    public Item getItem() {
        return item;
    }

    /**
     * Sets the Item to be exchanged.
     * @param item the Item to be exchanged
     */
    public void setItem(Item item) {
        this.item = item;
    }

    /**
     * Returns the WeaponItem to be exchanged for.
     * @return the WeaponItem to be exchanged for
     */
    public WeaponItem getWeapon() {
        return weapon;
    }

    /**
     * Sets the WeaponItem to be exchanged for.
     * @param weapons the WeaponItem to be exchanged for
     */
    public void setWeapons(WeaponItem weapons) {
        this.weapon = weapons;
    }
}
