package game.environments;

import game.engine.actors.Actor;
import game.engine.positions.Ground;
import game.engine.positions.Location;
import game.actions.combat.DeathAction;
import game.characters.Status;

/**
 * A class that represents a cliff.
 * @author Harshath Muruganantham
 * @see Ground
 */
public class Cliff extends Ground {

    /**
     * Constructor.
     */
    public Cliff() {
        super('+');
    }


    /**
     * Returns true if the actor can enter this ground.
     * @param actor The actor to check.
     * @return true if the actor can enter (possesses FALLS_OFF_CLIFF capability).
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return actor.hasCapability(Status.FALLS_OFF_CLIFF);
    }

    /**
     * Tick method that kills the actor if the location contains an actor.
     * @param location The location of the ground.
     */
    @Override
    public void tick(Location location) {
        if (location.containsAnActor()) {
            new DeathAction(location.getActor()).execute(location.getActor(), location.map());
        }
    }
}
