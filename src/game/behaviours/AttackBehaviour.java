package game.behaviours;

import game.engine.actions.Action;
import game.engine.actors.Actor;
import game.engine.positions.GameMap;
import game.actions.combat.AttackAction;

/**
 * AttackBehaviour allows the enemy to attack other characters if called upon (at each turn usually).
 * @author Harshath Muruganantham
 * @see Behaviour
 */
public class AttackBehaviour implements Behaviour{



    /**
     * The target to attack
     */
    private Actor target;

    /**
     * Constructor
     * @param target: Actor to attack
     */
    public AttackBehaviour(Actor target) {
        setTarget(target);
    }

    /**
     * Returns an AttackAction, with the first weapon in the enemy's artillery if teh enemy has weapons, else it
     * uses an intrinsic weapon.
     * @param actor: The actor performing the action.
     * @param map: The map the actor is on.
     * @return AttackAction if target is in range, otherwise null
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        if (actor.getWeaponInventory().size() > 0) {
            return new AttackAction(getTarget(), getTarget().toString(), actor.getWeaponInventory().get(0));
        } else {
            return new AttackAction(getTarget(), getTarget().toString());
        }
    }

    /**
     * Returns the target to attack
     * @return target
     */
    public Actor getTarget() {
        return target;
    }

    /**
     * Sets the target to attack
     * @param target: Actor to attack
     */
    public void setTarget(Actor target) {
        this.target = target;
    }
}
