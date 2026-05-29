package uk.mercator.group;

public enum Fruits {
    ORANGES(25),
    APPLE(60);

    private final int unitCost;
    Fruits(int unitCost) {
        this.unitCost = unitCost;
    }

    public int getUnitCost() {return unitCost;}
}
