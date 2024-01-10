module com.example.ecodesk {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.datatransfer;
    requires org.apache.httpcomponents.httpcore;
    requires org.apache.httpcomponents.httpclient;

    opens com.example.ecodesk to javafx.fxml;
    exports com.example.ecodesk;
    exports com.example.ecodesk.SingletonClass;
    opens com.example.ecodesk.SingletonClass to javafx.fxml;
}