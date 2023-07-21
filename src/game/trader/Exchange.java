package game.trader;

import game.engine.actions.ActionList;
import game.engine.items.Item;

/**
 * An interface that represents an exchange between two items.
 * @author Harshath Murugannatham
 */
public interface Exchange {

    /**
     * Adds an exchanging action to the item.
     * @param item The item to add the action to.
     * @return The action list of the item this can be exchanged for.
     */
    ActionList addExchangingAction(Item item);
}
