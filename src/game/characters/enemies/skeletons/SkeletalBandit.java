package game.characters.enemies.skeletons;

import game.engine.actions.Action;
import game.engine.actions.ActionList;
import game.engine.actions.DoNothingAction;
import game.engine.displays.Display;
import game.engine.positions.Exit;
import game.engine.positions.GameMap;
import game.engine.positions.Location;
import game.actions.combat.DespawnAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.Behaviour;
import game.behaviours.FollowBehaviour;
import game.behaviours.WanderBehaviour;
import game.characters.Status;
import game.characters.specialdeaths.SpecialDeath;
import game.characters.specialdeaths.SpecialDeathManager;
import game.items.rune.IRune;
import game.items.rune.RuneManager;
import game.items.weapons.Scimitar;
import game.reset.ResetManager;
import game.utils.RandomNumberGenerator;

/**
 * A class that represents a SkeletalBandit -> a type of enemy. Becomes Pile of Bones upon Death
 * @author Ho Seng, Harshath Muruganantham
 * @see Skeleton
 * @see IRune
 * @see SpecialDeath
 */
public class SkeletalBandit extends Skeleton implements IRune, SpecialDeath {
    
    /**
     * Constructor.
     */
    public SkeletalBandit() {
        super("Skeletal Bandit", 'b', 184);
        super.addWeaponToInventory(new Scimitar());
        addBehaviour(999, new WanderBehaviour());
        ResetManager.getInstance().registerResettable(this);
        SpecialDeathManager.getInstance().registerSpecialDeath(this);
        this.addCapability(Status.TURN_TO_SKELETON);
        this.addCapability(Status.HOSTILE_TO_ENEMY);
        RuneManager.getInstance().storeRuneAmount(this, RandomNumberGenerator.getRandomInt(35, 892));
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
        // Add Follow Behaviour
        Location here = map.locationOf(this);

        // Add Follow Behaviour
        if (getBehaviour(998) == null){
            for (Exit exit : here.getExits()) {
                Location destination = exit.getDestination();
                if (destination.containsAnActor()) {
                    if (!(destination.getActor().hasCapability(Status.CANNOT_ATTACK_SKELETONS))) {
                        addBehaviour(998, new AttackBehaviour(destination.getActor()));
                    }
                    if (destination.getActor().getDisplayChar() == '@') {
                        addBehaviour(997, new FollowBehaviour(destination.getActor()));
                    }
                }
            }
        }
        if (RandomNumberGenerator.getChance(10) && getBehaviour(997) == null){
            return new DespawnAction();
        }
        for (Behaviour behaviour : getBehaviours().values()) {
            Action action = behaviour.getAction(this, map);
            if(action != null)   {   // retrieves an action from hashmap and returns it, else returns a DoNothingAction
                if (getBehaviours().containsKey(998)) {this.getBehaviours().remove(998);}
                return action;}
        }
        return new DoNothingAction();
    }

    /**
     * Returns the amount of runes the actor is worth.
     *
     * @return the amount of runes the actor is worth.
     */
    @Override
    public int getRuneAmount() {
        return RuneManager.getInstance().getRuneAmount(this);
    }

    /**
     * Becomes pile of bones when killed. A special type of death action
     * @param map: the map the actor is on.
     */
    @Override
    public void hasDied(GameMap map) {
        RuneManager runeManager = RuneManager.getInstance();
        int runeAmount = runeManager.getRuneAmount(this);
        Skeleton skeleton = this;
        Location location = map.locationOf(this);
        map.removeActor(this);
        location.addActor(new PileOfBones(skeleton, runeAmount));
    }
}
