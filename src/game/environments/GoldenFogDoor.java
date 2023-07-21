package game.environments;

import game.engine.actions.ActionList;
import game.engine.actors.Actor;
import game.engine.positions.GameMap;
import game.engine.positions.Ground;
import game.engine.positions.Location;
import game.actions.travel.TravelRealmsAction;
import game.characters.Status;

/**
 * A class that represents a golden fog door.
 * @author Harshath Muruganantham
 * @see Ground
 * @see TravelRealmsAction
 */
public class GoldenFogDoor extends Ground {

    /**
     * The map that the door leads to.
     */
    private GameMap targetMap;

    /**
     * The location that the door leads to.
     */
    private Location targetLocation;

    /**
     * The name of the map that the door leads to.
     */
    private String targetMapName;


    /**
     * Constructor.
     * @param targetMap The map that the door leads to.
     * @param targetLocation The location that the door leads to.
     * @param targetMapName The name of the map that the door leads to.
     */
    public GoldenFogDoor(GameMap targetMap, Location targetLocation, String targetMapName) {
        super('D');
        setTargetLocation(targetLocation);
        setTargetMapName(targetMapName);
        setTargetMap(targetMap);
    }

    /**
     * Returns true if the actor can enter this ground.
     * @param actor The actor to check.
     * @return true if the actor can enter (possesses resting capability).
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return actor.hasCapability(Status.TRAVEL_BETWEEN_REALMS);
    }

    /**
     * Returns the allowable actions for the actor in the location.
     * @param actor The actor to check.
     * @param location The location of the ground.
     * @param direction The direction of the ground from the actor.
     * @return the allowable actions for the actor in the location.
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();
        if(actor.hasCapability(Status.TRAVEL_BETWEEN_REALMS)){
            actions.add(new TravelRealmsAction(getTargetMap(), getTargetLocation(), getTargetMapName()));
        }
        return actions;
    }

    /**
     * Returns the map that the door leads to.
     * @return the map that the door leads to.
     */
    public GameMap getTargetMap() {
        return targetMap;
    }

    /**
     * Sets the map that the door leads to.
     * @param targetMap the map that the door leads to.
     */
    public void setTargetMap(GameMap targetMap) {
        this.targetMap = targetMap;
    }

    /**
     * Returns the location that the door leads to.
     * @return the location that the door leads to.
     */
    public Location getTargetLocation() {
        return targetLocation;
    }

    /**
     * Sets the location that the door leads to.
     * @param targetLocation the location that the door leads to.
     */
    public void setTargetLocation(Location targetLocation) {
        this.targetLocation = targetLocation;
    }

    /**
     * Returns the name of the map that the door leads to.
     * @return the name of the map that the door leads to.
     */
    public String getTargetMapName() {
        return targetMapName;
    }

    /**
     * Sets the name of the map that the door leads to.
     * @param targetMapName the name of the map that the door leads to.
     */
    public void setTargetMapName(String targetMapName) {
        this.targetMapName = targetMapName;
    }
}
