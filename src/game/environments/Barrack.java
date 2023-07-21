package game.environments;

import game.engine.positions.Ground;
import game.engine.positions.Location;
import game.characters.enemies.spawning.EnemyFactory;

/**
 * A class that represents a barrack.
 * @author Ziheng Liao
 * @see Ground
 */
public class Barrack extends Ground {

    /**
     * Constructor.
     */
    public Barrack() {
        super('B');
    }

    /**
     * Tick method that adds a GodrickSoldier to the location if the location is empty and the random number generator
     * returns true.
     * @param location The location of the ground.
     */
    @Override
    public void tick(Location location) {
        EnemyFactory.getInstance().addEnemy(location);
    }
}
