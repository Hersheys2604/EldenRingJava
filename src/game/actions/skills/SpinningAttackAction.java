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
 * Class representing the spinningAttack. A child class of Action
 * Goal is to attack everyone around it
 * @author Harshath Muruganantham
 * @see Action
 */
public class SpinningAttackAction extends Action{

    /**
     * The weapon used for the attack
     */
    private Weapon weapon;

    /**
     * Constructor
     * @param weapon The weapon used for the attack
     */
    public SpinningAttackAction(Weapon weapon) {
        this.weapon = weapon;
    }

    /**
     * The spinning attack being executed
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a string describing the result of the attack
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String result = "";
        result += actor + " attacks their surrounding!";
        Location here = map.locationOf(actor);
        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();
            if (destination.containsAnActor()) {
                Actor target = destination.getActor();
                if (RandomNumberGenerator.getChance(getWeapon().chanceToHit())) {    //not everyone will get hit but everyone has equal chance to be hit
                    target.hurt(getWeapon().damage());     // actor takes damage
                    result = result + " \n" + actor + " " + getWeapon().verb() + " " + target + " for " + getWeapon().damage() + " damage.";
                    if (!target.isConscious()) {
                        result += new DeathAction(actor).execute(target, map);
                    }
                } else {
                    result = result + " \n" + actor + " misses " + target + ".";
                }
            }
        }

        return result;
    }

    /**
     * Returns a descriptive string
     * @param actor The actor performing the action.
     * @return a string of what happened
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " attacks anything in the surrounding with " + getWeapon();
    }

    /**
     * Returns the weapon used for the attack
     * @return the weapon used for the attack
     */
    public Weapon getWeapon() {
        return weapon;
    }

    /**
     * Sets the weapon used for the attack
     * @param weapon the weapon used for the attack
     */
    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }
}
