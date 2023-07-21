package game.actions.combat;

import game.engine.actions.Action;
import game.engine.actors.Actor;
import game.engine.positions.GameMap;
import game.engine.weapons.Weapon;
import game.utils.RandomNumberGenerator;

import java.util.Random;

/**
 * An Action to attack another Actor.
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Harshath Muruganantham
 * @see Action
 */
public class AttackAction extends Action {

	/**
	 * The Actor that is to be attacked
	 */
	private Actor target;

	/**
	 * The direction of incoming attack.
	 */
	private String direction;

	/**
	 * Random number generator
	 */
	private Random rand = new Random();

	/**
	 * Weapon used for the attack
	 */
	private Weapon weapon;

	/**
	 * Constructor.
	 * 
	 * @param target the Actor to attack
	 * @param direction the direction where the attack should be performed (only used for display purposes)
	 * @param weapon the weapon used for the attack
	 */
	public AttackAction(Actor target, String direction, Weapon weapon) {
		this.target = target;
		this.direction = direction;
		this.weapon = weapon;
	}

	/**
	 * Constructor with intrinsic weapon as default
	 *
	 * @param target the actor to attack
	 * @param direction the direction where the attack should be performed (only used for display purposes)
	 */
	public AttackAction(Actor target, String direction) {
		this.target = target;
		this.direction = direction;
	}

	/**
	 * When executed, the chance to hit of the weapon that the Actor used is computed to determine whether
	 * the actor will hit the target. If so, deal damage to the target and determine whether the target is killed.
	 *
	 * @param actor The actor performing the attack action.
	 * @param map The map the actor is on.
	 * @return the result of the attack, e.g. whether the target is killed, etc.
	 * @see DeathAction
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		if (getWeapon() == null) {
			setWeapon(actor.getIntrinsicWeapon());
		}

		if (!(getRand().nextInt(100) <= getWeapon().chanceToHit())) {
			return actor + " misses " + getTarget() + ".";
		}

		if (actor.getDisplayChar() != '@') {
				Action skill = getWeapon().getSkill(getTarget(),getDirection());
				if (skill != null) {
					if (RandomNumberGenerator.getChance(50)) {
						return skill.execute(actor, map);
				}
			}
		}

		int damage = getWeapon().damage();
		String result = actor + " " + getWeapon().verb() + " " + getTarget() + " for " + damage + " damage.";
		getTarget().hurt(damage);


		if (!getTarget().isConscious()) {
			result += new DeathAction(actor).execute(getTarget(), map);
		}

		return result;
	}

	/**
	 * Describes which target the actor is attacking with which weapon
	 *
	 * @param actor The actor performing the action.
	 * @return a description used for the menu UI
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " attacks " + getTarget() + " at " + getDirection() + " with " + (weapon != null ? weapon : "Intrinsic Weapon");
	}

	/**
	 * Returns the target of this attack.
	 *
	 * @return the target of this attack
	 */
	public Actor getTarget() {
		return target;
	}

	/**
	 * Sets the target of this attack.
	 *
	 * @param target the target of this attack
	 */
	public void setTarget(Actor target) {
		this.target = target;
	}

	/**
	 * Returns the direction of this attack.
	 *
	 * @return the direction of this attack
	 */
	public String getDirection() {
		return direction;
	}

	/**
	 * Sets the direction of this attack.
	 *
	 * @param direction the direction of this attack
	 */
	public void setDirection(String direction) {
		this.direction = direction;
	}

	/**
	 * Returns the random number generator.
	 *
	 * @return the random number generator
	 */
	public Random getRand() {
		return rand;
	}

	/**
	 * Sets the random number generator.
	 *
	 * @param rand the random number generator
	 */
	public void setRand(Random rand) {
		this.rand = rand;
	}

	/**
	 * Returns the weapon used for this attack.
	 *
	 * @return the weapon used for this attack
	 */
	public Weapon getWeapon() {
		return weapon;
	}

	/**
	 * Sets the weapon used for this attack.
	 *
	 * @param weapon the weapon used for this attack
	 */
	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}
}
