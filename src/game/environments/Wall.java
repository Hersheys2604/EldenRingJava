package game.environments;

import game.engine.actors.Actor;
import game.engine.positions.Ground;

/**
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by:
 * @see Ground
 */
public class Wall extends Ground {

	/**
	 * Constructor.
	 */
	public Wall() {
		super('#');
	}

	/**
	 * Returns false because no actor can enter this ground.
	 * @param actor The actor to check.
	 * @return false because the actor cannot enter this ground.
	 */
	@Override
	public boolean canActorEnter(Actor actor) {
		return false;
	}

	/**
	 * Returns true because the actor cannot throw objects on this ground.
	 * @return true because the actor cannot throw objects on this ground.
	 */
	@Override
	public boolean blocksThrownObjects() {
		return true;
	}

}
