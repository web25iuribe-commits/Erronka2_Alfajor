package app.controller;

import app.App;
import javafx.fxml.FXML;

public class menua {

    @FXML
    private void makinakMenu() throws Exception {
        App.setRoot("Makinak_printzipala");
    }
    @FXML
    private void erabiltzaileakMenu() throws Exception {
        App.setRoot("Erabiltzaileak_printzipala");
    }

    @FXML
    private void piezakMenu() throws Exception {    
        App.setRoot("Pieza_printzipala");
    }
    /*  @FXML private ImageView MiguelAltuna;


    @FXML private ImageView miguel_altuna; // match fx:id in FXML

    @FXML
    public void initialize() {
        var is = getClass().getResourceAsStream("/app/miguel-altuna.jpg");
        if (is != null) {
            miguel_altuna.setImage(new Image(is));
        } else {
            System.err.println("Imagen no encontrada: /app/miguel-altuna.jpg");
        }
    }
    // ...existing code...

*/ 
}