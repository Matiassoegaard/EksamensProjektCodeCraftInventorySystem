package model;

public class Armor extends Item {
    private static final long serialVersionUID = 1L;
    private ArmorType armorType;

    //contruktor for armor class where rarity is preset.
    public Armor(ArmorType armorType) {
        super(armorType.toString(), armorType.getWeight());
        this.armorType = armorType;
    }
    //contruktor for armor class where rarity is not preset.
    public Armor(ArmorType armorType, ItemRarity itemRarity) {
        super(armorType.toString(), armorType.getWeight(), itemRarity);
        this.armorType = armorType;
    }

    @Override
    public String getType() {
        return "ARMOR";
    }

    @Override
    public String toString() {
        return "ARMOR, " + itemRarity + ", " + itemName + ", " + weight + " kg";
    }
}
