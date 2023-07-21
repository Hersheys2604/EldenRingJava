package game.environments;

import game.engine.positions.Ground;
import game.engine.positions.Location;
import game.characters.enemies.spawning.EnemyFactory;

/**
 * A class that represents a cage.
 * @author Ziheng Liao
 * @see Ground
 */
public class Cage extends Ground {

    /**
     * Constructor.
     */
    public Cage() {
        super('<');
    }

    /**
     * Tick method that adds a Dog to the location if the location is empty and the random number generator
     * returns true.
     * @param location The location of the ground.
     */
    @Override
    public void tick(Location location) {
        EnemyFactory.getInstance().addEnemy(location);
    }
}
