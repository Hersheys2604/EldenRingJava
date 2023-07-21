package game.items;

import game.engine.items.Item;
import game.engine.weapons.WeaponItem;
import game.items.trading.Exchangeable;
import game.items.trading.Sellable;
import game.items.weapons.AxeOfGodrick;
import game.items.weapons.GraftedDragon;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that represents a Remembrance of the Grafted -> a type of item.
 * @author Harshath Murugannatham
 * @see Item
 */
public class RemembranceOfTheGrafted extends Item implements Sellable, Exchangeable {

    /***
     * The amount of money the player gets when they sell this item.
     */
    private int sellAmount;

    /***
     * Constructor.
     */
    public RemembranceOfTheGrafted() {
        super("Remembrance of the Grafted", 'O', true);
        setSellAmount(20000);
    }

    /**
     * Returns the amount of money the player gets when they sell this item.
     * @return The amount of money the player gets when they sell this item.
     */
    @Override
    public int getSellAmount() {
        return sellAmount;
    }

    /**
     * Sets the amount of money the player gets when they sell this item.
     * @param sellAmount The amount of money the player gets when they sell this item.
     */
    @Override
    public void setSellAmount(int sellAmount) {
        this.sellAmount = sellAmount;
    }

    /**
     * Returns the weapons that the player can exchange for this item.
     * @return weapons
     */
    @Override
    public List<WeaponItem> getExchangeWeapons() {
        List<WeaponItem> weapons = new ArrayList<>();
        weapons.add(new GraftedDragon());
        weapons.add(new AxeOfGodrick());
        return weapons;
    }
}
