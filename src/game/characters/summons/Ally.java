package game.characters.summons;

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
import game.actions.combat.DespawnAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.Behaviour;
import game.behaviours.WanderBehaviour;
import game.characters.Status;
import game.characters.players.archetype.Archetype;
import game.reset.ResetManager;
import game.reset.SpecialResettables;
import game.utils.EnemyInSurrounding;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * An Ally Class (Spawned from summon Sign) that can attack enemies.
 * @author Ziheng Liao
 * Modified By: Harshath Muruganantham
 * @see Actor
 * @see SpecialResettables
 */
public class Ally extends Actor implements SpecialResettables {

    /**
     * Map of behaviours, with priority as key.
     */
    private Map<Integer, Behaviour> behaviours = new HashMap<>();

    /**
     * Constructor.
     * @param archetype: The archetype of the player.
     */
    public Ally(Archetype archetype) {
        super("Ally", 'A', archetype.getHitPoints());
        this.addWeaponToInventory(archetype.getStartingWeapon());
        this.addCapability(Status.ALLY);
        this.addCapability(Status.HOSTILE_TO_ENEMY);
        ResetManager.getInstance().registerSpecialResettable(this);
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


        Location here = map.locationOf(this);

        if (getBehaviour(998) == null){
            for (Exit exit : here.getExits()) {
                Location destination = exit.getDestination();
                if (destination.containsAnActor()) {
                    if (!(destination.getActor().hasCapability(Status.ALLY))) {
                        addBehaviour(998, new AttackBehaviour(destination.getActor()));
                    }
                }
            }
            if (getWeaponInventory().get(0).hasCapability(Status.RANGE_ATTACK)) {
                List<Actor> enemies = EnemyInSurrounding.findEnemiesInSurrounding(this, map, 3);
                for (Actor enemy : enemies) {
                    if (!(enemy.hasCapability(Status.ALLY))) {
                        addBehaviour(998, new AttackBehaviour(enemy));
                    }
                }
            }
        }


        for (Behaviour behaviour : getBehaviours().values()) {
            Action action = behaviour.getAction(this, map);
            if(action != null){
                if (getBehaviours().containsKey(998)) {
                    getBehaviours().remove(998);
                }
                return action;}
        }
        return new DoNothingAction();
    }

    /**
     * Returns the actions that the ally can perform.
     * @param otherActor: The actor that the ally is interacting with.
     * @param direction: The direction of the other actor.
     * @param map: The map that the ally is in.
     * @return The actions that the ally can perform.
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            if (!(otherActor.hasCapability(Status.ALLY))) {
                if (otherActor.getWeaponInventory().size() == 0) {
                    actions.add(new AttackAction(this, direction));
                } else if ((otherActor.getDisplayChar() == '@')) {
                    actions.add(new AttackAction(this, direction));
                    for (WeaponItem weaponItem : otherActor.getWeaponInventory()) {
                        actions.add(new AttackAction(this, direction, weaponItem));
                        Action skillAction = weaponItem.getSkill(this, direction);
                        if (skillAction != null) {
                            actions.add(skillAction);
                        }
                    }
                } else {
                    actions.add(new AttackAction(this, direction, otherActor.getWeaponInventory().get(0)));
                }
            }
        }
        return actions;
    }

    /**
     * Returns the behaviour of the ally based on priority.
     * @param priority: The priority of the behaviour.
     * @return The behaviour of the ally.
     */
    public Behaviour getBehaviour(int priority){
        return this.behaviours.get(priority);
    }

    /**
     * Adds a behaviour to the ally.
     * @param priority: The priority of the behaviour.
     * @param behaviour: The behaviour to be added.
     */
    public void addBehaviour(int priority, Behaviour behaviour){
        this.behaviours.put(priority, behaviour);
    }

    /**
     * Returns the behaviours of the ally
     * @return The behaviours of the ally.
     */
    public Map<Integer, Behaviour> getBehaviours() {
        return behaviours;
    }

    /**
     * Sets the behaviours of the ally.
     * @param behaviours: The behaviours to be set.
     */
    public void setBehaviours(Map<Integer, Behaviour> behaviours) {
        this.behaviours = behaviours;
    }

    /**
     * Resets the ally.
     * @param map: The map that the ally is in.
     */
    @Override
    public void specialReset(GameMap map) {
        DespawnAction despawnAction = new DespawnAction();
        despawnAction.execute(this, map);
    }
}

