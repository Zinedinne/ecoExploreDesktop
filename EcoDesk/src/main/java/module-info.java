module com.example.ecodesk {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ecodesk to javafx.fxml;
    exports com.example.ecodesk;
}