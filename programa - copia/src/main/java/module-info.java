module app {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j
    ;

    opens app to javafx.fxml;
    exports app;
    opens app.controller to javafx.fxml;
    exports app.controller;
    opens app.model to javafx.fxml;
    exports app.model;
    
}
