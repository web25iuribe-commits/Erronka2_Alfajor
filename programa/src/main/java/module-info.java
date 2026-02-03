module app {
    requires javafx.controls;
    requires javafx.fxml;

    opens app to javafx.fxml;
    exports app;
    opens app.controller to javafx.fxml;
    exports app.controller;
    opens app.model to javafx.fxml;
    exports app.model;
    
}
