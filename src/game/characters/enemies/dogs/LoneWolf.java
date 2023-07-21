package game.characters.enemies.dogs;

import game.engine.actions.Action;
import game.engine.actions.ActionList;
import game.engine.actions.DoNothingAction;
import game.engine.displays.Display;
import game.engine.positions.Exit;
import game.engine.positions.GameMap;
import game.engine.positions.Location;
import game.engine.weapons.IntrinsicWeapon;
import game.actions.combat.DespawnAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.Behaviour;
import game.behaviours.FollowBehaviour;
import game.behaviours.WanderBehaviour;
import game.characters.Status;
import game.items.rune.IRune;
import game.items.rune.RuneManager;
import game.reset.ResetManager;
import game.utils.RandomNumberGenerator;

/**
 * BEHOLD, DOG!
 *
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Harshath Murugannatham
 * @see LimgraveDog
 * @see IRune
 */
public class LoneWolf extends LimgraveDog implements IRune{

    /**
     * Constructor.
     */
    public LoneWolf() {
        super("Lone Wolf", 'h', 102);
        addBehaviour(999, new WanderBehaviour());
        ResetManager.getInstance().registerResettable(this);
        this.addCapability(Status.HOSTILE_TO_ENEMY);
        RuneManager.getInstance().storeRuneAmount(this, RandomNumberGenerator.getRandomInt(55, 1470));


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
                    if (!(destination.getActor().hasCapability(Status.CANNOT_ATTACK_DOGS))) {
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
            if(action != null){
                if (getBehaviours().containsKey(998)) {getBehaviours().remove(998);}
                return action;}
        }
        return new DoNothingAction();
    }

    /**
     * Returns the intrinsic weapon of the actor.
     *
     * @return the intrinsic weapon of the actor.
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(97, "bites", 95);
    }

    /**
     * Returns the amount of runes the actor drops when killed by player.
     *
     * @return the amount of runes the actor drops when killed by player.
     */
    @Override
    public int getRuneAmount() {
        return RuneManager.getInstance().getRuneAmount(this);
    }
}
