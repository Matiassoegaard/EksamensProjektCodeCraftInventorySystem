package model;

public class Armor extends Item {
    private static final long serialVersionUID = 1L;
    private ArmorType armorType;

    public Armor(ArmorType armorType) {
        super(armorType.toString(), armorType.getWeight());
        this.armorType = armorType;
    }

    public Armor(ArmorType armorType, ItemRarity itemRarity) {
        super(armorType.toString(), armorType.getWeight(), itemRarity);
        this.armorType = armorType;
    }

    public ArmorType getArmorType() { return armorType; }

    @Override
    public String getType() {
        return "ARMOR";
    }

    @Override
    public String toString() {
        return "ARMOR, " + itemRarity + ", " + itemName + ", " + weight + " kg";
    }
}
