package game.actions;

import game.engine.actions.Action;
import game.engine.actors.Actor;
import game.engine.displays.Display;
import game.engine.positions.Exit;
import game.engine.positions.GameMap;
import game.engine.positions.Location;
import game.characters.players.archetype.Archetype;
import game.characters.summons.Ally;
import game.characters.summons.Invader;
import game.environments.SummonSign;
import game.utils.RandomNumberGenerator;

/**
 * An Action to summon a guest from another realm (i.e. activate a Summon Sign).
 * @author Ho Seng
 * @see Action
 * @see SummonSign
 * @see Location
 */
public class SummonAction extends Action{

    /**
     * The Summon Sign that is to be activated.
     */
    private SummonSign sign;

    /**
     * The location of the Summon Sign.
     */
    private Location location;


    /**
     * Constructor.
     * @param sign the Summon Sign to be activated
     * @param location the location of the Summon Sign
     */
    public SummonAction(SummonSign sign , Location location) {
        setLocation(location);
        setSign(sign);
    }

    /**
     * Activates the Summon Sign.
     * @param actor the Actor that is activating the Summon Sign
     * @param map the GameMap that the Actor is on
     * @return a String describing the result of the action
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Display display = new Display();
        String result = "";
        display.println(actor + " activates the summon sign");
        Archetype archetype = getSign().getSummons().get(RandomNumberGenerator.getRandomInt(0, getSign().getSummons().size() - 1));
        if (RandomNumberGenerator.getChance(50)) {
            for (Exit exit : getLocation().getExits()) {
                Location destination = exit.getDestination();
                if (!(destination.containsAnActor())) {
                    destination.addActor(new Ally(archetype));
                    result += "An ally has been summoned!";
                    break;
                }
            }
        } else {
            for (Exit exit : getLocation().getExits()) {
                Location destination = exit.getDestination();
                if (!(destination.containsAnActor())) {
                    destination.addActor(new Invader(archetype));
                    result += "An Invader has been summoned!";
                    break;
                }
            }
        }
        return result;
    }

    /**
     * Returns a description of this SummonAction.
     * @param actor the Actor that is activating the Summon Sign
     * @return a String description of this SummonAction
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " summons a guest from another realm";
    }

    /**
     * Returns the Summon Sign that is to be activated.
     * @return the Summon Sign that is to be activated
     */
    public SummonSign getSign() {
        return sign;
    }

    /**
     * Sets the Summon Sign that is to be activated.
     * @param sign the Summon Sign that is to be activated
     */
    public void setSign(SummonSign sign) {
        this.sign = sign;
    }

    /**
     * Returns the location of the Summon Sign.
     * @return the location of the Summon Sign
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Sets the location of the Summon Sign.
     * @param location the location of the Summon Sign
     */
    public void setLocation(Location location) {
        this.location = location;
    }
}
