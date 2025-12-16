package logik;

import model.Item;

import java.util.List;

public class InventorySorter {

    // bubble-sort where it sorts items by name
    public static void sortByName(List<Item> items) {
        for(int i = 0; i < items.size()- 1; i++) {
            for( int j = 0; j < items.size() - i - 1; j++) {
                if(items.get(j).getItemName().compareTo(items.get(j + 1).getItemName()) > 0){
                    Item temp = items.get(j);
                    items.set(j, items.get(j + 1));
                    items.set(j + 1, temp);
                }
            }
        }
    }

    // bubble-sort where it sorts items by weight
    public static void sortByWeight(List<Item> items) {
        for (int i = 0; i < items.size() - 1; i++) {
            for (int j = 0; j < items.size() - i - 1; j++) {
                if (items.get(j).getWeight() > items.get(j + 1).getWeight()) {
                    Item temp = items.get(j);
                    items.set(j, items.get(j + 1));
                    items.set(j + 1, temp);
                }
            }
        }
    }

    // bubble-sort where it sorts items by type
    public static void sortByType(List<Item> items) {
        for (int i = 0; i < items.size() - 1; i++) {
            for (int j = 0; j < items.size() - i - 1; j++) {
                if (items.get(j).getType().compareTo(items.get(j + 1).getType()) > 0) {
                    Item temp = items.get(j);
                    items.set(j, items.get(j + 1));
                    items.set(j + 1, temp);
                }
            }
        }
    }
}

