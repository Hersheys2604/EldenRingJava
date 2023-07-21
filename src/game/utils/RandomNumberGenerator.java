package game.utils;

import java.util.Random;

/**
 * A random number generator
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Harshath Muruganantham
 *
 */
public class RandomNumberGenerator {

    /**
     * Get a random integer between 0 and bound
     * @param bound the upper bound of the random integer
     * @return a random integer between 0 and bound
     */
    public static int getRandomInt(int bound) {
        return bound > 0 ? new Random().nextInt(bound) : 0;
    }

    /**
     * Get a random integer between lowerBound and upperBound
     * @param lowerBound the lower bound of the random integer
     * @param upperBound the upper bound of the random integer
     * @return a random integer between lowerBound and upperBound
     */
    public static int getRandomInt(int lowerBound, int upperBound) {
        int range = upperBound - lowerBound + 1;
        return new Random().nextInt(range) + lowerBound;
    }

    /**
     * Get a true/false statement if the random number generated is less than the chance.
     * @param chance teh chance needed
     * @return a boolean depending on if that chance was met
     */
    public static boolean getChance(int chance) {
        return new Random().nextInt(100) < chance;
    }
}
