package controller;

import exception.InvalidItemException;
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
import model.Consumable;
import model.Item;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class InventoryController {
    @FXML
    private Label welcomeText;

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
    public void initialize() {
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        rarityColumn.setCellValueFactory(new PropertyValueFactory<>("rarity"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        weightColumn.setCellValueFactory(new PropertyValueFactory<>("weight"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        tableData = FXCollections.observableArrayList();
        itemTable.setItems(tableData);


    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
        refreshDisplay();
    }

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
    private void saveInventory(){
        FileManager.autoSave(inventory);
    }

    @FXML
    private void loadInventory(){
        FileManager.autoLoad(inventory);
        refreshDisplay();
    }

    @FXML
    private void HandlelevelUp(){
        if(inventory != null){
            inventory.getPlayer().levelUp();
            refreshDisplay();
        }
    }
    @FXML
    private void sortItemByName(){
        inventory.sortByName(inventory.getItems());
        refreshDisplay();
    }

    @FXML
    private void sortItemByType(){
        inventory.sortByType(inventory.getItems());
        refreshDisplay();
    }

    @FXML
    private void sortItemByWeight(){
        inventory.sortByWeight(inventory.getItems());
        refreshDisplay();
    }

    public static class ItemTableData{
        private String type;
        private String rarity;
        private String name;
        private String quantity;
        private double weight;

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