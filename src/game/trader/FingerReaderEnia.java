package game.trader;

import game.engine.actions.Action;
import game.engine.actions.ActionList;
import game.engine.actions.DoNothingAction;
import game.engine.actors.Actor;
import game.engine.displays.Display;
import game.engine.items.Item;
import game.engine.positions.GameMap;
import game.engine.weapons.WeaponItem;
import game.actions.trading.ExchangeAction;
import game.actions.trading.SellAction;
import game.characters.Status;
import game.items.RemembranceOfTheGrafted;
import game.items.weapons.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * FingerReaderEnia is a trader that can purchase weapons/items and exchange certain items for weapons.
 * @author Harshath Muruganantham
 *
 */
public class FingerReaderEnia extends Actor implements SellWeapon, Exchange, SellItem{
    

    /**
     * The inventory of the FingerReaderEnia containing all weapons that can be sold to him
     */
    private Map<WeaponItem, Integer> weaponPurchaseInventory = new HashMap<>();

    /**
     * The inventory of the FingerReaderEnia containing all items that can be sold to him
     */
    private Map<Item, Integer> itemPurchaseInventory = new HashMap<>();

    /**
     * The inventory of the FingerReaderEnia containing all items that can be exchanged
     */
    private Map<Item, List<WeaponItem>> itemExchangeInventory = new HashMap<>();
    
    


    /**
     * Constructor.
     */
    public FingerReaderEnia() {
        super("Finger Reader Enia", 'E', 99999999);
        Club club = new Club();
        addWeaponToPurchaseInventory(club, club.getSellAmount());

        Uchigatana uchigatana = new Uchigatana();
        addWeaponToPurchaseInventory(uchigatana, uchigatana.getSellAmount());

        GreatKnife greatKnife = new GreatKnife();
        addWeaponToPurchaseInventory(greatKnife, greatKnife.getSellAmount());

        Scimitar scimitar = new Scimitar();
        addWeaponToPurchaseInventory(scimitar, scimitar.getSellAmount());

        addWeaponToPurchaseInventory(new Grossmesser(), (new Grossmesser()).getSellAmount());

        addItemToPurchaseInventory(new RemembranceOfTheGrafted(), (new RemembranceOfTheGrafted()).getSellAmount());

        addItemToExchangeInventory(new RemembranceOfTheGrafted(), (new RemembranceOfTheGrafted().getExchangeWeapons()));

        AstrologerStaff astrologerStaff = new AstrologerStaff();
        addWeaponToPurchaseInventory(astrologerStaff, astrologerStaff.getSellAmount());

        HeavyCrossbow heavyCrossbow = new HeavyCrossbow();
        addWeaponToPurchaseInventory(heavyCrossbow, heavyCrossbow.getSellAmount());

        addWeaponToPurchaseInventory(new AxeOfGodrick(), (new AxeOfGodrick()).getSellAmount());
        addWeaponToPurchaseInventory(new GraftedDragon(), (new GraftedDragon()).getSellAmount());
    }


    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }

    /**
     * Check if the actor is a player and lets the player trade with FingerReaderEnia
     * @param otherActor the actor to be checked
     * @param direction the direction of the actor
     * @param map the map where the actor is
     * @return true if the actor is a player, false otherwise
     */
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if (otherActor.hasCapability(Status.TRADING)) {
            // need the to give player choice to trade Remembrance of the Grafted
            for (Item item : otherActor.getItemInventory()) {
                if(item.getDisplayChar() == 'O'){
                    actions.add(addSellingOptionItem(item));
                    actions.add(addExchangingAction(item));
                }
            }
            
//             need the to give player choice to choose item to sell
            for (WeaponItem weaponItem : otherActor.getWeaponInventory()) {
                actions.add(addSellingOption(weaponItem));
            }
        }
        return actions;
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
     * Add a weapon to the purchase inventory
     * @param item the weapon to be added
     * @param price the price of the weapon
     */
    public void addItemToPurchaseInventory(Item item, int price) {
        itemPurchaseInventory.put(item, price);
    }

    /**
     * Return the Exchange inventory
     * @return Return the Exchange inventory
     */
    public Map<Item, List<WeaponItem>> getItemExchangeInventory() {
        return itemExchangeInventory;
    }

    /**
     * Set the inventory for exchanging
     * @param itemExchangeInventory the inventory for exchanging
     */
    public void setItemExchangeInventory(Map<Item, List<WeaponItem>> itemExchangeInventory) {
        this.itemExchangeInventory = itemExchangeInventory;
    }

    /**
     * Add a weapon to the exchanging inventory
     * @param item the weapon to be added
     * @param weapons list of weapons to be returned for exchanging
     */
    public void addItemToExchangeInventory(Item item, List<WeaponItem> weapons) {
        itemExchangeInventory.put(item, weapons);
    }

    /**
     * Add exchanging option to the action list
     * @param item the item to be exchanged
     * @return the action list with selling option
     */
    @Override
    public ActionList addExchangingAction(Item item) {
        ActionList actions = new ActionList();
        for (Item ourItem : getItemExchangeInventory().keySet()) {
            if (ourItem.getDisplayChar() == item.getDisplayChar()) {
                for (WeaponItem weapon: getItemExchangeInventory().get(ourItem)) {
                    actions.add(new ExchangeAction(item, weapon));
                }
            }
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
