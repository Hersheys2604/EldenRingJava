package game.actions.skills;

import game.engine.actions.Action;
import game.engine.actors.Actor;
import game.engine.positions.GameMap;
import game.engine.weapons.Weapon;
import game.actions.combat.DeathAction;
import game.utils.RandomNumberGenerator;

/**
 * An Class to perform Unsheathe action ( a special skill of weapon to double damage ) .
 * @author Ziheng Liao
 * Modified By: Harshath Muruganantham
 * @see Action
 */
public class UnsheatheAction extends Action {

    /**
     * Weapon used for the attack
     */
    private Weapon weapon;

    /**
     * The Actor that is to be attacked
     */
    private Actor target;

    /**
     * Constructor.
     *
     * @param weapon the Weapon to attack with
     * @param target the Actor to attack
     */
    public UnsheatheAction(Weapon weapon, Actor target) {
        this.weapon = weapon;       // this might not be necessary
        this.target = target;
    }


    /**
     * Perform the Action of attacking the target using QuickStep Skill.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of what happened that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String result = "";
        result += actor + " unsheathes " + getWeapon() + " on " + getTarget();
        if (RandomNumberGenerator.getChance(60)) {
//            map.getActorAt(map.locationOf(target)).hurt(damage);      // this will be used if line 27 doesnt work
            getTarget().hurt(2*getWeapon().damage());

            result = result + "\n" + actor + " " + weapon.verb() + " " + map.getActorAt(map.locationOf(getTarget())) + " for " + 2*getWeapon().damage() + " damage.";

            if (!getTarget().isConscious()) {
                result += new DeathAction(actor).execute(getTarget(), map);
            }

        } else {
            result = result + "\n" + actor + " misses " + map.getActorAt(map.locationOf(getTarget())) + ".";
        }
        return result;
    }

    /**
     * Returns a descriptive string
     *
     * @param actor The actor performing the action.
     * @return a string
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " unsheathes " + getWeapon() + " on " + getTarget();
    }

    /**
     * Returns the weapon used for the attack
     *
     * @return the weapon used for the attack
     */
    public Weapon getWeapon() {
        return weapon;
    }

    /**
     * Sets the weapon used for the attack
     *
     * @param weapon the weapon used for the attack
     */
    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    /**
     * Returns the target of the attack
     *
     * @return the target of the attack
     */
    public Actor getTarget() {
        return target;
    }

    /**
     * Sets the target of the attack
     *
     * @param target the target of the attack
     */
    public void setTarget(Actor target) {
        this.target = target;
    }
}
