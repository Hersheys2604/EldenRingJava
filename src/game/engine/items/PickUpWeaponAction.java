package game.engine.items;

import game.engine.actions.Action;
import game.engine.actors.Actor;
import game.engine.positions.GameMap;
import game.engine.weapons.WeaponItem;

/**
 * Action to allow weapons to be picked up.
 */
public class PickUpWeaponAction extends PickUpAction {
    private final WeaponItem weapon;

    /**
     * Constructor.
     *
     * @param weapon the weapon to pick up
     */
    public PickUpWeaponAction(WeaponItem weapon) {
        super(weapon);
        this.weapon = weapon;
    }

    /**
     * Add the weapon to the actor's inventory.
     *
     * @see Action#execute(Actor, GameMap)
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a suitable description to display in the UI
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        actor.addWeaponToInventory(weapon);
        return super.execute(actor, map);
    }
}
