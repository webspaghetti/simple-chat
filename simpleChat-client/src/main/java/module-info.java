module xyz.webspaghetti.simplechatclient {
    requires javafx.controls;
    requires javafx.fxml;


    opens xyz.webspaghetti.simplechatclient to javafx.fxml;
    exports xyz.webspaghetti.simplechatclient;
}