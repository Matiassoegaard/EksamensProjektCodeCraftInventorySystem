package controller;

import exception.InventoryFullException;
import exception.WeightLimitException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import logik.Inventory;
import model.*;

import java.io.IOException;

public class InventoryApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
       FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/inventoryView.fxml"));
       Parent root = fxmlLoader.load();

        Player player = new Player();
        Inventory inventory = new Inventory(player);

        addTestItems(inventory);

        InventoryController controller = fxmlLoader.getController();
        controller.setInventory(inventory);

        Scene scene = new Scene(root);
        stage.setTitle("Inventory");
        stage.setScene(scene);
        stage.show();



    }
    private void addTestItems(Inventory inventory) {
        try {
            // Add weapons with different rarities
            inventory.addWeapon(WeaponType.SWORD, ItemRarity.RARE);
            inventory.addWeapon(WeaponType.BOW, ItemRarity.COMMON);
            inventory.addWeapon(WeaponType.DAGGER, ItemRarity.EPIC);

            // Add armor
            inventory.addArmor(ArmorType.HELMET, ItemRarity.COMMON);
            inventory.addArmor(ArmorType.CHESTPLATE, ItemRarity.RARE);
            inventory.addArmor(ArmorType.BOOTS, ItemRarity.COMMON);

            // Add consumables
            inventory.addConsumable(ConsumableType.POTION, "Health Potion", 5, ItemRarity.COMMON);
            inventory.addConsumable(ConsumableType.FOOD, "Bread", 10, ItemRarity.COMMON);
            inventory.addConsumable(ConsumableType.POTION, "Mana Potion", 3, ItemRarity.COMMON);

        } catch (InventoryFullException | WeightLimitException e) {
            System.err.println("Error adding test items: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}