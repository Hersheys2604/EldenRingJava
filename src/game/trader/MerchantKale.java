package game.trader;

import game.engine.actions.Action;
import game.engine.actions.ActionList;
import game.engine.actions.DoNothingAction;
import game.engine.actors.Actor;
import game.engine.displays.Display;
import game.engine.items.Item;
import game.engine.positions.GameMap;
import game.engine.weapons.WeaponItem;
import game.actions.trading.PurchaseAction;
import game.actions.trading.SellAction;
import game.characters.Status;
import game.items.RemembranceOfTheGrafted;
import game.items.weapons.*;

import java.util.HashMap;
import java.util.Map;
/**
 * Merchant Kale is a trader that can sell and purchase weapons
 * Created by:
 * @author Ziheng Liao
 * Modified by: Harshath Muruganantham
 *
 */
public class MerchantKale extends Actor implements Purchase, SellWeapon, SellItem {

    /**
     * The inventory of the MerchantKale containing all weapons that can be bought from him
     */
    private Map<WeaponItem, Integer> weaponSellInventory = new HashMap<>();

    /**
     * The inventory of the MerchantKale containing all weapons that can be sold to him
     */
    private Map<WeaponItem, Integer> weaponPurchaseInventory = new HashMap<>();

    /**
     * The inventory of the MerchantKale containing all items that can be sold to him
     */
    private Map<Item, Integer> itemPurchaseInventory = new HashMap<>();

    /**
     * The instance of the MerchantKale
     */
    private static MerchantKale instance;


    /**
     * Constructor
     */
    private MerchantKale() {
        super("Merchant Kale", 'K', 99999999);   //infinite hit points, cannot be attacked.
        Club club = new Club();
        addWeaponToSellInventory(club, club.getBuyAmount());
        addWeaponToPurchaseInventory(club, club.getSellAmount());

        Uchigatana uchigatana = new Uchigatana();
        addWeaponToSellInventory(uchigatana, uchigatana.getBuyAmount());
        addWeaponToPurchaseInventory(uchigatana, uchigatana.getSellAmount());
        
        GreatKnife greatKnife = new GreatKnife();
        addWeaponToSellInventory(greatKnife, greatKnife.getBuyAmount());
        addWeaponToPurchaseInventory(greatKnife, greatKnife.getSellAmount());

        Scimitar scimitar = new Scimitar();
        addWeaponToSellInventory(scimitar, scimitar.getBuyAmount());
        addWeaponToPurchaseInventory(scimitar, scimitar.getSellAmount());

        addWeaponToPurchaseInventory(new Grossmesser(), (new Grossmesser()).getSellAmount());

        addItemToPurchaseInventory(new RemembranceOfTheGrafted(), (new RemembranceOfTheGrafted()).getSellAmount());

        AstrologerStaff astrologerStaff = new AstrologerStaff();
        addWeaponToSellInventory(astrologerStaff, astrologerStaff.getBuyAmount());
        addWeaponToPurchaseInventory(astrologerStaff, astrologerStaff.getSellAmount());

        HeavyCrossbow heavyCrossbow = new HeavyCrossbow();
        addWeaponToSellInventory(heavyCrossbow, heavyCrossbow.getBuyAmount());
        addWeaponToPurchaseInventory(heavyCrossbow, heavyCrossbow.getSellAmount());

        addWeaponToPurchaseInventory(new AxeOfGodrick(), (new AxeOfGodrick()).getSellAmount());
        addWeaponToPurchaseInventory(new GraftedDragon(), (new GraftedDragon()).getSellAmount());
    }

    /**
     * Get the instance of the MerchantKale
     * @return the instance of the MerchantKale
     */
    public static MerchantKale getInstance() {
        if (instance == null) {
            instance = new MerchantKale();
        }
        return instance;
    }


