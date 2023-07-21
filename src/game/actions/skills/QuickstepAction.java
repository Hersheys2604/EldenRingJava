package game.actions.skills;

import game.engine.actions.Action;
import game.engine.actors.Actor;
import game.engine.positions.Exit;
import game.engine.positions.GameMap;
import game.engine.positions.Location;
import game.engine.weapons.Weapon;
import game.actions.combat.DeathAction;
import game.utils.RandomNumberGenerator;
/**
 * An Action to attack another Actor and move to the closest available exit.
 * @author Ziheng Liao
 * Modified by: Harshath Muruganantham
 * @see Action
 */

public class QuickstepAction extends Action {

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
    public QuickstepAction(Weapon weapon, Actor target) {
        this.weapon = weapon;
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
        if (RandomNumberGenerator.getChance(getWeapon().chanceToHit())) {
            getTarget().hurt(getWeapon().damage());
            result += actor + " " + getWeapon().verb() + " " + getTarget() + " for " + getWeapon().damage() + " damage.";
            if (!getTarget().isConscious()) {
                result += new DeathAction(actor).execute(getTarget(), map);
            }
//            ActorLocationsIterator actorLocations = new ActorLocationsIterator();
            Location location = map.locationOf(actor);
            for (Exit exit : location.getExits()) {
                if (exit.getDestination().canActorEnter(actor) && exit.getDestination().containsAnActor() == false) {
                    map.moveActor(actor, exit.getDestination());
                    int x = exit.getDestination().x();
                    int y = exit.getDestination().y();
                    result = result + "\n" + actor + " moves to (" + x + ", " + y + ")";
                    return result;
                }
            }
            if (location == map.locationOf(actor)){
                result += actor + " performed quickstep but did not move.";
            }
        } else {
            result += actor + " did not perform quickstep on " + getTarget();
        }
        return result;
    }

    /**
     * Returns a descriptive string to display on the UI
     *
     * @param actor The actor performing the action.
     * @return a descriptive string to display on the UI
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " attacks " + getTarget() +  " and moves away";
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
     * Returns the Actor that is to be attacked
     *
     * @return the Actor that is to be attacked
     */
    public Actor getTarget() {
        return target;
    }

    /**
     * Sets the Actor that is to be attacked
     *
     * @param target the Actor that is to be attacked
     */
    public void setTarget(Actor target) {
        this.target = target;
    }
}
