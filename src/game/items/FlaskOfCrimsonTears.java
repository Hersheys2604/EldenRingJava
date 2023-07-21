package game.items;

import game.engine.actors.Actor;
import game.engine.items.Item;
import game.engine.positions.GameMap;
import game.engine.positions.Location;
import game.actions.skills.FlaskHealAction;
import game.characters.players.Player;
import game.reset.ResetManager;
import game.reset.Resettable;

/**
 * A class that represents a FlaskOfCrimsonTears -> a type of item.
 * It is a healing item that can be used by the player to heal themselves.
 * It can be used twice before it is destroyed.
 * It is resettable.
 * @author Harshath Muruganantham
 * @see Item
 * @see Resettable
 */
public class FlaskOfCrimsonTears extends Item implements Resettable {

    /**
     * The amount of uses in the object.
     */
    private int uses = 2;

    /**
     * The maximum amount of uses in the object.
     */
    private int maxUses = 2;

    /**
     * The owner of the flask.
     */
    private Player owner;

    /**
     * Constructor.
     * @param owner The owner of the flask.
     */
    public FlaskOfCrimsonTears(Player owner) {
        super("Flask Of Crimson Tears",'f', false);
        setOwner(owner);
        ResetManager.getInstance().registerResettable(this);
        addAction(new FlaskHealAction(this));
        addCapability(ItemCapability.FLASK_OF_CRIMSON_TEARS);
    }

    /**
     * Returns the owner of the flask.
     * @return owner The owner of the flask.
     */
    public Player getOwner() {
        return owner;
    }

    /**
     * Returns the name of the flask.
     * @return name The name of the flask.
     */
    public String getName() {
        return "Flask Of Crimson Tears";
    }

    /**
     * Returns the amount of uses in the object.
     * @return uses The amount of uses in the object.
     */
    public int getUses() {
        return uses;
    }

    /**
     * Sets the amount of uses in the object.
     * @param uses The amount of uses in the object.
     */
    public void setUses(int uses) {
        this.uses = uses;
    }

    /**
     * Returns the maximum amount of uses in the object.
     * @return maxUses The maximum amount of uses in the object.
     */
    public int getMaxUses() {
        return maxUses;
    }

    /**
     * Sets the maximum amount of uses in the object.
     * @param maxUses The maximum amount of uses in the object.
     */
    public void setMaxUses(int maxUses) {
        this.maxUses = maxUses;
    }

    /**
     * Sets the owner of the flask.
     * @param owner The owner of the flask.
     */
    public void setOwner(Player owner) {
        this.owner = owner;
    }

    /**
     * Resets the amount of uses of this flask to 2.
     */
    @Override
    public void reset(GameMap map) {
        setUses(getMaxUses());
    }

    /**
     * At every turn check and run this method to simulate the passage of time.
     * @param currentLocation The location of the flask.
     * @param actor The actor holding the flask.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        if (hasCapability(ItemCapability.INCREASE_MAXIMUM_USES)){
            setMaxUses(getMaxUses() + 2);
            setUses(getMaxUses());
            removeCapability(ItemCapability.INCREASE_MAXIMUM_USES);
        }
    }
}
