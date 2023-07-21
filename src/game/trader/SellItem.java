package game.trader;

import game.engine.actions.ActionList;
import game.engine.items.Item;

/**
 * An interface that represents an item that can be sold by the player.
 * @author Harshath Muruganantham
 * @see Item
 * @see ActionList
 */
public interface SellItem {

    /**
     * Adds a selling option to the item.
     * @param item The item to add the selling option to.
     * @return The action list of the item this can be sold for.
     */
    ActionList addSellingOptionItem(Item item);
}
