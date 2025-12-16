package data;

import model.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class InventoryData implements Serializable {
    private static final long serialVersionUID = 1L;
    private int playerLevel;
    private List<Item> items;

    public InventoryData(Player player, List<Item> items) {
        this.playerLevel = player.getLevel();
        this.items = new ArrayList<>(items);
    }

    public int getPlayerLevel() {
        return playerLevel;
    }

    public List<Item> getItems() {
        return items;
    }
}
