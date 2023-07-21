package game.items.trading;

import game.engine.weapons.WeaponItem;

import java.util.List;

/**
 * An interface that allows an item to be exchanged
 * @author Harshath Muruganantham
 */
public interface Exchangeable {

    /**
     * Get the list of weapons that this item can be exchanged for
     * @return the list of weapons that this item can be exchanged for
     */
    List<WeaponItem> getExchangeWeapons();
}
