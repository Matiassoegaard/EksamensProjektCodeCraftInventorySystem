package logik;

import exception.InvalidItemException;
import exception.InventoryFullException;
import exception.WeightLimitException;
import model.*;

import java.util.ArrayList;
import java.util.List;

// Inventory.java
public class Inventory {
    private List<Item> items;
    private Player player;
    private static final double MAX_WEIGHT = 50.0;

    public Inventory(Player player) {
        this.items = new ArrayList<>();
        this.player = player;
    }

    // method where the default rarity is set to COMMON
    public void addWeapon(WeaponType type) throws InventoryFullException, WeightLimitException {
        Weapon weapon = new Weapon(type);
        addItem(weapon);
    }
    // overloaded version where rarity is chosen
    public void addWeapon(WeaponType type, ItemRarity rarity) throws InventoryFullException, WeightLimitException {
        Weapon weapon = new Weapon(type, rarity);
        addItem(weapon);
    }

    // method where the default rarity is set to COMMON
    public void addArmor(ArmorType type) throws InventoryFullException, WeightLimitException {
        Armor armor = new Armor(type);
        addItem(armor);
    }

    // overloaded version where rarity is chosen
    public void addArmor(ArmorType type, ItemRarity rarity) throws InventoryFullException, WeightLimitException {
        Armor armor = new Armor(type, rarity);
        addItem(armor);
    }

    // method where the default rarity is set to COMMON
    public void addConsumable(ConsumableType type, int quantity)
            throws InventoryFullException, WeightLimitException {
        Consumable consumable = new Consumable(type, quantity);
        addItem(consumable);
    }

    // overloaded version where rarity is chosen
    public void addConsumable(ConsumableType type, int quantity, ItemRarity rarity)
            throws InventoryFullException, WeightLimitException {
        Consumable consumable = new Consumable(type, quantity, rarity);
        addItem(consumable);
    }

    public void addItem(Item item) throws InventoryFullException, WeightLimitException {
        // Check if consumable already exists in case of stacking
        if (item instanceof Consumable) {
            Consumable newCons = (Consumable) item;
            for (Item existing : items) {
                if (existing instanceof Consumable) {
                    Consumable existingCons = (Consumable) existing;
                    if (existingCons.getConsumableType() == newCons.getConsumableType()) {
                        // Check weight before stacking
                        if (getCurrentWeight() + item.getWeight() > MAX_WEIGHT) {
                            throw new WeightLimitException("Cant add this many!, they're to heavy!");
                        }
                        existingCons.addQuantity(newCons.getQuantity());
                        return;
                    }
                }
            }
        }

        // Check slot limit
        if (getUsedSlots() >= player.getMaxInventorySlots()) {
            throw new InventoryFullException("Inventory is full!");
        }

        // Check weight limit
        if (getCurrentWeight() + item.getWeight() > MAX_WEIGHT) {
            throw new WeightLimitException("Item is to Heavy!");
        }

        items.add(item);
    }

    public void removeItem(int index) throws InvalidItemException {
        if (index < 0 || index >= items.size()) {
            throw new InvalidItemException("Invalid item index: " + index);
        }
        Item item = items.get(index);
        if (item instanceof Consumable) {
            Consumable cons = (Consumable) item;
            if (cons.getQuantity() > 1) {
                cons.removeQuantity(1);
                return;
            }
        }
        items.remove(index);
    }

    // bubble-sort where it sorts items by name
    public void sortByName(List<Item> items) {
        for(int i = 0; i < items.size()- 1; i++) {
            for( int j = 0; j < items.size() - i - 1; j++) {
                if(items.get(j).getItemName().compareTo(items.get(j + 1).getItemName()) > 0){
                    Item temp = items.get(j);
                    items.set(j, items.get(j + 1));
                    items.set(j + 1, temp);
                }
            }
        }
    }

    // bubble-sort where it sorts items by weight
    public void sortByWeight(List<Item> items) {
        for (int i = 0; i < items.size() - 1; i++) {
            for (int j = 0; j < items.size() - i - 1; j++) {
                if (items.get(j).getWeight() < items.get(j + 1).getWeight()) {
                    Item temp = items.get(j);
                    items.set(j, items.get(j + 1));
                    items.set(j + 1, temp);
                }
            }
        }
    }

    // bubble-sort where it sorts items by type
    public void sortByType(List<Item> items) {
        for (int i = 0; i < items.size() - 1; i++) {
            for (int j = 0; j < items.size() - i - 1; j++) {
                if (items.get(j).getType().compareTo(items.get(j + 1).getType()) > 0) {
                    Item temp = items.get(j);
                    items.set(j, items.get(j + 1));
                    items.set(j + 1, temp);
                }
            }
        }
    }

    public int getUsedSlots() {
        return items.size();
    }

    public double getCurrentWeight() {
        double total = 0;
        for (Item item : items) {
            total += item.getWeight();
        }
        return total;
    }

    // checks if inventory is empty
    public boolean isEmpty() {
        return items.isEmpty();
    }

    // removes all items from inventory
    public void clear() {
        items.clear();
    }

    // returns a list of current inventory items
    public List<Item> getItems() {
        return items;
    }


    // return player
    public Player getPlayer() {
        return player;
    }

    // returns max weight
    public double getMaxWeight() {
        return MAX_WEIGHT;
    }
}


