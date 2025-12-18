package model;

public class Weapon extends Item {
    private static final long serialVersionUID = 1L;
    private WeaponType weaponType;

    //Constructor with a preset rarity.
    public Weapon(WeaponType weaponType) {
        super(weaponType.toString(), weaponType.getWeight());
        this.weaponType = weaponType;
    }

    //Constructor without a preset rarity.
    public Weapon(WeaponType weaponType, ItemRarity itemRarity) {
        super(weaponType.toString(), weaponType.getWeight(), itemRarity);
        this.weaponType = weaponType;
    }


    @Override
    public String getType() { return "WEAPON"; }

    @Override
    public String toString() {
        return "WEAPON, " + itemRarity + ", " + itemName + ", " + weight + " kg";
    }
}