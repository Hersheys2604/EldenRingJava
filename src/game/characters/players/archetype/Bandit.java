package game.characters.players.archetype;

import game.characters.players.Player;
import game.items.weapons.GreatKnife;

/**
 * Type of Archetype class called Bandit that stores the stats of the player based on teh Astrologer archetype.
 * @author Ho Seng
 * @see Player
 * @see Archetype
 */
public class Bandit extends Archetype{

    /**
     * Constructor.
     */
    public Bandit() {
        super();
        setHitPoints(414);
        setStartingWeapon(new GreatKnife());
        
    }
}
