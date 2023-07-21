package game.behaviours;

import game.engine.actions.Action;
import game.engine.actions.MoveActorAction;
import game.engine.actors.Actor;
import game.engine.positions.Exit;
import game.engine.positions.GameMap;
import game.engine.positions.Location;

/**
 * A class that figures out a MoveAction that will move the actor one step 
 * closer to a target Actor.
 * @see edu.monash.fit2099.demo.mars.Application
 *
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by: Harshath Muruganantham
 * @see Behaviour
 */
public class FollowBehaviour implements Behaviour {


	/**
	 * The target to follow
	 */
	private final Actor target;

	/**
	 * Constructor.
	 * 
	 * @param subject the Actor to follow
	 */
	public FollowBehaviour(Actor subject) {
		this.target = subject;
	}

	/**
	 * Returns a MoveActorAction that will move the actor one step
	 * closer to the target.
	 *
	 * @param actor the Actor acting
	 * @param map the GameMap containing the Actor
	 * @return a MoveActorAction or null if actor is already next to the target
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		if(!map.contains(target) || !map.contains(actor))
			return null;
		
		Location here = map.locationOf(actor);
		Location there = map.locationOf(target);

		int currentDistance = distance(here, there);
		for (Exit exit : here.getExits()) {
			Location destination = exit.getDestination();
			if (destination.canActorEnter(actor)) {
				int newDistance = distance(destination, there);
				if (newDistance < currentDistance) {
					System.out.println(actor + " follows " + target);
					return new MoveActorAction(destination, exit.getName());
				}
			}
		}

		return null;
	}

	/**
	 * Compute the Manhattan distance between two locations.
	 * 
	 * @param a the first location
	 * @param b the first location
	 * @return the number of steps between a and b if you only move in the four cardinal directions.
	 */
	private int distance(Location a, Location b) {
		return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
	}
}