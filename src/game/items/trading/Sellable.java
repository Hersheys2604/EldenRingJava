package game.items.trading;

/**
 * Interface for sellable weapons
 * @author Ziheng liao
 * Modified by: Harshath Muruganantham
 * @see game.trader.MerchantKale
 */
public interface Sellable {

    /**
     * Get the amount of money the weapon can be sold for
     * @return the amount of money the weapon can be sold for
     */
    int getSellAmount();

    /**
     * Set the amount of money the weapon can be sold for
     * @param sellAmount the amount of money the weapon can be sold for
     */
    void setSellAmount(int sellAmount);
}
