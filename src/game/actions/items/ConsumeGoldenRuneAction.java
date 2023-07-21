package game.actions.items;

import game.engine.actions.Action;
import game.engine.actors.Actor;
import game.engine.positions.GameMap;
import game.items.rune.GoldenRune;
import game.items.rune.RuneManager;

/**
 * An Action that allows an Actor to consume a GoldenRune and gain a random amount of runes.
 * @author Harshath Muruganantham
 * @see Action
 * @see GoldenRune
 */
public class ConsumeGoldenRuneAction extends Action{

    /**
     * The GoldenRune to be consumed
     */
    private GoldenRune goldenRune;

    /**
     * Constructor.
     *
     * @param goldenRune the GoldenRune to be consumed
     */
    public ConsumeGoldenRuneAction(GoldenRune goldenRune) {
        setGoldenRune(goldenRune);
    }

    /**
     * Perform the Action of consuming the GoldenRune.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of what happened that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        RuneManager.getInstance().incrementRuneAmount(actor, getGoldenRune().getAmount());
        actor.removeItemFromInventory(getGoldenRune());
        return actor + " consumed " + getGoldenRune() +" and "+ actor + " has gained " + getGoldenRune().getAmount() + " runes.";
    }

    /**
     * Returns a description of this ConsumeGoldenRuneAction suitable to display in the menu.
     *
     * @param actor The actor performing the action.
     * @return a String, e.g. "Player consumes Golden Rune"
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " consumes " + getGoldenRune();
    }

    /**
     * Returns the amount of runes gained from consuming the GoldenRune.
     *
     * @return the amount of runes gained from consuming the GoldenRune
     */
    public GoldenRune getGoldenRune() {
        return goldenRune;
    }

    /**
     * Sets the GoldenRune to be consumed.
     *
     * @param goldenRune the GoldenRune to be consumed
     */
    public void setGoldenRune(GoldenRune goldenRune) {
        this.goldenRune = goldenRune;
    }
}
