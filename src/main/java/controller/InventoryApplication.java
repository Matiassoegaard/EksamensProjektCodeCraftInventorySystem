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
    //This method add some test items in then inventory.
    private void addTestItems(Inventory inventory) {
        try {
            // Add default weapons
            inventory.addWeapon(WeaponType.SWORD, ItemRarity.RARE);
            inventory.addWeapon(WeaponType.BOW, ItemRarity.COMMON);
            inventory.addWeapon(WeaponType.DAGGER, ItemRarity.EPIC);

            // Add default armor
            inventory.addArmor(ArmorType.HELMET, ItemRarity.COMMON);
            inventory.addArmor(ArmorType.CHESTPLATE, ItemRarity.RARE);
            inventory.addArmor(ArmorType.BOOTS, ItemRarity.COMMON);

            // Add default consumables
            inventory.addConsumable(ConsumableType.BEER, 5, ItemRarity.COMMON);
            inventory.addConsumable(ConsumableType.DRIED_MEAT, 10, ItemRarity.COMMON);
            inventory.addConsumable(ConsumableType.HEALTH_POTION, 3, ItemRarity.COMMON);


        } catch (InventoryFullException | WeightLimitException e) {
            System.err.println("Error adding test items: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}