package model;

import java.io.Serializable;

public class Player implements Serializable{
    private static final long serialVersionUID = 1L;
    private int level;
    private int maxInventorySlots;
    private static final int SLOTS_PER_LEVEL = 32;
    private static final int MAX_LEVEL = 6;

    public Player() {
        this.level = 1;
        updateMaxSlots();
    }

    public int getLevel() {
        return level;
    }

    public int getMaxInventorySlots() {
        return maxInventorySlots;
    }

    public void levelUp() {
        if (level < MAX_LEVEL) {
            level++;
            updateMaxSlots();
        }
    }

    private void updateMaxSlots() {
        maxInventorySlots = level * SLOTS_PER_LEVEL;
    }

    public void reset() {
        level = 1;
        updateMaxSlots();
    }
}