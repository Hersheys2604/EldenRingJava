package game.items.rune;

import game.engine.actors.Actor;

import java.util.HashMap;

/**
 * A class that manages the amount of runes for each actor.
 * @author Ziheng Lioa, Ho Seng
 * Modified by: Harshath Muruganantham
 */
public class RuneManager{

    /**
     * A HashMap that stores the amount of runes for each actor.
     */
    private HashMap<Actor, Integer> RuneManager = new HashMap<Actor, Integer>();

    /**
     * Singleton instance for this class
     */
    private static RuneManager instance = null;

    /**
     * Constructor
     */
    private RuneManager() {}

    /**
     * Returns an object of this class - always the same instance
     * @return An object of this class - always the same instance
     */
    public static RuneManager getInstance()
    {
        if(instance == null)
        {
            instance = new RuneManager();
        }
        return instance;
    }

    /**
     * Returns the amount of runes for the actor.
     * @param actor The actor to check.
     * @return The amount of runes for the actor.
     */
    public int getRuneAmount(Actor actor) {
        return RuneManager.get(actor);
    }

    /**
     * Sets the amount of runes for the actor.
     * @param actor The actor to check.
     * @param amount The amount of runes for the actor.
     */
    public void storeRuneAmount(Actor actor, int amount) {
        RuneManager.put(actor, amount);
    }

    /**
     * Increments the amount of runes for the actor.
     * @param actor The actor to check.
     * @param amount The amount of runes to increment for the actor.
     */
    public void incrementRuneAmount(Actor actor, int amount) {
        RuneManager.put(actor, RuneManager.get(actor) + amount);
    }

    /**
     * Decrements the amount of runes for the actor.
     * @param actor The actor to check.
     * @param amount The amount of runes to decrement for the actor.
     */
    public void decrementRuneAmount(Actor actor, int amount) {
        RuneManager.put(actor, RuneManager.get(actor) - amount);
    }
}
