package app.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import app.App;
import app.DBKonexioa;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class piezaGehitu {

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


    @FXML private TextField idPieza;
    @FXML private TextField izenaPieza;
    @FXML private TextField deskribapenaPieza;
    @FXML private TextField prezioaPieza;
    @FXML private TextField stockPieza;
    @FXML private TextField pisuPieza;
    @FXML private TextField id_pieza_Mota;

    @FXML
    private void piezaGehitu() throws IOException {

        String id = idPieza.getText();
        String izena = izenaPieza.getText();
        String deskribapena = deskribapenaPieza.getText();
        int prezioa = Integer.parseInt(prezioaPieza.getText());
        int stock = Integer.parseInt(stockPieza.getText());
        String pisua = pisuPieza.getText();

        System.out.println("ID: " + id);
        System.out.println("Izena: " + izena);
        System.out.println("Deskribapena: " + deskribapena);
        System.out.println("Prezioa: " + prezioa);
        System.out.println("Stock: " + stock);
        System.out.println("Pisua: " + pisua);

        // DATU GUZTIAK BETE BEHAR DIRA.
        if ( id.isEmpty() || izena.isEmpty() || deskribapena.isEmpty() || prezioa <= 0 || stock <= 0 || pisua.isEmpty() ) {
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
        if (String.valueOf(prezioa).length() > 10) {
            System.out.println("ERROREA: Prezioak 10 karaktere baino gehiago ditu.");
            return;
        }
        if (String.valueOf(stock).length() > 7) {
            System.out.println("ERROREA: Stock-ak 7 karaktere baino gehiago ditu.");
            return;
        }

        // PREZIOA, STOCK-A ETA PISUA ZENBAKIAK IZAN BEHAR DIRA ETA EZIN DIRA NEGATIBOAK IZAN.
        try {
            double prezioaDouble = Double.parseDouble(String.valueOf(prezioa));
            if (prezioaDouble <= 0) {
                System.out.println("ERROREA: Prezioa ezin da negatiboa edo zero izan.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("ERROREA: Prezioa zenbaki bat izan behar da.");
            return;      
        }
        try {
            int stockInt = Integer.parseInt(String.valueOf(stock));
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


        DBKonexioa konex = new DBKonexioa();
        Connection cn = null;

         try {
            cn = konex.konektatu();
            if (cn != null && !cn.isClosed()) {

                // INSERT en la tabla PIEZA
                String sql = "INSERT INTO PIEZA (Id_pieza, Izena, Deskribapena, Pisua, Prezioa, Stock) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement ps = cn.prepareStatement(sql);
                ps.setString(1, id);
                ps.setString(2, izena);
                ps.setString(3, deskribapena);
                ps.setInt(4, Integer.parseInt(pisua));
                ps.setDouble(5, Double.parseDouble(String.valueOf(prezioa)));
                ps.setInt(6, Integer.parseInt(String.valueOf(stock)));
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Pieza gehitu da datu-basean!");
                    App.setRoot("Pieza_printzipala");
                } else {
                    System.out.println("Errorea: Pieza ez da gehitu.");
                }
                ps.close();
                cn.close();
                System.out.println("Konexioa itxi da.");
            }

        } catch (SQLException e) {
            System.out.println("Errorea datu-basera konektatzean");
            e.printStackTrace();
        }
        
    }
      @FXML
    private void Bueltatu() throws IOException {
        App.setRoot("Pieza_printzipala");
    }
}




















       /* try {
            // konektatu() metodoak SQLException jaurti dezake
            cn = konex.konektatu();

            // konexioa existitzen dela eta irekita dagoela konprobatzen du
            if (cn != null && !cn.isClosed()) {
                System.out.println("Komunikazio kanala irekita dago.");

                // Konexioa itxi egiten da
                cn.close();
                System.out.println("Konexioa itxi da.");
            }

        } catch (SQLException e) {
            System.out.println("Errorea datu-basera konektatzean");
            e.printStackTrace();
        }

        System.out.println("Pieza gehitu da!");
        App.setRoot("pieza_printzipala");
    }

    @FXML
    private void piezaBueltatu() throws IOException {
        App.setRoot("pieza_printzipala");
    }
} */