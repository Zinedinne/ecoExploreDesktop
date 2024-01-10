module com.example.ecodesk {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.datatransfer;

    opens com.example.ecodesk to javafx.fxml;
    exports com.example.ecodesk;
}