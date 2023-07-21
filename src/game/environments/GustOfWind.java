package game.environments;

import game.engine.positions.Ground;
import game.engine.positions.Location;
import game.characters.enemies.spawning.EnemyFactory;


/**
 * A class that represents a GustOfWind -> a type of environment.
 * @author Harshath Muruganantham
 * @see Ground
 * @see EnemyFactory
 */

public class GustOfWind extends Ground{

    /**
     * Constructor.
     */
    public GustOfWind() {
        super('&');
    }

    /**
     * Experiences the passage of time soo that it spawns an object of Dog class
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        EnemyFactory.getInstance().addEnemy(location);
    }
}
