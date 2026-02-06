package app.controller;

import app.App;
import javafx.fxml.FXML; 



public class piezaAldatu {

        @FXML
    private void makinakMenu() throws Exception {
        App.setRoot("Makinak_printzipala");
    }
        @FXML
    private void erabiltzaileakMenu() throws Exception {
        App.setRoot("Erabiltzaileak_printzipala");
    }

      /*  @FXML
    private void piezakMenu() throws Exception {    
        App.setRoot("Pieza_printzipala");
    }
    */ 
        @FXML
    private void menuaMenu() throws Exception {    
        App.setRoot("menua (1)");
    }
    String id = ""; // Obtener el ID de la pieza a modificar
        

    }


