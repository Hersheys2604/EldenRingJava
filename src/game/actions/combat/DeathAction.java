package game.actions.combat;

import game.engine.actions.Action;
import game.engine.actions.ActionList;
import game.engine.actors.Actor;
import game.engine.items.Item;
import game.engine.positions.GameMap;
import game.engine.weapons.WeaponItem;
import game.characters.Status;
import game.characters.specialdeaths.SpecialDeathManager;
import game.items.rune.RuneManager;

/**
 * An action executed if an actor is killed.
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Harshath Muruganantham
 * @see Action
 */
public class DeathAction extends Action {

    /**
     * The Actor that is to be attacked
     */
    private Actor attacker;

    /**
     * Constructor.
     *
     * @param actor the Actor to attack
     */
    public DeathAction(Actor actor) {
        this.attacker = actor;
    }

    /**
     * When the target is killed, the items and weapons carried by target
     * will be dropped to the location in the game map where the target was
     *
     * @param target The actor performing the action.
     * @param map The map the actor is on.
     * @return result of the action to be displayed on the UI
     */
    @Override
    public String execute(Actor target, GameMap map) {
        RuneManager runeManager = RuneManager.getInstance();
        if (target.getDisplayChar() == '@'){
            SpecialDeathManager.getInstance().run(target, map);
            return "";
        }

        if (target.getDisplayChar() == 'Y'){
            SpecialDeathManager.getInstance().run(target, map);
            return "";
        }

        if (target.getDisplayChar() == 'X') {
            if (attacker.getDisplayChar() == '@') {
                runeManager.incrementRuneAmount(attacker, runeManager.getRuneAmount(target));
                int amount = runeManager.getRuneAmount(target);
                SpecialDeathManager.getInstance().run(target, map);

                return "\nPile Of Bones dropped " + amount + " runes.\nPile of Bones is Dead!";
            }

            SpecialDeathManager.getInstance().run(target, map);
            return "\nPile of Bones is Dead!";
        }

        if (target.hasCapability(Status.TURN_TO_SKELETON)) {
            SpecialDeathManager.getInstance().run(target, map);
            return "\n" + target + " turns into Pile of Bones!";
        }

        if (attacker.getDisplayChar() == '@') {
            runeManager.incrementRuneAmount(attacker, runeManager.getRuneAmount(target));
        }

        String result = "";
        ActionList dropActions = new ActionList();

        // drop all items
        for (Item item : target.getItemInventory())
            dropActions.add(item.getDropAction(target));
        for (WeaponItem weapon : target.getWeaponInventory())
            dropActions.add(weapon.getDropAction(target));
        for (Action drop : dropActions)
            drop.execute(target, map);
        // remove actor
        map.removeActor(target);
        if (attacker.getDisplayChar() == '@') {
            result = result + "\n" + target + " dropped " + RuneManager.getInstance().getRuneAmount(target) + " runes.";
        }
        result += System.lineSeparator() + menuDescription(target);
        return result;
    }

    /**
     * Returns a description of this DeathAction suitable to display in the menu.
     *
     * @param actor The actor performing the action.
     * @return a String, e.g. "Player is killed"
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " is killed.";
    }

    /**
     * Returns the key used in the menu to trigger this Action.
     *
     * @return 'k' for "kill"
     */
    public Actor getAttacker() {
        return attacker;
    }
}
