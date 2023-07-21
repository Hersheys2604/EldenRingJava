package game.actions.combat;

import game.engine.actions.Action;
import game.engine.actors.Actor;
import game.engine.positions.GameMap;

/**
 * An Action to despawn an Actor.
 * @author Harshath Muruganantham
 * @see Action
 */
public class DespawnAction extends Action {

    /**
     * Constructor.
     */
    public DespawnAction() {
    }

    /**
     * Despawn the actor from the map.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return the result of despawn action that can be displayed to the user
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        map.removeActor(actor);
        return actor + " has been removed from the map.";
    }

    /**
     * Returns a description of this action suitable to display in the menu.
     *
     * @param actor The actor performing the action.
     * @return a String, e.g. "Player is despawned"
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " has been removed from the map.";
    }
}
