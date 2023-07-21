package game.engine.items;

import game.engine.actions.Action;
import game.engine.actors.Actor;
import game.engine.positions.GameMap;

/**
 * An abstract pick up action class
 */
public abstract class PickUpAction extends Action {
    private final Item item;

    public PickUpAction(Item item) {
        this.item = item;
    }

    /**
     * When executed, remove the item from the location in the game map where the actor is currently standing on
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of the action suitable for feedback in the UI
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        map.locationOf(actor).removeItem(item);
        return menuDescription(actor);
    }

    /**
     * Describe the action in a format suitable for displaying in the menu.
     *
     * @see Action#menuDescription(Actor)
     * @param actor The actor performing the action.
     * @return a string, e.g. "Player picks up the rock"
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " picks up the " + item;
    }
}
