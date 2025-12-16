module com.example.eksamensprojektcodecraftinventorysystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens controller to javafx.fxml;
    exports controller;
}