package game.characters.players.archetype;

import game.characters.players.Player;
import game.items.weapons.Club;

/**
 * Type of Archetype class called Wretch that stores the stats of the player based on teh Astrologer archetype.
 * @author Ho Seng
 * @see Player
 * @see Archetype
 */
public class Wretch extends Archetype{

    /**
     * Constructor.
     */
    public Wretch() {
        super();
        setHitPoints(414);
        setStartingWeapon(new Club());
    }
}
