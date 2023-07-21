package game.characters.specialdeaths;

import game.engine.positions.GameMap;

/**
 * Interface for actors that have special death conditions.
 * @author Harshath Muruganantham
 * @see game.characters.enemies.skeletons.PileOfBones
 * @see game.characters.players.Player
 * @see game.characters.enemies.skeletons.SkeletalBandit
 * @see game.characters.enemies.skeletons.HeavySkeletalSwordsman
 */
public interface SpecialDeath {

    /**
     * Method to be called when the actor dies.
     * @param map: The map the actor is on.
     */
    void hasDied(GameMap map);
}
