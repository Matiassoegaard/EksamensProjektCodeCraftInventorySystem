package logik;

import data.InventoryData;
import exception.FileHandlingException;
import exception.InventoryFullException;
import exception.WeightLimitException;
import model.*;

import java.io.*;

public class FileManager {

    // sets default savefile name
    private static final String DEFAULT_SAVE_FILE = "inventory_save.dat";

    // saves our inventory to our savefile
    public static void autoSave(Inventory inventory) {
        try{
            saveToFile(inventory, DEFAULT_SAVE_FILE); // calls saveToFile method
        } catch (FileHandlingException e) {
            System.out.println("Failed to save inventory: " + e.getMessage());
        }
    }

    // creates a savefile object and loads data from it into our inventory
    public static void autoLoad(Inventory inventory) {
        File saveFile = new File(DEFAULT_SAVE_FILE);
        if (saveFile.exists()) {
            try {
                loadFromFile(inventory, DEFAULT_SAVE_FILE); // calls loadFromFile method
            } catch (FileHandlingException e) {
                System.out.println("Failed to load inventory: " + e.getMessage());
            }
        }
    }

    // deletes our savefile data
    public static void deleteSaveFile() {
        File saveFile = new File(DEFAULT_SAVE_FILE);
        if (saveFile.exists()) {
            saveFile.delete();
        }
    }

    // Method for saving inventory data to our savefile
    private static void saveToFile(Inventory inventory, String filename) throws FileHandlingException {
        InventoryData data = new InventoryData(inventory.getPlayer(), inventory.getItems());
        // creates an InventoryData object and then uploads it to the savefile
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(data);
        }catch (IOException e) {
            throw new FileHandlingException("Failed to save inventory: " + e.getMessage());
        }
    }


    // Method for loading inventory data from our savefile
    private static void loadFromFile(Inventory inventory, String filename) throws FileHandlingException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            InventoryData data = (InventoryData) in.readObject();
            // reads InventoryData object from our savefile

            inventory.clear(); // clears inventory before loading savefile

            Player player = inventory.getPlayer();
            player.reset();    // resets player level before loading savefile
            for (int i = 1; i < data.getPlayerLevel(); i++) {
                player.levelUp();
            }

            for (Item item : data.getItems()) {
                try {
                    inventory.addItem(item);
                } catch (InventoryFullException | WeightLimitException e) {
                    // If inventory is full or the weight limit is exceeded during loading, skip the item
                }
            }
        } catch (FileNotFoundException e) {
            throw new FileHandlingException("File not found: " + filename);
        } catch (IOException e) {
            throw new FileHandlingException("Error reading file: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new FileHandlingException("Data format error: " + e.getMessage());
        }
    }
}
