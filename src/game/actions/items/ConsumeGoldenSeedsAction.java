package game.actions.items;

import game.engine.actions.Action;
import game.engine.actors.Actor;
import game.engine.items.Item;
import game.engine.positions.GameMap;
import game.items.GoldenSeed;
import game.items.ItemCapability;

/**
 * An Action that allows an Actor to cosume a Golden Seed to replenish and upgrade Flask Of Crimson Tears to have 2 more uses.
 * @author Harshath Muruganantham
 * @see Action
 * @see Item
 * @see GoldenSeed
 */
public class ConsumeGoldenSeedsAction extends Action {

    /**
     * The GoldenSeed to be consumed.
     */
    private GoldenSeed goldenSeed;

    /**
     * Constructor.
     * @param goldenSeed the GoldenSeed to be consumed
     */
    public ConsumeGoldenSeedsAction(GoldenSeed goldenSeed) {
        setGoldenSeed(goldenSeed);
    }

    /**
     * Perform the Action of consuming the GoldenSeed to replenish and upgrade Flask Of Crimson Tears to have 2 more uses.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of what happened that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        for (Item item: actor.getItemInventory()){
            if (item.hasCapability(ItemCapability.FLASK_OF_CRIMSON_TEARS)){
                item.addCapability(ItemCapability.INCREASE_MAXIMUM_USES);
                actor.removeItemFromInventory(getGoldenSeed());
            }
        }
        return "Flask Of Crimson Tears has been replenished and upgraded to have 2 more uses!";
    }

    /**
     * Returns a description of this ConsumeGoldenSeedsAction to be displayed in the menu.
     * @param actor The actor performing the action.
     * @return a String description of this ConsumeGoldenSeedsAction to be displayed in the menu.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " consumes Golden Seeds to replenish and upgrade Flask Of Crimson Tears to have 2 more uses!";
    }

    /**
     * Returns the GoldenSeed to be consumed.
     * @return the GoldenSeed to be consumed
     */
    public GoldenSeed getGoldenSeed() {
        return goldenSeed;
    }

    /**
     * Sets the GoldenSeed to be consumed.
     * @param goldenSeed the GoldenSeed to be consumed
     */
    public void setGoldenSeed(GoldenSeed goldenSeed) {
        this.goldenSeed = goldenSeed;
    }
}
