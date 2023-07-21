package game.items.weapons;

import game.engine.actions.Action;
import game.engine.actors.Actor;
import game.engine.positions.GameMap;
import game.engine.positions.Location;
import game.engine.weapons.WeaponItem;
import game.actions.combat.AttackAction;
import game.characters.Status;
import game.items.trading.Purchasable;
import game.items.trading.Sellable;
import game.trader.TradingStatus;
import game.utils.EnemyInSurrounding;

import java.util.ArrayList;
import java.util.List;

/**
 * AstrologerStaff is a weapon that can be used by an Astrologer Archetype with
 * 274 damage and 50% hit rate
 * @author Ho Seng
 * Modified by: Ziheng Liao, Harshath Muruganantham
 * @see Sellable
 * @see Purchasable
 */
public class AstrologerStaff extends WeaponItem implements Sellable, Purchasable {

    /**
     * The buy amount of the weapon
     */
    private int buyAmount;

    /**
     * The sell amount of the weapon
     */
    private int sellAmount;

    /**
     * Constructor.
     */
    public AstrologerStaff() {
        super("Astrologer's Staff", 'f', 274, "shoots", 50);
        setBuyAmount(800);
        setSellAmount(100);
        this.addCapability(TradingStatus.SELLABLE);
        this.addCapability(TradingStatus.PURCHASABLE);
        this.addCapability(Status.RANGE_ATTACK);
    }

    /**
     * Get the buy amount of the weapon
     *
     * @return the buy amount of the weapon
     */
    @Override
    public int getBuyAmount() {
        return buyAmount;
    }

    /**
     * Set the buy amount of the weapon
     *
     * @param buyAmount the buy amount of the weapon
     */
    @Override
    public void setBuyAmount(int buyAmount) {
        this.buyAmount = buyAmount;
    }

    /**
     * Get the sell amount of the weapon
     *
     * @return the sell amount of the weapon
     */
    @Override
    public int getSellAmount() {
        return sellAmount;
    }

    /**
     * Set the sell amount of the weapon
     *
     * @param sellAmount the sell amount of the weapon
     */
    @Override
    public void setSellAmount(int sellAmount) {
        this.sellAmount = sellAmount;
    }


    /**
     * Tick will scan for actors attack (simulates passage of time for this weapon)
     * it will store the actors inside a list
     * @param currentLocation the current location of the actor
     * @param actor the actor that is holding the weapon
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
//        super.tick(currentLocation, actor);
        GameMap map = currentLocation.map();
        List<Actor> enemies = EnemyInSurrounding.findEnemiesInSurrounding(actor, map, 3);

        List<Action> allowableActions = new ArrayList<>();

        for (Action action : getAllowableActions()) {
            allowableActions.add(action);
        }
        for (Action action : allowableActions) {
            removeAction(action);
        }

        for (Actor enemy : enemies) {
            if (enemy.hasCapability(Status.HOSTILE_TO_ENEMY) && !(enemy.hasCapability(Status.ALLY))){
                addAction(new AttackAction(enemy, "(" + map.locationOf(enemy).x() + "," + map.locationOf(enemy).y() + ")", this));
            }
        }
    }


}
