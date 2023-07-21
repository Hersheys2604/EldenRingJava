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
 * HeavyCrossbow is a weapon that can be used by a Godrick soldier with 64 damage and 57% hit rate
 * @author Ziheng Liao
 * @see Sellable
 * @see Purchasable
 */
public class HeavyCrossbow extends WeaponItem implements Sellable, Purchasable {

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
    public HeavyCrossbow() {
        super("Heavy Crossbow", '}', 64, "shoots", 57);
        setBuyAmount(1500);
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
     * Tick will scan for actors attack. It will store the actors inside a list. This only works for players. Simulates passage of time for this item.
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
//        super.tick(currentLocation, actor);
        GameMap map = currentLocation.map();
        List<Actor> enemies = EnemyInSurrounding.findEnemiesInSurrounding(actor, map, 2);

        List<Action> allowableActions = new ArrayList<>();
        for (Action action : getAllowableActions()) {
            allowableActions.add(action);
        }
        for (Action action : allowableActions) {
            removeAction(action);
        }


        for (Actor enemy : enemies) {


            if (enemy.hasCapability(Status.HOSTILE_TO_ENEMY) && !(enemy.hasCapability(Status.ALLY))) {
                addAction(new AttackAction(enemy, "(" + map.locationOf(enemy).x() + "," + map.locationOf(enemy).y() + ")", this));
            }
        }
    }
}
