package game.reset;

import game.engine.positions.GameMap;

import java.util.ArrayList;
import java.util.List;

/**
 * A reset manager class that manages a list of resettables.
 * @author Adrian Kristanto
 * Modified By: Harshath Muruganantham, Ziheng Liao
 */
public class ResetManager{
    /**
     * A list of resettables.
     */
    private List<Resettable> resettables;

    /**
     * A list of special resettables.
     */
    private List<SpecialResettables> specialResettables;

    /**
     * The instance of the reset manager.
     */
    private static ResetManager instance;

    /**
     * Constructor.
     */
    private ResetManager() {
        this.resettables = new ArrayList<>();
        this.specialResettables = new ArrayList<>();
    }

    /**
     * Runs the reset method of all resettables.
     * @param map The map to reset.
     */
    public void run(GameMap map) {
        for (Resettable resettable : resettables) {
            resettable.reset(map);
        }
    }

    /**
     * Returns the instance of the reset manager.
     * @return The instance of the reset manager.
     */
    public static ResetManager getInstance(){
        if (instance == null) {
            instance = new ResetManager();
        }
        return instance;
    }

    /**
     * Adds a resettable to the list of resettables.
     * @param resettable The resettable to add.
     */
    public void registerResettable(Resettable resettable) {
        this.resettables.add(resettable);
    }

    /**
     * Removes a resettable from the list of resettables.
     * @param resettable The resettable to remove.
     */
    public void removeResettable(Resettable resettable) {
        this.resettables.remove(resettable);
    }


    /**
     * Adds a resettable to the list of resettables.
     * @param specialResettables The resettable to add.
     */
    public void registerSpecialResettable(SpecialResettables specialResettables) {
        this.specialResettables.add(specialResettables);
    }

    /**
     * Removes a resettable from the list of resettables.
     * @param specialResettables The resettable to remove.
     */
    public void removeSpecialResettable(SpecialResettables specialResettables) {
        this.specialResettables.remove(specialResettables);
    }

    /**
     * Runs the special reset method of all resettables.
     * @param map The map to reset.
     */
    public void specialRun(GameMap map) {
        for (SpecialResettables specialResettables : this.specialResettables) {
            specialResettables.specialReset(map);
        }
    }

}
