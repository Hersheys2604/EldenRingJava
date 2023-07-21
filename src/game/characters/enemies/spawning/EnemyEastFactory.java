package game.characters.enemies.spawning;

import game.engine.positions.Location;
import game.characters.enemies.dogs.LoneWolf;
import game.characters.enemies.seacreatures.GiantCrab;
import game.characters.enemies.skeletons.HeavySkeletalSwordsman;
import game.utils.RandomNumberGenerator;

/**
 * A factory class for creating enemies in the east of the map.
 * @author Harshath Muruganantham
 * @see EnemyFactory
 */
public class EnemyEastFactory {

    /**
     * Singleton instance of EnemyEastFactory.
     */
    private static EnemyEastFactory instance = null;

    /**
     * Constructor.
     */
    private EnemyEastFactory() {
    }

    /**
     * Returns the singleton instance of EnemyEastFactory.
     * @return The singleton instance of EnemyEastFactory.
     */
    public static EnemyEastFactory getInstance() {
        if (instance == null) {
            instance = new EnemyEastFactory();
        }
        return instance;
    }

    /**
     * Adds an enemy to the location if chance is met.
     * @param location: The location to add the enemy to.
     */
    public void addEnemy(Location location){
        if (location.getGround().getDisplayChar() == 'n' && RandomNumberGenerator.getChance(27)){
            if (location.getActor() == null) {
                location.addActor(new HeavySkeletalSwordsman());
            }
        } else if (location.getGround().getDisplayChar() == '&' && RandomNumberGenerator.getChance(33)){
            if (location.getActor() == null) {
                location.addActor(new LoneWolf());
            }
        } else if (location.getGround().getDisplayChar() == '~' && RandomNumberGenerator.getChance(2)){
            if (location.getActor() == null) {
                location.addActor(new GiantCrab());
            }
        }
    }
}
