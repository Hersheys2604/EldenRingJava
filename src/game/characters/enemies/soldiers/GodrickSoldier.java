package game.characters.enemies.soldiers;

import game.engine.actions.Action;
import game.engine.actions.ActionList;
import game.engine.actions.DoNothingAction;
import game.engine.actors.Actor;
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
import game.items.weapons.HeavyCrossbow;
import game.reset.ResetManager;
import game.utils.EnemyInSurrounding;
import game.utils.RandomNumberGenerator;

import java.util.List;

/**
 * A Godrick Soldier (an enemy character).
 * @author Ziheng Liao
 * @see StormveilEnemy
 * @see IRune
 */
public class GodrickSoldier extends StormveilEnemy implements IRune{

    /**
     * Constructor.
     */
    public GodrickSoldier() {
        super("Godrick Soldier", 'p', 198);
        super.addWeaponToInventory(new HeavyCrossbow());
        addBehaviour(999, new WanderBehaviour());
        ResetManager.getInstance().registerResettable(this);
        this.addCapability(Status.HOSTILE_TO_ENEMY);
        RuneManager.getInstance().storeRuneAmount(this, RandomNumberGenerator.getRandomInt(38, 70));
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
                    if (!(destination.getActor().hasCapability(Status.CANNOT_ATTACK_STORMVEIL))) {
                        addBehaviour(998, new AttackBehaviour(destination.getActor()));
                    }
                    if (destination.getActor().getDisplayChar() == '@') {
                        addBehaviour(997, new FollowBehaviour(destination.getActor()));
                    }
                }
            }

            if (getWeaponInventory().get(0).hasCapability(Status.RANGE_ATTACK)) {
                List<Actor> enemies = EnemyInSurrounding.findEnemiesInSurrounding(this, map, 2);
                for (Actor enemy : enemies) {
                    if (!(enemy.hasCapability(Status.CANNOT_ATTACK_STORMVEIL))) {   // if they are able to attack the player, they should be able to follow the player
                        addBehaviour(998, new AttackBehaviour(enemy));
                    }
//                    if (enemy.getDisplayChar() == '@') {
//                        addBehaviour(997, new FollowBehaviour(enemy));
//                    }
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
