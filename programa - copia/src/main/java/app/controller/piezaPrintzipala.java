package app.controller;

import java.io.IOException;

import app.App;
import javafx.fxml.FXML;

public class piezaPrintzipala {


        @FXML
    private void makinakMenu() throws Exception {
        App.setRoot("Makinak_printzipala");
    }
    @FXML
    private void erabiltzaileakMenu() throws Exception {
        App.setRoot("Erabiltzaileak_printzipala");
    }

    @FXML
    private void menuaMenu() throws Exception {    
        App.setRoot("menua (1)");
    }

        @FXML
    private  void piezaGehitu() throws IOException {
        App.setRoot("Pieza_gehitu");
    }
        @FXML
    private void piezaEzabatu() throws IOException {
        App.setRoot("Pieza_ezabatu");
}
        @FXML
    private void piezaAldatu() throws IOException {
        App.setRoot("Pieza_aldatu");
}
}

