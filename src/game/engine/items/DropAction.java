package game.engine.items;

import game.engine.actions.Action;
import game.engine.actors.Actor;
import game.engine.positions.GameMap;

/**
 * An abstract drop action class
 */
public abstract class DropAction extends Action {
    /**
     * Current item
     */
    private final Item item;

    /**
     * Constructor.
     *
     * @param item the item to drop
     */
    public DropAction(Item item) {
        this.item = item;
    }

    /**
     * When executed, add the dropped item to the game map
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of the action suitable for feedback in the UI
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        map.locationOf(actor).addItem(item);
        return menuDescription(actor);
    }

    /**
     * A string describing the action suitable for displaying in the UI menu.
     *
     * @param actor The actor performing the action.
     * @return a String, e.g. "Player drops the potato"
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " drops the " + item;
    }
}
