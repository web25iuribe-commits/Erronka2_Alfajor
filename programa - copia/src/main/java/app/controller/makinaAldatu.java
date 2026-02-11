package app.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import app.App;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class makinaAldatu {
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
    @FXML private TextField Id_makina;
    @FXML private TextField Izena;
    @FXML private TextField Deskribapena;
    @FXML private TextField Potentzia;
    @FXML private TextField Instalazio_data;

    @FXML
    private void makinaAldatu() throws Exception {    
   
        String id = Id_makina.getText(); 
        String izena = Izena.getText(); 
        String Inst_data = Instalazio_data.getText();
        String desk = Deskribapena.getText();
        String potentzia = Potentzia.getText();        

        System.out.println("ID: " + id);
        System.out.println("Izena: " + izena);
        System.out.println("Instalazio data: " + Inst_data);
        System.out.println("Deskribapena: " + desk); 
        System.out.println("Potentzia: " + potentzia);

    if (id.length() > 4) {
        System.out.println("ERROREA: ID-ak 4 karaktere baino gehiago ditu.");
        return;
    }

    if (izena.length() > 30) {
        System.out.println("ERROREA: Izena ezin da 30 karaktere baino gehiago izan.");
        return;
    }

    if (Inst_data.length() > 20) {
        System.out.println("ERROREA: Instalazio datak 20 karaktere baino gehiago ditu.");
        return;
    }

    if (desk.length() > 150) {
        System.out.println("ERROREA: Deskribapenak 150 karaktere baino gehiago ditu.");
        return;
    }

    int potentziaInt;
    try {
        potentziaInt = Integer.parseInt(potentzia);
        if (potentziaInt < 0) {
            System.out.println("ERROREA: Potentzia ezin da negatiboa izan.");
            return;
        }
    } catch (NumberFormatException e) {
        System.out.println("ERROREA: Potentzia zenbakia izan behar da.");
        return;
    }
    
    DBKonexioa konex = new DBKonexioa();
        Connection cn = null;

        try {
            cn = konex.konektatu();
            if (cn != null && !cn.isClosed()) {

                String sql = "UPDATE MAKINA SET Izena=?, Instalazio_data=?, Deskribapena=?, Potentzia=? WHERE Id_makina=?";
                PreparedStatement ps = cn.prepareStatement(sql);
                ps.setString(1, izena);
                ps.setString(2, Inst_data);
                ps.setString(3, desk);
                ps.setString(4, potentzia);
                ps.setString(5, id);
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Makina aldatu da datu-basean!");
                    App.setRoot("Makina_printzipala");
                } else {
                    System.out.println("Errorea: Makina ez da aldatu.");
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
        App.setRoot("Makina_printzipala");
    }
}

