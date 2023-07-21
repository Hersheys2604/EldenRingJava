package game.characters.enemies.spawning;

import game.engine.positions.Location;
import game.characters.enemies.soldiers.Dog;
import game.characters.enemies.soldiers.GodrickSoldier;
import game.utils.RandomNumberGenerator;

/**
 * A factory class for spawning enemies in specific environments.
 * This class is a part of the Factory design pattern.
 * @author Harshath Muruganantham
 * @see EnemyEastFactory
 * @see EnemyWestFactory
 */
public class EnemyFactory {

    /**
     * Singleton instance of EnemyFactory.
     */
    private static EnemyFactory instance = null;

    /**
     * Constructor.
     */
    private EnemyFactory() {
    }

    /**
     * Returns the singleton instance of EnemyFactory.
     * @return The singleton instance of EnemyFactory.
     */
    public static EnemyFactory getInstance() {
        if (instance == null) {
            instance = new EnemyFactory();
        }
        return instance;
    }

    /**
     * Adds an enemy to the location depending om if the location is on the east or west side of the map.
     * @param location: The location to add the enemy to.
     */
    public void addEnemy(Location location){
        if (location.getGround().getDisplayChar() == '<' && RandomNumberGenerator.getChance(37) && location.getActor() == null) {
            location.addActor(new Dog());
        } else if (location.getGround().getDisplayChar() == 'B' && RandomNumberGenerator.getChance(45) && location.getActor() == null) {
            location.addActor(new GodrickSoldier());
        } else if (location.x() <= location.map().getXRange().max()/2){
            EnemyEastFactory.getInstance().addEnemy(location);
        }
        else{
            EnemyWestFactory.getInstance().addEnemy(location);
        }
    }
}