    /**
     * Every turn the trader will do nothing
     * @param actions list of possible Actions
     * @param lastAction previous Action, if it was a multiturn action
     * @param map the map where the current MerchantKale is
     * @param display display to user on menu io
     * @return DoNothingAction
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }

    /**
     * Check if the actor is a player and lets the player trade with MerchantKale
     * @param otherActor the actor to be checked
     * @param direction the direction of the actor
     * @param map the map where the actor is
     * @return true if the actor is a player, false otherwise
     */
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if (otherActor.hasCapability(Status.TRADING)) {
            // need the to give player choice to choose item to buy
            actions.add(addPurchasingOption());

//             need the to give player choice to choose item to sell
            for (WeaponItem weaponItem : otherActor.getWeaponInventory()) {
                actions.add(addSellingOption(weaponItem));
            }

            for (Item item : otherActor.getItemInventory()) {
                if(item.getDisplayChar() == 'O'){
                    actions.add(addSellingOptionItem(item));
                }
            }
        }
        return actions;
    }

    /**
     * Add a weapon to the sell inventory
     * @param weaponItem the weapon to be added
     * @param price the price of the weapon
     */
    public void addWeaponToSellInventory(WeaponItem weaponItem, int price) {
        weaponSellInventory.put(weaponItem, price);
    }

    /**
     * Add a weapon to the purchase inventory
     * @param weaponItem the weapon to be added
     * @param price the price of the weapon
     */
    public void addWeaponToPurchaseInventory(WeaponItem weaponItem, int price) {
        weaponPurchaseInventory.put(weaponItem, price);
    }

    /**
     * Returns the inventory for selling
     * @return the inventory for selling
     */
    public Map<WeaponItem, Integer> getWeaponSellInventory() {
        return weaponSellInventory;
    }

    /**
     * Set the inventory for selling
     * @param weaponSellInventory the inventory for selling
     */
    public void setWeaponSellInventory(Map<WeaponItem, Integer> weaponSellInventory) {
        this.weaponSellInventory = weaponSellInventory;
    }

    /**
     * Returns the inventory for purchasing
     * @return the inventory for purchasing
     */
    public Map<WeaponItem, Integer> getWeaponPurchaseInventory() {
        return weaponPurchaseInventory;
    }

    /**
     * Set the inventory for purchasing
     * @param weaponPurchaseInventory the inventory for purchasing
     */
    public void setWeaponPurchaseInventory(Map<WeaponItem, Integer> weaponPurchaseInventory) {
        this.weaponPurchaseInventory = weaponPurchaseInventory;
    }

    /**
     * Return the purchase inventory for item
     * @return Return the purchase inventory
     */
    public Map<Item, Integer> getItemPurchaseInventory() {
        return itemPurchaseInventory;
    }

    /**
     * Set the inventory for purchasing gor item
     * @param itemPurchaseInventory the inventory for purchasing
     */
    public void setItemPurchaseInventory(Map<Item, Integer> itemPurchaseInventory) {
        this.itemPurchaseInventory = itemPurchaseInventory;
    }

    /**
     * Add a item to the purchase inventory
     * @param item the item to be added
     * @param price the price of the item
     */
    public void addItemToPurchaseInventory(Item item, int price) {
        itemPurchaseInventory.put(item, price);
    }

    /**
     * Add purchasing option to the action list
     * @return the action list with purchasing option
     */
    @Override
    public ActionList addPurchasingOption() {
        ActionList actions = new ActionList();
        for (WeaponItem weaponItem : getWeaponSellInventory().keySet()) {
            int amount = getWeaponSellInventory().get(weaponItem);
            actions.add(new PurchaseAction(weaponItem, amount));
        }
        return actions;
    }

    /**
     * Add selling option to the action list for weapon
     * @param weapon the weapon to be sold
     * @return the action list with selling option
     */
    @Override
    public ActionList addSellingOption(WeaponItem weapon) {
        ActionList actions = new ActionList();
        for (WeaponItem weaponItem : getWeaponPurchaseInventory().keySet()) {
            if (weaponItem.getDisplayChar() == weapon.getDisplayChar()) {
                int amount = getWeaponPurchaseInventory().get(weaponItem);
                actions.add(new SellAction(weapon, amount));
            }
        }
        return actions;
    }

    /**
     * Add selling option to the action list for item
     * @param item the item to be sold
     * @return the action list with selling option
     */
    @Override
    public ActionList addSellingOptionItem(Item item) {
        ActionList actions = new ActionList();
        for (Item ourItem : getItemPurchaseInventory().keySet()) {
            if (ourItem.getDisplayChar() == item.getDisplayChar()) {
                int amount = getItemPurchaseInventory().get(ourItem);
                actions.add(new SellAction(item, amount));
            }
        }
        return actions;
    }
}
