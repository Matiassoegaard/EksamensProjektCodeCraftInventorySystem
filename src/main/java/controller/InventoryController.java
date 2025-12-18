package controller;

import exception.InvalidItemException;
import exception.InventoryFullException;
import exception.WeightLimitException;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.ObservableList;
import logik.FileManager;
import logik.Inventory;
import model.*;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class InventoryController {
    @FXML
    private TableView<ItemTableData> itemTable;

    @FXML
    private TableColumn<ItemTableData, String> typeColumn;

    @FXML
    private TableColumn<ItemTableData, String> rarityColumn;

    @FXML
    private TableColumn<ItemTableData, String> nameColumn;

    @FXML
    private TableColumn<ItemTableData, Double> weightColumn;

    @FXML
    private TableColumn<ItemTableData, String> quantityColumn;

    private ObservableList<ItemTableData> tableData;
    private Inventory inventory;

    @FXML
    private Label slotsLabel;

    @FXML
    private Label levelLabel;

    @FXML
    private Label weightLabel;

    @FXML
    private Button removeButton;

    @FXML
    private Button addButton;

    @FXML
    private Button saveButton;

    @FXML
    private Button loadButton;

    @FXML
    private Button sortByNameButton;
    @FXML
    private Button sortByTypeButton;
    @FXML
    private Button sortByWeightButton;
    @FXML
    private Button levelUp;
    @FXML
    private Button deleteSaveFileButton;

    @FXML
    //This method shows the option you get when you click on 'Add Item' button.
    private void showAddItemMenu() {
        ContextMenu contextMenu = new ContextMenu();

        MenuItem weaponItem = new MenuItem("Add Weapon");
        MenuItem armorItem = new MenuItem("Add Armor");
        MenuItem consumableItem = new MenuItem("Add Consumable");

        weaponItem.setOnAction(e -> addRandomWeapon());
        armorItem.setOnAction(e -> addRandomArmor());
        consumableItem.setOnAction(e -> addRandomConsumable());

        contextMenu.getItems().addAll(weaponItem, armorItem, consumableItem);
        contextMenu.show(addButton, javafx.geometry.Side.BOTTOM, 0, 0);
    }

    //This methods makes a random type of weapon
    private void addRandomWeapon() {
        try {
            WeaponType[] types = WeaponType.values();
            WeaponType randomType = types[(int)(Math.random() * types.length)];

            ItemRarity[] rarities = ItemRarity.values();
            ItemRarity randomRarity = rarities[(int)(Math.random() * rarities.length)];

            inventory.addWeapon(randomType, randomRarity);
            refreshDisplay();
        } catch (InventoryFullException | WeightLimitException e) {
            showError(e.getMessage());
        }
    }

    //This method makes a random type of armor
    private void addRandomArmor() {
        try {
            ArmorType[] types = ArmorType.values();
            ArmorType randomType = types[(int)(Math.random() * types.length)];

            ItemRarity[] rarities = ItemRarity.values();
            ItemRarity randomRarity = rarities[(int)(Math.random() * rarities.length)];

            inventory.addArmor(randomType, randomRarity);
            refreshDisplay();
        } catch (InventoryFullException | WeightLimitException e) {
            showError(e.getMessage());
        }
    }

    //This method makes a random type of consumable
    private void addRandomConsumable() {
        try {
            ConsumableType[] types = ConsumableType.values();
            ConsumableType randomType = types[(int)(Math.random() * types.length)];

            ItemRarity[] rarities = ItemRarity.values();
            ItemRarity randomRarity = rarities[(int)(Math.random() * rarities.length)];



            int quantity = (int)(Math.random() * 5) + 1;

            inventory.addConsumable(randomType, quantity, randomRarity);
            refreshDisplay();
        } catch (InventoryFullException | WeightLimitException e) {
            showError(e.getMessage());
        }
    }

    //This Method shows and error if you cant add an item due to weight or if the inventory is full
    private void showError(String message) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Cannot add item");
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    //This method initailizes the Table columns
    public void initialize() {
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        rarityColumn.setCellValueFactory(new PropertyValueFactory<>("rarity"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        weightColumn.setCellValueFactory(new PropertyValueFactory<>("weight"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        tableData = FXCollections.observableArrayList();
        itemTable.setItems(tableData);


    }

    //Method for setting the inventory
    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
        refreshDisplay();
    }

    //This method updates the screen.
    public void refreshDisplay(){
        if (inventory == null) return;

        tableData.clear();
        for (Item item : inventory.getItems()) {
            tableData.add(new ItemTableData(item));
        }
            levelLabel.setText("level: " + inventory.getPlayer().getLevel());
            slotsLabel.setText("Slots " + inventory.getUsedSlots() + "/" +
                                inventory.getPlayer().getMaxInventorySlots());

        double currentWeight = inventory.getCurrentWeight();
        double maxWeight = inventory.getMaxWeight();
        weightLabel.setText(String.format("Weight: %.1f / %.1f kg", currentWeight, maxWeight));

    }

    @FXML
    //This method remove an selected Item, when you click on the 'Remove Selected Item' button.
    private void removeItem() {
        int selectedIndex = itemTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0){
            try{
                inventory.removeItem(selectedIndex);
                refreshDisplay();
            } catch (InvalidItemException e){
                e.getMessage();
            }
        }
    }

    @FXML
    //Method saves the inventory and player in a file.
    private void saveInventory(){
        FileManager.autoSave(inventory);
    }

    @FXML
    //Method to load the saved inventory and player.
    private void loadInventory(){
        FileManager.autoLoad(inventory);
        refreshDisplay();
    }
    @FXML
    private void deleteSaveFile(){
        FileManager.deleteSaveFile();
    }

    @FXML
    //This method levels the player up when the 'Level Up' button is pressed.
    private void handleLevelUp(){
        if(inventory != null){
            inventory.getPlayer().levelUp();
            refreshDisplay();
        }
    }
    @FXML
    //This Method sort item by name when the associated button is pressed.
    private void sortItemByName(){
        inventory.sortByName(inventory.getItems());
        refreshDisplay();
    }

    @FXML
    //This Method sort item by type when the associated button is pressed.
    private void sortItemByType(){
        inventory.sortByType(inventory.getItems());
        refreshDisplay();
    }

    @FXML
    //This Method sort item by weight when the associated button is pressed.
    private void sortItemByWeight(){
        inventory.sortByWeight(inventory.getItems());
        refreshDisplay();
    }

    //This is a inner Class for the table.
    public static class ItemTableData{
        private String type;
        private String rarity;
        private String name;
        private String quantity;
        private double weight;

        //Contruktor for the inner class.
        public ItemTableData(Item item) {
            this.type = item.getType();
            this.rarity = item.getItemRarity().toString();
            this.name = item.getItemName();
            this.weight = item.getWeight();
            if (item instanceof Consumable) {
                Consumable consumable = (Consumable) item;
                this.quantity = "x " + consumable.getQuantity();
            } else {
                this.quantity = "-----";
            }

        }
        public String getType() { return type; }
        public String getRarity() { return rarity; }
        public String getName() { return name; }
        public double getWeight() { return weight; }
        public String getQuantity() { return quantity; }
    }

}