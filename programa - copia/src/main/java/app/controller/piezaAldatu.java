package app.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import app.App;
import app.DBKonexioa;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class piezaAldatu {

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
     
        @FXML
    private void menuaMenu() throws Exception {    
        App.setRoot("menua (1)");
    }

    @FXML private TextField idPieza;
    @FXML private TextField izenaPieza;
    @FXML private TextField deskribapenaPieza;
    @FXML private TextField prezioaPieza;
    @FXML private TextField stockPieza;
    @FXML private TextField pisuaPieza;

        @FXML
    private void piezaAldatu() throws IOException {

        String id = idPieza.getText(); 
        String izena = izenaPieza.getText(); 
        String deskribapena = deskribapenaPieza.getText(); 
        int prezioa = Integer.parseInt(prezioaPieza.getText()); 
        int stock = Integer.parseInt(stockPieza.getText()); 
        String pisua = pisuaPieza.getText();

        System.out.println("ID: " + id);
        System.out.println("Izena: " + izena);
        System.out.println("Deskribapena: " + deskribapena);
        System.out.println("Prezioa: " + prezioa);
        System.out.println("Stock: " + stock);
        System.out.println("Pisua: " + pisua);

            if (id.length() > 4) {
                System.out.println("ERROREA: ID-ak 4 karaktere baino gehiago ditu.");
                return;
            }
            if (deskribapena.length() > 150) {
                System.out.println("ERROREA: Deskribapenak 150 karaktere baino gehiago ditu.");
                return;
            }


        DBKonexioa konex = new DBKonexioa();
        Connection cn = null;

         try {
            cn = konex.konektatu();
            if (cn != null && !cn.isClosed()) {

                String sql = "UPDATE PIEZA SET Izena=?, Deskribapena=?, Pisua=?, Prezioa=?, Stock=? WHERE Id_pieza=?";
                PreparedStatement ps = cn.prepareStatement(sql);
                ps.setString(1, izena);
                ps.setString(2, deskribapena);
                ps.setInt(3, Integer.parseInt(pisua));
                ps.setInt(4, prezioa);
                ps.setInt(5, stock);
                ps.setString(6, id);
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Pieza aldatu da datu-basean!");
                    App.setRoot("Pieza_printzipala");
                } else {
                    System.out.println("Errorea: Pieza ez da aldatu.");
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

