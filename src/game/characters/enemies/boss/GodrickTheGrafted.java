package game.characters.enemies.boss;

import game.engine.actions.Action;
import game.engine.actions.ActionList;
import game.engine.actions.DoNothingAction;
import game.engine.actors.Actor;
import game.engine.displays.Display;
import game.engine.positions.Exit;
import game.engine.positions.GameMap;
import game.engine.positions.Location;
import game.engine.weapons.WeaponItem;
import game.actions.combat.AttackAction;
import game.actions.combat.HalfHealthAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.Behaviour;
import game.behaviours.FollowBehaviour;
import game.behaviours.WanderBehaviour;
import game.characters.Status;
import game.characters.specialdeaths.SpecialDeath;
import game.characters.specialdeaths.SpecialDeathManager;
import game.environments.SiteOfLostGrace;
import game.items.RemembranceOfTheGrafted;
import game.items.rune.RuneManager;
import game.items.weapons.AxeOfGodrick;
import game.reset.ResetManager;
import game.reset.Resettable;
import game.utils.FancyMessage;

import java.util.HashMap;
import java.util.Map;

/**
 * Class representing the final boss of the game, Godrick the Grafted.
 * @author Harshath Muruganantham
 * @see Actor
 * @see Resettable
 * @see SpecialDeath
 */
public class GodrickTheGrafted extends Actor implements Resettable, SpecialDeath {

    /**
     * Map of behaviours of this character, with priority as key.
     */
    private Map<Integer, Behaviour> behaviours = new HashMap<>();

    /**
     * The starting location of the boss.
     */
    private Location startingLocation;

    /**
     * Boolean to represent whether to boss has turned into the second half
     */
    private boolean secondStage = false;

    /**
     * Boolean to represent whether the boss has died.
     */
    private boolean hasDied = false;

    /**
     * Constructor.
     * @param startingLocation: The starting location of the boss.
     */
    public GodrickTheGrafted(Location startingLocation) {
        super("Godrick the Grafted", 'Y', 6080);
        setStartingLocation(startingLocation);
        addCapability(Status.HOSTILE_TO_ENEMY);
        addWeaponToInventory(new AxeOfGodrick());
        SpecialDeathManager.getInstance().registerSpecialDeath(this);
        ResetManager.getInstance().registerResettable(this);
        RuneManager.getInstance().storeRuneAmount(this, 20000);
        addBehaviour(999, new WanderBehaviour());
    }

    /**
     * At each turn, select a valid action to perform.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the valid action that can be performed in that iteration or null if no valid action is found
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

        if (this.hitPoints <= this.getMaxHp()/2 && !isSecondStage()){
            setSecondStage(true); // meaning we are now at the second half of the boss fight
            return new HalfHealthAction();
        }


        Location here = map.locationOf(this);
        if (getBehaviour(997) == null){
            for (Exit exit : here.getExits()) {
                Location destination = exit.getDestination();
                if (destination.containsAnActor()) {
                    addBehaviour(997, new AttackBehaviour(destination.getActor()));
                    if (destination.getActor().getDisplayChar() == '@') {
                        addBehaviour(998, new FollowBehaviour(destination.getActor()));
                    }
                }

            }
        }

        for (Behaviour behaviour : getBehaviours().values()) {
            Action action = behaviour.getAction(this, map);
            if(action != null){
                if (getBehaviours().containsKey(997)) {
                    getBehaviours().remove(997);
                }
                return action;
            }
        }
        return new DoNothingAction();
    }

    /**
     * Returns a list of allowable actions an actor can do to this class.
     * @param otherActor: The actor that is being attacked.
     * @param direction: The direction of the attack.
     * @param map: The map that the dog is on.
     * @return A list of allowable actions for the dog.
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if (otherActor.getWeaponInventory().size() == 0) {
            actions.add(new AttackAction(this, direction));
        } else {
            actions.add(new AttackAction(this, direction));
            for (WeaponItem weaponItem : otherActor.getWeaponInventory()) {
                actions.add(new AttackAction(this, direction, weaponItem));

                Action skillAction = weaponItem.getSkill(this, direction);
                if (skillAction != null) {
                    actions.add(skillAction);
                }
            }
        }
        return actions;
    }

    /**
     * Reset the chharacter's location to its starting location with full hp if not defeated.
     * @param map The map to reset.
     */
    @Override
    public void reset(GameMap map) {
        if (!isHasDied()) {
            this.heal(getMaxHp());
            map.removeActor(this);
            map.addActor(this, getStartingLocation());
        }
    }

    /**
     * Drops a remembrance of Grafted when died, and its location turns to a site of Lost Grace.
     * @param map: the map the actor is on.
     */
    @Override
    public void hasDied(GameMap map) {
        for (String line : FancyMessage.DEMIGOD_FELLED.split("\n")) {
            new Display().println(line);
            try {
                Thread.sleep(200);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        setHasDied(true);
        Location here = map.locationOf(this);
        map.removeActor(this);
        map.at(here.x(), here.y()).addItem(new RemembranceOfTheGrafted());
        here.setGround(new SiteOfLostGrace("Godrick the Grafted (Site of Lost Grace)", here));
    }

    /**
     * Returns the behaviour of the dog.
     * @param priority: The priority of the behaviour.
     * @return The behaviour of the dog.
     */
    public Behaviour getBehaviour(int priority){
        return this.behaviours.get(priority);
    }

    /**
     * Adds a behaviour to the dog.
     * @param priority: The priority of the behaviour.
     * @param behaviour: The behaviour to be added.
     */
    public void addBehaviour(int priority, Behaviour behaviour){
        this.behaviours.put(priority, behaviour);
    }

    /**
     * Returns the map of behaviours.
     * @return The map of behaviours.
     */
    public Map<Integer, Behaviour> getBehaviours() {
        return behaviours;
    }

    /**
     * Sets the map of behaviours.
     * @param behaviours: The map of behaviours.
     */
    public void setBehaviours(Map<Integer, Behaviour> behaviours) {
        this.behaviours = behaviours;
    }

    /**
     * Returns whether the boss has died.
     * @return Whether the boss has died.
     */
    public boolean isHasDied() {
        return hasDied;
    }

    /**
     * Sets whether the boss has died.
     * @param hasDied: Whether the boss has died.
     */
    public void setHasDied(boolean hasDied) {
        this.hasDied = hasDied;
    }

    /**
     * Returns the starting location of the boss.
     * @return The starting location of the boss.
     */
    public Location getStartingLocation() {
        return startingLocation;
    }

    /**
     * Sets the starting location of the boss.
     * @param startingLocation: The starting location of the boss.
     */
    public void setStartingLocation(Location startingLocation) {
        this.startingLocation = startingLocation;
    }

    /**
     * Returns whether the boss is at the second stage.
     * @return Whether the boss is at the second stage.
     */
    public boolean isSecondStage() {
        return secondStage;
    }

    /**
     * Sets whether the boss is at the second stage.
     * @param secondStage: Whether the boss is at the second stage.
     */
    public void setSecondStage(boolean secondStage) {
        this.secondStage = secondStage;
    }
}
