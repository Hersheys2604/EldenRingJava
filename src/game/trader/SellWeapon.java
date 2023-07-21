package game.trader;

import game.engine.actions.ActionList;
import game.engine.weapons.WeaponItem;

/**
 * Interface for selling weapons
 * @author Ziheng Liao, Harshath Muruganantham
 * @see MerchantKale
 */
public interface SellWeapon {

    /**
     * Add a sell weapon option that can be chosen by the user
     * @param weapon any allowable weapon to be bought from player.
     * @return the list of actions that can be chosen by the user
     */
    ActionList addSellingOption(WeaponItem weapon);
}
