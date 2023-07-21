package game.items.rune;

import game.engine.actors.Actor;
import game.engine.items.Item;
import game.engine.items.PickUpAction;
import game.engine.positions.Location;

/**
 * A class that represents a rune.
 * @author Ziheng Liao, Ho Seng, Harshath Muruganantham
 * @see Item
 */
public class Rune extends Item {

    /**
     * The amount of runes in the object.
     */
    private int amount;

    /**
     * Constructor.
     * @param amount The amount of runes in the object.
     */
    public Rune(int amount) {
        super("Rune", '$', false);
        this.amount = amount;
    }

    /**
     * Returns the amount of runes in the object.
     * @return amount The amount of runes in the object.
     */
    @Override
    public PickUpAction getPickUpAction(Actor actor) {
        if (actor.getDisplayChar() == '@'){
            RuneManager.getInstance().incrementRuneAmount(actor,this.getAmount());
            return super.getPickUpAction(actor);
        }
        else {
            return null;
        }
    }


    /**
     * Removes this Rune from the map.
     * @param location The location of the rune.
     */
    public void removeRuneFromMap(Location location){
        location.removeItem(this);
    }

    /**
     * Returns the amount of runes in the object.
     * @return amount The amount of runes in the object.
     */
    public int getAmount() {    //used for displaying amount on screen
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
