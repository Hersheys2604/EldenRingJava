package game.trader;

import game.engine.actions.ActionList;

/**
 * Interface for purchasing weapons
 * @author Ziheng Liao, Harshath Muruganantham
 * @see MerchantKale
 */
public interface Purchase {

    /**
     * Add a purchase weapon option taht can be chosen by the user
     * @return the list of actions that can be chosen by the user
     */
    ActionList addPurchasingOption();
}
