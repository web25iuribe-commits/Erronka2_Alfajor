package app.controller;

import app.App;
import javafx.fxml.FXML;

public class erabiltzailePrintzipala {
    
    @FXML
    private void makinakMenu() throws Exception {
        App.setRoot("Makina_printzipala");
    }
    @FXML
    private void erabiltzaileakMenu() throws Exception {
        App.setRoot("Erabiltzailea_printzipala");
    }
    @FXML
    private void piezakMenu() throws Exception {
        App.setRoot("Pieza_printzipala");
    }

    @FXML
    private void menuaMenu() throws Exception {    
        App.setRoot("menua (1)");
    }
    @FXML
    private void erabiltzaileGehitu() throws Exception {
        App.setRoot("Erabiltzailea_gehitu");
    }
    @FXML
    private void erabiltzaileEzabatu() throws Exception {
        App.setRoot("Erabiltzaile_ezabatu");
    }       
    @FXML
    private void erabiltzaileAldatu() throws Exception {
        App.setRoot("Erabiltzaile_aldatu");
    }
}
