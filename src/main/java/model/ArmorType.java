package model;

public enum ArmorType {
    HELMET(2.5), CHESTPLATE(8.0), LEGGINGS(5.0), BOOTS(2.0),
    GLOVES(1.5), SHIELD(6.0), SHOULDER_PADS(3.0), BELT(1.0);

    private double weight;

    ArmorType(double weight) {
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }
}