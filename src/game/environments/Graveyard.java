package game.environments;

import game.engine.positions.Ground;
import game.engine.positions.Location;
import game.characters.enemies.spawning.EnemyFactory;


/**
 * A class that represents a Graveyard -> a type of environment.
 * @author Harshath Muruganantham
 * @see Ground
 * @see EnemyFactory
 */
public class Graveyard extends Ground {

    /**
     * Constructor.
     */
    public Graveyard() {
        super('n');
    }

    /**
     * Experiences the passage of time soo that it spawns a skeleton
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        EnemyFactory.getInstance().addEnemy(location);
    }
}
