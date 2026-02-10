package app.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import app.App;
import app.DBKonexioa;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class piezaEzabatu {
    
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

        

        DBKonexioa konex = new DBKonexioa();
        Connection cn = null;

        try {
            // konektatu() metodoak SQLException jaurti dezake
            cn = konex.konektatu();

            // konexioa existitzen dela eta irekita dagoela konprobatzen du
            if (cn != null && !cn.isClosed()) {
                String sql = "DELETE FROM PIEZA WHERE Id_pieza = ?";
                java.sql.PreparedStatement ps = cn.prepareStatement(sql);
                ps.setString(1, id);
                ps.executeUpdate();
                ps.close();

                // Konexioa itxi egiten da
                cn.close();
                System.out.println("Konexioa itxi da.");
            }

        } catch (SQLException e) {
            System.out.println("Errorea datu-basera konektatzean");
            e.printStackTrace();
        }


        System.out.println("Pieza ezabatu da!");
        App.setRoot("Pieza_printzipala");
    }
    @FXML
    private void Bueltatu() throws IOException {
        App.setRoot("Pieza_printzipala");
    }

}

