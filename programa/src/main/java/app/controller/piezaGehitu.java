package app.controller;

import java.io.IOException;

import app.App;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class piezaGehitu {

    @FXML private TextField idPieza;
    @FXML private TextField izenaPieza;
    @FXML private TextField deskribapenaPieza;
    @FXML private TextField prezioaPieza;
    @FXML private TextField stockPieza;
    @FXML private TextField pisuPieza;

    @FXML
    private void piezaGehitu() throws IOException {

        String id = idPieza.getText();
        String izena = izenaPieza.getText();
        String deskribapena = deskribapenaPieza.getText();
        String prezioa = prezioaPieza.getText();
        String stock = stockPieza.getText();
        String pisua = pisuPieza.getText();

        System.out.println("ID: " + id);
        System.out.println("Izena: " + izena);
        System.out.println("Deskribapena: " + deskribapena);
        System.out.println("Prezioa: " + prezioa);
        System.out.println("Stock: " + stock);
        System.out.println("Pisua: " + pisua);

        // DATU GUZTIAK BETE BEHAR DIRA.
        if ( id.isEmpty() || izena.isEmpty() || deskribapena.isEmpty() || prezioa.isEmpty() || stock.isEmpty() ) {
            System.out.println("ERROREA: Datu guztiak bete behar dira.");
            return;
        }

        // ATRIBUTU BAKOITZAREN KARAKTERE MAXIMOAK KONTROLATU.
        if (izena.length() > 20) {
            System.out.println("ERROREA: Izena ezin da 20 karaktere baino gehiago izan");
        return;
        }
        if (id.length() > 4) {
            System.out.println("ERROREA: ID-ak 4 karaktere baino gehiago ditu.");
            return;
        }
        if (deskribapena.length() > 150) {
            System.out.println("ERROREA: Deskribapenak 150 karaktere baino gehiago ditu.");
            return;
        }
        if (prezioa.length() > 10) {
            System.out.println("ERROREA: Prezioak 10 karaktere baino gehiago ditu.");
            return;
        }
        if (stock.length() > 7) {
            System.out.println("ERROREA: Stock-ak 7 karaktere baino gehiago ditu.");
            return;
        }

        // PIEZAKO ID-A EZIN DELAKO BERDINA IZAN.
        /* boolean exists = false; 
        if(exists){
            System.out.println("ERROREA: ID hori pieza bat du");
            return;
        }*/

        // PREZIOA, STOCK-A ETA PISUA ZENBAKIAK IZAN BEHAR DIRA ETA EZIN DIRA NEGATIBOAK IZAN.
        try {
            double prezioaDouble = Double.parseDouble(prezioa);
            if (prezioaDouble <= 0) {
                System.out.println("ERROREA: Prezioa ezin da negatiboa edo zero izan.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("ERROREA: Prezioa zenbaki bat izan behar da.");
            return;      
        }
        try {
            int stockInt = Integer.parseInt(stock);
            if (stockInt <= 0) {
                System.out.println("ERROREA: Stock-a ezin da negatiboa edo zero izan.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("ERROREA: Stock-a zenbaki bat izan behar da.");
            return;
        }

        try {
            int pisuaInt = Integer.parseInt(pisua);
            if (pisuaInt <= 0) {
                System.out.println("ERROREA: Pisua ezin da negatiboa izan.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("ERROREA: Pisuak zenbaki bat izan behar da.");
            return;
        }
        System.out.println("Pieza gehitu da!");
        App.setRoot("pieza_printzipala");
    }

    @FXML
    private void piezaBueltatu() throws IOException {
        App.setRoot("pieza_printzipala");
    }
}