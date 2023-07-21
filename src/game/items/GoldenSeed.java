package game.items;

import game.engine.actors.Actor;
import game.engine.items.Item;
import game.engine.items.PickUpAction;
import game.actions.items.ConsumeGoldenSeedsAction;

/**
 * A class that represents a Golden Seed -> a type of seed.
 * @author Harshath Muruganantham
 * @see Item
 */
public class GoldenSeed extends Item {

    /***
     * Constructor.
     */
    public GoldenSeed() {
        super("Golden Seed", '0', true);
    }

    /**
     * Allows player to pick up the golden seed from ground.
     * @return pickupAction
     */
    @Override
    public PickUpAction getPickUpAction(Actor actor) {
        if (actor.getDisplayChar() == '@'){
            addAction(new ConsumeGoldenSeedsAction(this));
            return super.getPickUpAction(actor);
        }
        else {
            return null;
        }
    }
}
