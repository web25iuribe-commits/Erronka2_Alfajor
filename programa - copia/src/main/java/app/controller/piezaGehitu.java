package app.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import app.App;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

public class piezaGehitu {

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


    @FXML private TextField idPieza;
    @FXML private TextField izenaPieza;
    @FXML private TextField deskribapenaPieza;
    @FXML private TextField prezioaPieza;
    @FXML private TextField stockPieza;
    @FXML private TextField pisuPieza;
    @FXML private TextField id_pieza_Mota;

    @FXML
    private void piezaGehitu() throws IOException {

        Alert alert = new Alert(AlertType.ERROR);
        Alert alerta = new Alert(AlertType.INFORMATION);

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
            alert.setTitle("ERROREA"); 
            alert.setHeaderText(null); 
            alert.setContentText("ERROREA: Datu guztiak bete behar dira."); 
            alert.showAndWait();
            return;
         }

        // ATRIBUTU BAKOITZAREN KARAKTERE MAXIMOAK KONTROLATU.
        if (izena.length() > 20) {
            alert.setTitle("ERROREA"); 
            alert.setHeaderText(null); 
            alert.setContentText("Izena ezin da 20 karaktere baino gehiago izan."); 
            alert.showAndWait(); 
            return;
        }
        if (id.length() > 4) {
            alert.setTitle("ERROREA"); 
            alert.setHeaderText(null); 
            alert.setContentText("Id-a ezin da 4 karaktere baino gehiago izan"); 
            alert.showAndWait(); 
            return;
        }
        if (deskribapena.length() > 150) {
            alert.setTitle("ERROREA"); 
            alert.setHeaderText(null); 
            alert.setContentText("Deskribapenak 150 karaktere baino gehiago ditu."); 
            alert.showAndWait(); 

            return;
        }
        if (String.valueOf(prezioa).length() > 10) {
            alert.setTitle("ERROREA"); 
            alert.setHeaderText(null); 
            alert.setContentText("Prezioak 10 karaktere baino gehiago ditu."); 
            alert.showAndWait(); 
            return;
        }
        if (String.valueOf(stock).length() > 7) {
            alert.setTitle("ERROREA"); 
            alert.setHeaderText(null); 
            alert.setContentText("Stock-ak 7 karaktere baino gehiago ditu."); 
            alert.showAndWait(); 
            return;
        }
        // PREZIOA, STOCK-A ETA PISUA ZENBAKIAK IZAN BEHAR DIRA ETA EZIN DIRA NEGATIBOAK IZAN.
        try {
            double prezioaDouble = Double.parseDouble(String.valueOf(prezioa));
            if (prezioaDouble <= 0) {
                alert.setTitle("ERROREA"); 
                alert.setHeaderText(null);
                alert.setContentText("Prezioa ezin da negatiboa edo 0 izan."); 
                alert.showAndWait();            
                return;
            }
        } catch (NumberFormatException e) {
            alert.setTitle("ERROREA"); 
            alert.setHeaderText(null);
            alert.setContentText("Prezioa zenbakia izan behar da."); 
            alert.showAndWait();
            return;    
        }
        try {
            int stockInt = Integer.parseInt(String.valueOf(stock));
            if (stockInt < 0) {
                alert.setTitle("ERROREA"); 
                alert.setHeaderText(null);
                alert.setContentText("Stock-a ezin da negatiboa izan."); 
                alert.showAndWait();            
                return;
            }
        } catch (NumberFormatException e) {
            alert.setTitle("ERROREA"); 
            alert.setHeaderText(null);
            alert.setContentText("Stock-a zenbakia izan behar da."); 
            alert.showAndWait();
            return;
        }

        try {
            int pisuaInt = Integer.parseInt(pisua);
            if (pisuaInt <= 0) {
            alert.setTitle("ERROREA"); 
            alert.setHeaderText(null);
            alert.setContentText("Pisua ezin da negatiboa edo 0 izan."); 
            alert.showAndWait();            
            return;
            }
        } catch (NumberFormatException e) {
            alert.setTitle("ERROREA"); 
            alert.setHeaderText(null);
            alert.setContentText("Pisua zenbakia izan behar da."); 
            alert.showAndWait();
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
                    alerta.setTitle("GEHITUTA!"); 
                    alerta.setHeaderText(null);
                    alerta.setContentText("Pieza gehitu da datu-basean!"); 
                    alerta.showAndWait();
                    App.setRoot("Pieza_printzipala");
                } else {
                    alert.setTitle("ERROREA"); 
                    alert.setHeaderText(null);
                    alert.setContentText("Pieza ez da gehitu.");
                }
                ps.close();
                cn.close();
                System.out.println("Konexioa itxi da.");
            }

        } catch (SQLException e) {
            alert.setTitle("ERROREA"); 
            alert.setHeaderText(null);
            alert.setContentText("Errorea datu-basera konektatzean"); 
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