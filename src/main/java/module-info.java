module order2 {

    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    requires java.sql;
    requires java.sql.rowset;

    opens application;
    opens controller;
}