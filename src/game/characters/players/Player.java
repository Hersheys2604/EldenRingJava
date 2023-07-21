package game.characters.players;

import game.engine.actions.Action;
import game.engine.actions.ActionList;
import game.engine.actors.Actor;
import game.engine.displays.Display;
import game.engine.displays.Menu;
import game.engine.items.Item;
import game.engine.positions.Exit;
import game.engine.positions.GameMap;
import game.engine.positions.Location;
import game.engine.weapons.IntrinsicWeapon;
import game.actions.combat.AttackAction;
import game.characters.Status;
import game.characters.players.archetype.Archetype;
import game.characters.specialdeaths.SpecialDeath;
import game.characters.specialdeaths.SpecialDeathManager;
import game.items.FlaskOfCrimsonTears;
import game.items.rune.IRune;
import game.items.rune.Rune;
import game.items.rune.RuneManager;
import game.reset.ResetManager;
import game.reset.Resettable;
import game.utils.FancyMessage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Class representing the Player. It implements the Resettable interface.
 * It carries around a club to attack a hostile creature in the Lands Between.
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Harshath Muruganantham
 * @see Actor
 * @see Resettable
 * @see IRune
 * @see SpecialDeath
 */
public class Player extends Actor implements Resettable, IRune, SpecialDeath {

	/**
	 * The menu that will be displayed when the player is playing.
	 */
	private final Menu menu = new Menu();

	/**
	 * Player's Last Visited Site of Lost Grace's Location.
	 */
	private Location lastRestedSiteLocation;


	/**
	 * Player's Last Visited Site of Lost Grace's Map.
	 */
	private GameMap lastRestedSiteMap;

	/**
	 * Player's Last Visited Location.
	 */
	private List<Location> lastVisited = new ArrayList<>();

	/**
	 * Stores the location and Rune item left by the player after Death.
	 */
	private Map<Rune, Location> runeLeftAfterDeath = new HashMap<>();

	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param archetype  The archetype of the player
	 * @param map         The map the player is on.
	 * @see Archetype
	 */
	public Player(String name, char displayChar, Archetype archetype, GameMap map) {
		super(name, displayChar, archetype.getHitPoints());
		this.addCapability(Status.RESTING);
		this.addCapability(Status.HOSTILE_TO_ENEMY);
		this.addCapability(Status.TRADING);		// allows the player to trade
		this.addCapability(Status.KILL_PILE_OF_BONES); //allows player to attack pile of bones
		this.addCapability(Status.FALLS_OFF_CLIFF);
		this.addCapability(Status.TRAVEL_BETWEEN_REALMS);
		this.addCapability(Status.SUMMONING); // allows use of SummonSign
		this.addCapability(Status.FINAL_BOSS_BATTLE);
		this.addCapability(Status.ALLY);
		this.addCapability(Status.INCREASE_HITPOINTS);
		this.addCapability(Status.FAST_TRAVEL);
		this.addItemToInventory(new FlaskOfCrimsonTears(this));
		this.addWeaponToInventory(archetype.getStartingWeapon());
		addLastVisited(map.locationOf(this));
		RuneManager.getInstance().storeRuneAmount(this, 0);
		ResetManager.getInstance().registerResettable(this);
		SpecialDeathManager.getInstance().registerSpecialDeath(this);
	}

	/**
	 * Returns the menu that will display all the options available to teh player at this turn and prompt the user to choose one.
	 * @return the menu that will be displayed when the player is playing.
	 */
	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
		if (getLastVisited().get(getLastVisited().size()-1) != map.locationOf(this)){
			addLastVisited(map.locationOf(this));
		}

		for (Item item: map.locationOf(this).getItems()){
			if (item.getDisplayChar() == '$'){
				map.locationOf(this).removeItem(item);
				break;
			}
		}

		// Handle multi-turn Actions
		display.println(this.name + " (" + this.hitPoints + "/" + this.getMaxHp() + "), Runes: " + getRuneAmount());
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();

