package game.characters.enemies.skeletons;

import game.engine.actions.Action;
import game.engine.actions.ActionList;
import game.engine.actions.DoNothingAction;
import game.engine.actors.Actor;
import game.engine.displays.Display;
import game.engine.items.Item;
import game.engine.positions.GameMap;
import game.engine.positions.Location;
import game.engine.weapons.WeaponItem;
import game.actions.combat.AttackAction;
import game.characters.Status;
import game.characters.specialdeaths.SpecialDeath;
import game.characters.specialdeaths.SpecialDeathManager;
import game.items.rune.IRune;
import game.items.rune.RuneManager;

/**
 * A class that represents a PileOfBones -> a type of enemy.
 * @author Harshath Muruganantham
 * @see Skeleton
 * @see IRune
 * @see SpecialDeath
 */
public class PileOfBones extends Actor implements IRune, SpecialDeath {

    /**
     * Amount of turns passed after pile of bones has spawned.
     */
    private int turns = 0;

    /**
     * The skeleton that the pile of bones will turn into.
     */
    private Skeleton skeleton;



    /**
     * Constructor.
     * @param skeleton: The skeleton that the pile of bones will turn into.
     * @param runeAmount: The amount of runes the pile of bones will drop.
     */
    public PileOfBones(Skeleton skeleton, int runeAmount) {
        super("Pile of Bones", 'X', 1);
        this.skeleton = skeleton;
        RuneManager.getInstance().storeRuneAmount(this, runeAmount);
        SpecialDeathManager.getInstance().registerSpecialDeath(this);

    }

    /**
     * Respawn the skeleton teh pile of bones will turn into if not killed within 3 rounds.
     * @return the skeleton that the pile of bones will turn into.
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (this.getTurns() == 3){
            Location location = map.locationOf(this);
            map.removeActor(this);
            this.getSkeleton().heal(100000);
            System.out.println("\n Skeleton is back to life!");
            location.addActor(this.getSkeleton());
        }
        this.setTurns(this.getTurns() + 1);
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
        if (otherActor.hasCapability(Status.KILL_PILE_OF_BONES)){
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
        }
        return actions;
    }

    /**
     * Returns the amount of turns passed after pile of bones has spawned.
     * @return the amount of turns passed after pile of bones has spawned.
     */
    public int getTurns() {
        return turns;
    }

    /**
     * Set the amount of turns passed after pile of bones has spawned.
     * @param turns the amount of turns passed after pile of bones has spawned.
     */
    public void setTurns(int turns) {
        this.turns = turns;
    }

    /**
     * Returns the skeleton that the pile of bones will turn into.
     * @return the skeleton that the pile of bones will turn into.
     */
    public Skeleton getSkeleton() {
        return skeleton;
    }

    /**
     * Set the skeleton that the pile of bones will turn into.
     * @param skeleton the skeleton that the pile of bones will turn into.
     */
    public void setSkeleton(Skeleton skeleton) {
        this.skeleton = skeleton;
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

    /**
     * Special Death for Pile of Bones. Actor despawns after dropping everything owned by the skeleton.
     *
     * @param map: the map the actor is on.
     */
    @Override
    public void hasDied(GameMap map) {
        ActionList dropActions = new ActionList();
        for (Item item : getSkeleton().getItemInventory())
            this.addItemToInventory(item);
        for (WeaponItem weapon : getSkeleton().getWeaponInventory())
            this.addWeaponToInventory(weapon);
        for (Item item : this.getItemInventory())
            dropActions.add(item.getDropAction(this));
        for (WeaponItem weapon : this.getWeaponInventory())
            dropActions.add(weapon.getDropAction(this));
        for (Action drop : dropActions)
            drop.execute(this, map);

        map.removeActor(this);
    }
}
