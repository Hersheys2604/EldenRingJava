package game.environments;

import game.engine.actors.Actor;
import game.engine.positions.Ground;
import game.characters.Status;

/**
 * A class that represents the floor inside a building.
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by:
 * @see Ground
 */
public class Floor extends Ground {

	/**
	 * Constructor.
	 */
	public Floor() {
		super('_');
	}

	/**
	 * Returns true if the actor can enter this ground.
	 * @param actor The actor to check.
	 * @return true if the actor can enter (possesses resting capability).
	 */
	@Override
	public boolean canActorEnter(Actor actor) {
		return actor.hasCapability(Status.RESTING);
	}
}
