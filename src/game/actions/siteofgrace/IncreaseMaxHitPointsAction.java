package game.actions.siteofgrace;

import game.engine.actions.Action;
import game.engine.actors.Actor;
import game.engine.positions.GameMap;
import game.items.rune.RuneManager;

/**
 * An action that allows the player to increase their max hit points in the Site of Lost Grace in exchange for runes.
 * @author Harshath Muruganantham
 * @see Action
 * @see game.environments.SiteOfLostGrace
 * @see RuneManager
 */
public class IncreaseMaxHitPointsAction extends Action {

    /**
     * The cost of increasing max health in runes.
     */
    private int runeCost;

    /**
     * The instance of this class.
     */
    private static IncreaseMaxHitPointsAction instance;

    /**
     * Constructor.
     */
    private IncreaseMaxHitPointsAction() {
        setRuneCost(200);
    }

    /**
     * Returns the instance of this class (same one every time).
     * @return the instance of this class.
     */
    public static IncreaseMaxHitPointsAction getInstance() {
        if (instance == null) {
            instance = new IncreaseMaxHitPointsAction();
        }
        return instance;
    }

    /**
     * Executes the action of increasing the player's max hit points.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return A string describing the action.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        RuneManager runeManager = RuneManager.getInstance();
        if (runeManager.getRuneAmount(actor) >= getRuneCost()) {
            runeManager.decrementRuneAmount(actor, getRuneCost());
            actor.increaseMaxHp(48);
            setRuneCost(getRuneCost() + 100);
            return actor + " increases their max hit points by 48";
        } else {
            return actor + " does not have enough runes to increase their max hit points";
        }
    }

    /**
     * Returns a string describing the action.
     * @param actor The actor performing the action.
     * @return A string describing the action.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " purchases Vigor attribute for " + getRuneCost() + " runes";
    }

    /**
     * Returns the cost of increasing max health in runes.
     * @return the cost of increasing max health in runes.
     */
    public int getRuneCost() {
        return runeCost;
    }

    /**
     * Sets the cost of increasing max health in runes.
     * @param runeCost the cost of increasing max health in runes.
     */
    public void setRuneCost(int runeCost) {
        this.runeCost = runeCost;
    }
}
