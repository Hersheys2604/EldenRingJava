package game.characters.players.archetype;

import game.characters.players.Player;
import game.items.weapons.Uchigatana;

/**
 * Type of Archetype class called Samurai that stores the stats of the player based on teh Astrologer archetype.
 * @author Ho Seng
 * @see Player
 * @see Archetype
 */
public class Samurai extends Archetype{

    /**
     * Constructor.
     */
    public Samurai() {
        super();
        setHitPoints(455);
        setStartingWeapon(new Uchigatana());
    }


}
