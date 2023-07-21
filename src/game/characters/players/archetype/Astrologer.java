package game.characters.players.archetype;

import game.characters.players.Player;
import game.items.weapons.AstrologerStaff;

/**
 * Type of Archetype class called Astrologer that stores the stats of the player based on teh Astrologer archetype.
 * @author Ho Seng
 * @see Player
 * @see Archetype
 */
public class Astrologer extends Archetype{

    /**
     * Constructor.
     */
    public Astrologer() {
        super();
        setHitPoints(396);
        setStartingWeapon(new AstrologerStaff());
    }
}
