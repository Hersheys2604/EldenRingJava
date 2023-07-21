package game.characters.enemies.spawning;

import game.engine.positions.Location;
import game.characters.enemies.dogs.GiantDog;
import game.characters.enemies.seacreatures.GiantCrayfish;
import game.characters.enemies.skeletons.SkeletalBandit;
import game.utils.RandomNumberGenerator;

/**
 * A factory class for creating enemies in the west of the map.
 * This class is a part of the Factory design pattern.
 * @author Harshath Muruganantham
 * @see EnemyFactory
 */
public class EnemyWestFactory {

    /**
     * Singleton instance of EnemyWestFactory.
     */
    private static EnemyWestFactory instance = null;

    /**
     * Constructor.
     */
    private EnemyWestFactory() {
    }

    /**
     * Returns the singleton instance of EnemyWestFactory.
     * @return The singleton instance of EnemyWestFactory.
     */
    public static EnemyWestFactory getInstance() {
        if (instance == null) {
            instance = new EnemyWestFactory();
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
                location.addActor(new SkeletalBandit());
            }
        } else if (location.getGround().getDisplayChar() == '&' && RandomNumberGenerator.getChance(4)){
            if (location.getActor() == null) {
                location.addActor(new GiantDog());
            }
        } else if (location.getGround().getDisplayChar() == '~' && RandomNumberGenerator.getChance(1)){
            if (location.getActor() == null) {
                location.addActor(new GiantCrayfish());
            }
        }
    }
}
