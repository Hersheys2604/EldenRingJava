package game.reset;

import game.engine.positions.GameMap;

/**
 * A resettable interface
 * @author Adrian Kristanto
 *
 */
public interface Resettable {

    /**
     * Resets the resettable.
     * @param map The map to reset.
     */
    void reset(GameMap map);
}
