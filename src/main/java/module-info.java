module com.example.supplychainmanjeet {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.supplychainmanjeet to javafx.fxml;
    exports com.example.supplychainmanjeet;
}