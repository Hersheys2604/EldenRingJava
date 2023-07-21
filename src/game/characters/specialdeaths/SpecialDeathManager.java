package game.characters.specialdeaths;

import game.engine.actors.Actor;
import game.engine.positions.GameMap;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that manages all actors that have special death conditions.
 * This class is a singleton.
 * @author Harshath Muruganantham
 * @see SpecialDeath
 * @see game.characters.enemies.skeletons.PileOfBones
 * @see game.characters.players.Player
 * @see game.characters.enemies.skeletons.SkeletalBandit
 * @see game.characters.enemies.skeletons.HeavySkeletalSwordsman
 */
public class SpecialDeathManager{

    /**
     * Singleton instance of SpecialDeathManager.
     */
    private List<SpecialDeath> specialDeaths;

    /**
     * Singleton instance of SpecialDeathManager.
     */
    private static SpecialDeathManager instance;

    /**
     * Constructor.
     */
    private SpecialDeathManager() {
        this.specialDeaths = new ArrayList<>();
    }

    /**
     * Returns the singleton instance of SpecialDeathManager.
     * @return The singleton instance of SpecialDeathManager.
     */
    public static SpecialDeathManager getInstance(){
        if (instance == null) {
            instance = new SpecialDeathManager();
        }
        return instance;
    }

    /**
     * Adds actor to the list of all actors that have special death.
     * @param actor: actor to be added to this list
     */
    public void registerSpecialDeath(SpecialDeath actor) {
        this.specialDeaths.add(actor);
    }

    /**
     * Runs teh specialDeath method stored within the actor inputted.
     * @param actor: actor that has died
     * @param map: map that the actor is on
     */
    public void run(Actor actor, GameMap map) {
        for (SpecialDeath specialDeath : specialDeaths) {
            if (actor == specialDeath) {
                specialDeath.hasDied(map);
                break;
            }
        }
    }
}
