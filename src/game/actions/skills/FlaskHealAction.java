package game.actions.skills;

import game.engine.actions.Action;
import game.engine.actors.Actor;
import game.engine.positions.GameMap;
import game.items.FlaskOfCrimsonTears;

/**
 * An Action to heal the actor by 250 health points.
 * @author Harshath Muruganantham
 * @see Action
 */
public class FlaskHealAction extends Action {

    /**
     * The flask to be used.
     */
    private FlaskOfCrimsonTears flask;

    /**
     * Constructor.
     *
     * @param flask The flask to be used.
     */
    public FlaskHealAction(FlaskOfCrimsonTears flask) {
        setFlask(flask);
    }

    /**
     * Heals the actor by 250 health points if the flask has uses left.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a String, e.g. "Player consumes Flask of Crimson Tears (1/2)"
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (getFlask().getUses() > 0) {
            getFlask().getOwner().heal(250);
            getFlask().setUses(getFlask().getUses() - 1);
            return getFlask().getOwner() + " consumes " + getFlask().getName() + " (" + getFlask().getUses() + "/2)";
        }
        return getFlask().getOwner() + " has no more " + getFlask().getName() + " to consume";
    }

    /**
     * Returns a description of this action suitable to display in the menu.
     *
     * @param actor The actor performing the action.
     * @return a String, e.g. "Player consumes Flask of Crimson Tears (1/2)"
     */
    @Override
    public String menuDescription(Actor actor) {
        return getFlask().getOwner() + " consumes " + getFlask().getName() + " (" + getFlask().getUses() + "/" + getFlask().getMaxUses() + ")";
    }

    /**
     * Returns the flask to be used.
     *
     * @return the flask to be used.
     */
    public FlaskOfCrimsonTears getFlask() {
        return flask;
    }

    /**
     * Sets the flask to be used.
     *
     * @param flask the flask to be used.
     */
    public void setFlask(FlaskOfCrimsonTears flask) {
        this.flask = flask;
    }
}
