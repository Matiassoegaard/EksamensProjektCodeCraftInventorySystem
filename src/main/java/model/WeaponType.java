package model;

public enum WeaponType {
    SWORD(3.5), GREATSWORD(8.0), AXE(4.5), DAGGER(1.2),
    MACE(5.0), SPEAR(3.0), BOW(2.5), CROSSBOW(4.0);

    private double weight;

    WeaponType(double weight) {
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }
}