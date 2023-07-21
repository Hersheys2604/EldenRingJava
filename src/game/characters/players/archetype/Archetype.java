package game.characters.players.archetype;

import game.engine.weapons.WeaponItem;
import game.characters.players.Player;

/**
 * Archetype class that stores the stats of the player depending on the option chosen by the user.
 * @author Ho Seng
 * Modified By: Harshath Muruganantham
 * @see Player
 */
public abstract class Archetype {

    /**
     * The stats of the player depending on the option chosen by the user.
     */
    private int hitPoints;

    /**
     * The starting weapon of the player depending on the option chosen by the user.
     */
    private WeaponItem startingWeapon;

    /**
     * Constructor.
     */
    public Archetype() {
        setHitPoints(100);
        setStartingWeapon(null);
    }


    /**
     * Set the starting weapon of the player depending on the option chosen by the user.
     * @param startingWeapon the starting weapon of the player depending on the option chosen by the user.
     */
    public void setStartingWeapon(WeaponItem startingWeapon) {
        this.startingWeapon = startingWeapon;
    }

    /**
     * Get the starting weapon of the player depending on the option chosen by the user.
     * @return the starting weapon of the player depending on the option chosen by the user.
     */
    public WeaponItem getStartingWeapon() {
        return startingWeapon;
    }

    /**
     * Set the hit points of the player depending on the option chosen by the user.
     * @param hitPoints the hit points of the player depending on the option chosen by the user.
     */
    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    /**
     * Get the hit points of the player depending on the option chosen by the user.
     * @return the hit points of the player depending on the option chosen by the user.
     */
    public int getHitPoints() {
        return hitPoints;
    }

}