		// return/print the console menu
		return menu.showMenu(this, actions, display);
	}

	/**
	 * Returns the list of actions that another actor can do to the player.
	 * @param otherActor the actor that wants to interact with the player
	 * @param direction the direction of the other actor relative to the player
	 * @param map the map that the player is currently on
	 * @return the list of actions that the player can do to another actor
	 */
	@Override
	public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
		ActionList actions = new ActionList();
		if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
			if (otherActor.getWeaponInventory().size() == 0) {
				actions.add(new AttackAction(this, direction));
			} else {
				actions.add(new AttackAction(this, direction, otherActor.getWeaponInventory().get(0)));
			}
		}
		return actions;
	}

	/**
	 * Reset the player's hit points to its maximum hit points and move the player to the last visited Site of Lost Grace location.
	 * @param map The map to reset.
	 */
	@Override
	public void reset(GameMap map) {
		for (Exit exit : map.locationOf(this).getExits()) {
			if (exit.getDestination().getGround().getDisplayChar() == 'U' && hasCapability(Status.IS_RESTING)) {
				setLastRestedSiteLocation(exit.getDestination());
				setLastRestedSiteMap(map);
				removeCapability(Status.IS_RESTING);
				break;
			}
		}
		if (map.locationOf(this).getGround().getDisplayChar() =='U' && hasCapability(Status.IS_RESTING)){
			setLastRestedSiteLocation(map.locationOf(this));
			setLastRestedSiteMap(map);
			removeCapability(Status.IS_RESTING);
		}


		this.hitPoints = this.getMaxHp();
		map.removeActor(this);
		map.moveActor(this, getLastRestedSiteMap().at(getLastRestedSiteLocation().x(), getLastRestedSiteLocation().y()));
	}

	/**
	 * The player's intrinsic weapon
	 * @return a punch as its intrinsic weapon
	 */
	@Override
	public IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(11, "punches");
	}

	/**
	 * Returns the amount of Rune the player has.
	 * @return the amount of Rune the player has.
	 */
	@Override
	public int getRuneAmount() {
		return RuneManager.getInstance().getRuneAmount(this);
	}

	/**
	 * Returns the location of the Player's Last Visited Site of Lost Grace.
	 * @return the location of the Player's Last Visited Site of Lost Grace.
	 */
	public Location getLastRestedSiteLocation() {
		return lastRestedSiteLocation;
	}

	/**
	 * Sets the location of the Player's Last Visited Site of Lost Grace.
	 * @param lastVisitedSite the location of the Player's Last Visited Site of Lost Grace.
	 */
	public void setLastRestedSiteLocation(Location lastVisitedSite) {
		this.lastRestedSiteLocation = lastVisitedSite;
	}

	/**
	 * Returns the list of the player's last visited locations.
	 * @return the list of the player's last visited locations.
	 */
	public List<Location> getLastVisited() {
		return lastVisited;
	}

	/**
	 * Sets the list of the player's last visited locations.
	 * @param lastVisited the list of the player's last visited locations.
	 */
	public void setLastVisited(List<Location> lastVisited) {
		this.lastVisited = lastVisited;
	}

	/**
	 * Adds a location to the list of the player's last visited locations.
	 * @param lastVisited the location to be added to the list of the player's last visited locations.
	 */
	public void addLastVisited(Location lastVisited) {
		this.lastVisited.add(lastVisited);
	}

	/**
	 * Returns the list of the player's dropped runes when the player died.
	 * @return the list of the player's dropped runes when the player died.
	 */
	public Map<Rune, Location> getRuneLeftAfterDeath() {
		return runeLeftAfterDeath;
	}

	/**
	 * Sets the list of the player's dropped runes when the player died.
	 * @param runeLeftAfterDeath the list of the player's dropped runes when the player died.
	 */
	public void setRuneLeftAfterDeath(Map<Rune, Location> runeLeftAfterDeath) {
		this.runeLeftAfterDeath = runeLeftAfterDeath;
	}

	/**
	 * Adds a rune to the list of the player's dropped runes when the player died.
	 * @param rune the rune to be added to the list of the player's dropped runes when the player died.
	 * @param location the location of the rune to be added to the list of the player's dropped runes when the player died.
	 */
	public void addRuneLeftAfterDeath(Rune rune, Location location) {
		this.runeLeftAfterDeath.put(rune, location);
	}

	/**
	 * Resets the list of the player's dropped runes when the player died to an empty list.
	 */
	public void resetRuneLeftAfterDeath() {
		this.runeLeftAfterDeath = new HashMap<>();
	}

	/**
	 * Player's Death Action
	 */
	@Override
	public void hasDied(GameMap map) {

		RuneManager runeManager = RuneManager.getInstance();
		for (Rune rune : getRuneLeftAfterDeath().keySet()) {
			Location location = getRuneLeftAfterDeath().get(rune);
			rune.removeRuneFromMap(location);
		}

		resetRuneLeftAfterDeath();
		Rune targetRune = new Rune(runeManager.getRuneAmount(this));

		Location location = getLastVisited().get(getLastVisited().size() - 1);
		location.addItem(targetRune);
		runeManager.storeRuneAmount(this, 0);

		addRuneLeftAfterDeath(targetRune, location);
		for (String line : FancyMessage.YOU_DIED.split("\n")) {
			new Display().println(line);
			try {
				Thread.sleep(200);
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}
		ResetManager.getInstance().run(map);
		ResetManager.getInstance().specialRun(map);		// this is to remove all the Allies and Enemies
	}

	/**
	 * Returns the map of the Player's Last Visited Site of Lost Grace.
	 * @return the map of the Player's Last Visited Site of Lost Grace.
	 */
	public GameMap getLastRestedSiteMap() {
		return lastRestedSiteMap;
	}

	/**
	 * Sets the map of the Player's Last Visited Site of Lost Grace.
	 * @param lastRestedSiteMap the map of the Player's Last Visited Site of Lost Grace.
	 */
	public void setLastRestedSiteMap(GameMap lastRestedSiteMap) {
		this.lastRestedSiteMap = lastRestedSiteMap;
	}
}


