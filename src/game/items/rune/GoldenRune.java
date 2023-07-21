package game.items.rune;

import game.engine.actors.Actor;
import game.engine.items.Item;
import game.engine.items.PickUpAction;
import game.actions.items.ConsumeGoldenRuneAction;
import game.utils.RandomNumberGenerator;

/**
 * A class that represents a Golden Rune -> a type of rune.
 * @author Harshath Muruganantham
 * @see Item
 */
public class GoldenRune extends Item {

    /**
     * The amount of runes in the object.
     */
    private int amount;

    /***
     * Constructor
     */
    public GoldenRune() {
        super("Golden Rune",'*', true);
        setAmount(RandomNumberGenerator.getRandomInt(200,10000));
    }

    /**
     * Allows player to pick up the golden rune from ground.
     * @return pickupAction
     */
    @Override
    public PickUpAction getPickUpAction(Actor actor) {
        if (actor.getDisplayChar() == '@'){
            addAction(new ConsumeGoldenRuneAction(this));
            return super.getPickUpAction(actor);
        }
        else {
            return null;
        }
    }

    /**
     * Returns the amount of runes in the object.
     * @return amount The amount of runes in the object.
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Sets the amount of runes in the object.
     * @param amount The amount of runes in the object.
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }
}
