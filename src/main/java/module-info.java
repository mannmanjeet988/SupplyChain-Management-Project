module com.example.supplychainmanjeet {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.supplychainmanjeet to javafx.fxml;
    exports com.example.supplychainmanjeet;
}