package game.reset;

import game.engine.positions.GameMap;

/**
 * A special resettable interface - used for special cases of resetting
 */
public interface SpecialResettables {

    /**
     * Resets the resettable.
     * @param map The map to reset.
     */
    void specialReset(GameMap map);
}
