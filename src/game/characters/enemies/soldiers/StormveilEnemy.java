package game.characters.enemies.soldiers;

import game.engine.actions.Action;
import game.engine.actions.ActionList;
import game.engine.actors.Actor;
import game.engine.positions.GameMap;
import game.engine.weapons.WeaponItem;
import game.actions.combat.AttackAction;
import game.actions.combat.DespawnAction;
import game.behaviours.Behaviour;
import game.characters.Status;
import game.reset.Resettable;
import game.reset.SpecialResettables;

import java.util.HashMap;
import java.util.Map;

/**
 * Abstract class for all Stormveil characters.
 * @author Ziheng Liao
 * @see Actor
 * @see Resettable
 * @see SpecialResettables
 * @see Dog
 * @see GodrickSoldier
 */
public abstract class StormveilEnemy extends Actor implements Resettable {

    /**
     * Map of behaviours, with priority as key.
     */
    private Map<Integer, Behaviour> behaviours = new HashMap<>();

    /**
     * Constructor.
     * @param name: Name of the dog.
     * @param displayChar: Display character of the dog.
     * @param hitPoints: Hit points of the dog.
     */
    public StormveilEnemy(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.addCapability(Status.CANNOT_ATTACK_STORMVEIL);
    }

    /**
     * Returns a list of allowable actions an actor can do to this class.
     * @param otherActor: The actor that is attacking this.
     * @param direction: The direction of the attack.
     * @param map: The map that the dog is on.
     * @return A list of allowable actions for the dog.
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            if (!(otherActor.hasCapability(Status.CANNOT_ATTACK_STORMVEIL))) {
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
     * Returns the behaviour of the Enemy.
     * @param priority: The priority of the behaviour.
     * @return The behaviour of the Enemy.
     */
    public Behaviour getBehaviour(int priority){
        return this.behaviours.get(priority);
    }

    /**
     * Adds a behaviour to the Enemy.
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
     * Resets the Enemy.
     * @param map: The map that the Enemy is on.
     */
    @Override
    public void reset(GameMap map) {
        DespawnAction despawnAction = new DespawnAction();
        despawnAction.execute(this, map);
    }
}


