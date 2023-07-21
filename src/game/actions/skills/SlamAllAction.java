package game.actions.skills;

import game.engine.actions.Action;
import game.engine.actors.Actor;
import game.engine.positions.Exit;
import game.engine.positions.GameMap;
import game.engine.positions.Location;
import game.actions.combat.DeathAction;
import game.utils.RandomNumberGenerator;
/**
 * Class representing the SlamAll attack by Giant Crab. It extends the Action class.
 * Goal is to attack everyone around it
 * @author Ziheng Liao
 * Modified by: Harshath Muruganantham
 * @see Action
 */
public class SlamAllAction extends Action {

    /**
     * Constructor.
     */
    public SlamAllAction() {
    }

    /**
     * Executing the Slam All attack which attacks everything in surrounding
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description (String) of what happened that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String result = actor + " attacks their surrounding!";
        Location here = map.locationOf(actor);
        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();
            if (destination.containsAnActor()) {
                Actor target = destination.getActor();
                if (RandomNumberGenerator.getChance(actor.getIntrinsicWeapon().chanceToHit())) {    //not everyone will get hit but everyone has equal chance to be hit
                    target.hurt(actor.getIntrinsicWeapon().damage());     // actor takes damage
                    result = result + "\n" +  actor + " " + actor.getIntrinsicWeapon().verb() + " " + target +
                            " for " +actor.getIntrinsicWeapon().damage() + " damage.";
                    if (!target.isConscious()) {
                        result += new DeathAction(actor).execute(target, map);}
                    } else {
                    result = result + "\n" + actor + " misses " + target + ".";
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
        return "Giant Crab slams all nearby actors";
    }
}
