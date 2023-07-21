package game.characters.enemies.skeletons;

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

import java.util.HashMap;
import java.util.Map;

/**
 * Abstract class for all skeletons characters.
 * @author Ho Seng, Harshath Muruganantham
 * @see Actor
 * @see Resettable
 * @see HeavySkeletalSwordsman
 * @see SkeletalBandit
 * @see PileOfBones
 */
public abstract class Skeleton extends Actor implements Resettable {
    private Map<Integer, Behaviour> behaviours = new HashMap<>();

    /**
     * Constructor.
     * @param name: Name of the skeleton.
     * @param displayChar: Display character of the skeleton.
     * @param hitPoints: Hit points of the skeleton.
     */
    public Skeleton(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.addCapability(Status.CANNOT_ATTACK_SKELETONS);
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
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            if (!(otherActor.hasCapability(Status.CANNOT_ATTACK_SKELETONS))) {
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
     * Returns the behaviour of the skeleton based on priority.
     * @param priority: The priority of the behaviour.
     * @return The behaviour of the skeleton.
     */
    public Behaviour getBehaviour(int priority){
        return this.behaviours.get(priority);
    }

    /**
     * Adds a behaviour to the skeleton.
     * @param priority: The priority of the behaviour.
     * @param behaviour: The behaviour to be added.
     */
    public void addBehaviour(int priority, Behaviour behaviour){
        this.behaviours.put(priority, behaviour);
    }

    /**
     * Returns the behaviours of the skeleton
     * @return The behaviours of the skeleton.
     */
    public Map<Integer, Behaviour> getBehaviours() {
        return behaviours;
    }

    /**
     * Sets the behaviours of the skeleton.
     * @param behaviours: The behaviours to be set.
     */
    public void setBehaviours(Map<Integer, Behaviour> behaviours) {
        this.behaviours = behaviours;
    }

    /**
     * Resets the skeleton.
     * @param map: The map that the skeleton is on.
     */
    @Override
    public void reset(GameMap map) {
        DespawnAction despawnAction = new DespawnAction();
        despawnAction.execute(this, map);
    }
}


