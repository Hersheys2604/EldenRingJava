package game.actions.combat;

import game.engine.actions.Action;
import game.engine.actors.Actor;
import game.engine.positions.GameMap;
import game.characters.enemies.boss.GodrickTheGrafted;
import game.items.weapons.GraftedDragon;

/**
 * An Action that is entered when Godrick the Grafted reaches half its health.
 * Created by: Harshath Muruganantham
 * @see Action
 * @see GodrickTheGrafted
 */
public class HalfHealthAction extends Action {

    /**
     * Executes the action of removing the current weapon and adding a Grafted Dragon to the inventory.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return A string describing the action.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        actor.removeWeaponFromInventory(actor.getWeaponInventory().get(0));
        actor.addWeaponToInventory(new GraftedDragon());
        return actor + " switched their weapon to Grafted Dragon.";
    }

    /**
     * Returns a string describing the action.
     * @param actor The actor performing the action.
     * @return A string describing the action.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " has lost half of its health and has been granted a Grafted Dragon";
    }
}
