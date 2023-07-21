package game.engine.items;

import game.engine.actors.Actor;
import game.engine.positions.GameMap;

/**
 * Special Action that allows Actors to drop items.
 */
public class DropItemAction extends DropAction {
	private final Item item;

	/**
	 * Constructor.
	 *
	 * @param item the item to drop
	 */
	public DropItemAction(Item item) {
		super(item);
		this.item = item;
	}

	/**
	 * Drop the item.
	 *
	 * @param actor The actor performing the action
	 * @param map The map the actor is on
	 * @return a description of the action suitable for feedback in the UI
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		actor.removeItemFromInventory(item);
		return super.execute(actor, map);
	}

}
