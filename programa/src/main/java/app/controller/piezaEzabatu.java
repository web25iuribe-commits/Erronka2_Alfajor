package app.controller;

import java.io.IOException;

import app.App;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class piezaEzabatu {
    
    @FXML private TextField idPiezaEzabatu;

    @FXML
    private void piezaEzabatu() throws IOException {
        
        String id = idPiezaEzabatu.getText();

        System.out.println("ID-a ezabatu da: " + id);

        if (id.isEmpty()) {
            System.out.println("ERROREA: ID bete behar da.");
            return;
        }

        if (id.length() > 4) {
            System.out.println("ERROREA: ID-ak 4 karaktere baino gehiago ditu.");
            return;
        }


        System.out.println("Pieza ezabatu da!");
        App.setRoot("Pieza_printzipala");
    }

    @FXML
    private void piezaBueltatu() throws IOException {
        App.setRoot("Pieza_printzipala");
    }
}

