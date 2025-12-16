package model;

import java.io.Serializable;

public abstract class Item implements Serializable {
    private static final long serialVersionUID = 1L;
    protected String itemName;
    protected double weight;
    protected ItemRarity itemRarity;

    public Item(String name, double weight, ItemRarity ItemRarity) {
        this.itemName = name;
        this.weight = weight;
        this.itemRarity = ItemRarity;
    }

    public Item(String name, double weight) {
        this(name, weight, ItemRarity.COMMON);
    }

    public String getItemName() { return itemName; }
    public double getWeight() { return weight; }
    public ItemRarity getItemRarity() { return itemRarity; }
    public abstract String getType();
}