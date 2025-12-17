package model;

public class Consumable extends Item {
    private static final long serialVersionUID = 1L;
    private ConsumableType consumableType;
    private int quantity;
    private static final double DEFAULT_WEIGHT_PER_UNIT = 0.2;

    public Consumable(ConsumableType consumableType, int quantity) {
        super(consumableType.toString(), DEFAULT_WEIGHT_PER_UNIT);
        this.consumableType = consumableType;
        this.quantity = quantity;
    }

    public Consumable(ConsumableType consumableType, int quantity, ItemRarity itemRarity) {
        super(consumableType.toString(), DEFAULT_WEIGHT_PER_UNIT, itemRarity);
        this.consumableType = consumableType;
        this.quantity = quantity;
    }

    public ConsumableType getConsumableType() { return consumableType; }
    public int getQuantity() { return quantity; }
    public void addQuantity(int amount) { quantity += amount; }
    public void removeQuantity(int amount) { quantity -= amount; }

    @Override
    public double getWeight() {
        return weight * quantity;
    }

    @Override
    public String getType() { return "CONSUMABLE"; }

    @Override
    public String toString() {
        return "CONSUMABLE, " + itemRarity + ", " + itemName + ", " + weight + " kg, x" + quantity;
    }
}