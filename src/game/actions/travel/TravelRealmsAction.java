package game.actions.travel;

import game.engine.actions.Action;
import game.engine.actors.Actor;
import game.engine.positions.GameMap;
import game.engine.positions.Location;

/**
 * An Action to travel to another realm (i.e. travel to another map) through Golden Fog Doors.
 * @author Harshath Muruganantham
 * @see Action
 * @see Location
 * @see GameMap
 * @see game.environments.GoldenFogDoor
 */
public class TravelRealmsAction extends Action {

    /**
     * The GameMap that the Actor is travelling to.
     */
    private GameMap targetMap;

    /**
     * The Location that the Actor is travelling to.
     */
    private Location targetLocation;

    /**
     * The name of the GameMap that the Actor is travelling to.
     */
    private String targetMapName;

    /**
     * Constructor.
     * @param map the GameMap that the Actor is travelling to
     * @param location the Location that the Actor is travelling to
     * @param targetMapName the name of the GameMap that the Actor is travelling to
     */
    public TravelRealmsAction(GameMap map, Location location, String targetMapName) {
        setTargetLocation(location);
        setTargetMapName(targetMapName);
        setTargetMap(map);
    }

    /**
     * Actor travels to the target GameMap (executes the action).
     * @param actor the Actor that is travelling
     * @param map the GameMap that the Actor is on
     * @return a String describing the result of the action
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        map.removeActor(actor);
        getTargetMap().addActor(actor, getTargetLocation());
        return actor + " travels to the " + getTargetMapName();
    }

    /**
     * Returns a description of this TravelRealmsAction.
     * @param actor the Actor that is travelling
     * @return a String description of this TravelRealmsAction
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " travels to " + getTargetMapName();
    }

    /**
     * Returns the GameMap that the Actor is travelling to.
     * @return the GameMap that the Actor is travelling to
     */
    public GameMap getTargetMap() {
        return targetMap;
    }

    /**
     * Sets the GameMap that the Actor is travelling to.
     * @param targetMap the GameMap that the Actor is travelling to
     */
    public void setTargetMap(GameMap targetMap) {
        this.targetMap = targetMap;
    }

    /**
     * Returns the Location that the Actor is travelling to.
     * @return the Location that the Actor is travelling to
     */
    public Location getTargetLocation() {
        return targetLocation;
    }

    /**
     * Sets the Location that the Actor is travelling to.
     * @param targetLocation the Location that the Actor is travelling to
     */
    public void setTargetLocation(Location targetLocation) {
        this.targetLocation = targetLocation;
    }

    /**
     * Returns the name of the GameMap that the Actor is travelling to.
     * @return the name of the GameMap that the Actor is travelling to
     */
    public String getTargetMapName() {
        return targetMapName;
    }

    /**
     * Sets the name of the GameMap that the Actor is travelling to.
     * @param targetMapName the name of the GameMap that the Actor is travelling to
     */
    public void setTargetMapName(String targetMapName) {
        this.targetMapName = targetMapName;
    }
}
