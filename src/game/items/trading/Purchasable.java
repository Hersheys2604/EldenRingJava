package game.items.trading;

/**
 * Interface for purchasable weapons
 * @author Ziheng Liao
 * Modified by: Harshath Muruganantham
 * @see game.trader.MerchantKale
 */
public interface Purchasable {
    /**
     * Get the amount of money the weapon can be bought for
     * @return the amount of money the weapon can be bought for
     */
    int getBuyAmount();
    /**
     * Set the amount of money the weapon can be bought for
     * @param buyAmount the amount of money the weapon can be bought for
     */
    void setBuyAmount(int buyAmount);
}
