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
import game.actions.skills.SlamAllAction;
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
 * The enemy class of Giant Dog.
 * @author Ho Seng, Harshath Muruganantham
 * @see LimgraveDog
 * @see IRune
 */
public class GiantDog extends LimgraveDog implements IRune{

    /**
     * Constructor for a Giant Dog
     */
    public GiantDog() {
        super("Giant Dog", 'G', 693);
        addBehaviour(999, new WanderBehaviour());
        ResetManager.getInstance().registerResettable(this);
        this.addCapability(Status.HOSTILE_TO_ENEMY);
        RuneManager.getInstance().storeRuneAmount(this, RandomNumberGenerator.getRandomInt(313, 1808));
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
                    if (!(destination.getActor().hasCapability(Status.CANNOT_ATTACK_DOGS))){
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
                if (getBehaviours().containsKey(998)) {
                    getBehaviours().remove(998);
                    if (RandomNumberGenerator.getChance(50)) {  // i didnt do this inside the slam method because i would have to return null and do an if statement here anyways
                        Action slamAction = new SlamAllAction();      // and its better to do it here because inside this conditional, it is an intrinsic attack
                        return slamAction;
                    }
                }
                return action;
            }
        }
        return new DoNothingAction();
    }

    /**
     * Get the intrinsic weapon used by GiantDof
     * @return Intrinsic Weapon used by GiantDog
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(314, "slams", 90);
    }

    /**
     * Returns the amount of runes this character will drop when killed by the player.
     * @return the amount of runes this character will drop when killed by the player
     */
    @Override
    public int getRuneAmount() {
        return RuneManager.getInstance().getRuneAmount(this);
    }
}
