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
   
        Alert alert = new Alert(AlertType.ERROR);
        Alert alerta = new Alert(AlertType.INFORMATION);

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

    if (id.isEmpty() || izena.isEmpty() || Inst_data.isEmpty() || desk.isEmpty()
        || potentzia.isEmpty()) {
        alert.setTitle("ERROREA"); 
        alert.setHeaderText(null); 
        alert.setContentText("ERROREA: Datu guztiak bete behar dira."); 
        alert.showAndWait();
        return;    
    }
    if (id.length() > 4) {
        alert.setTitle("ERROREA"); 
        alert.setHeaderText(null);
        alert.setContentText("ID-ak 4 karaktere baino gehiago ditu."); 
        alert.showAndWait();
        return;
    }

    if (izena.length() > 30) {
        alert.setTitle("ERROREA"); 
        alert.setHeaderText(null);
        alert.setContentText("Izena ezin da 30 karaktere baino gehiago izan."); 
        alert.showAndWait();
        return;
    }

    if (Inst_data.length() > 20) {
        alert.setTitle("ERROREA"); 
        alert.setHeaderText(null);
        alert.setContentText("Instalazio datak 20 karaktere baino gehiago ditu."); 
        alert.showAndWait();
        return;
    }

    if (desk.length() > 150) {
        alert.setTitle("ERROREA"); 
        alert.setHeaderText(null);
        alert.setContentText("Deskribapenak 150 karaktere baino gehiago ditu."); 
        alert.showAndWait();
        return;
    }

    int potentziaInt;
    try {
        potentziaInt = Integer.parseInt(potentzia);
        if (potentziaInt < 0) {
            alert.setTitle("ERROREA"); 
            alert.setHeaderText(null);
            alert.setContentText("Potentzia ezin da negatiboa izan."); 
            alert.showAndWait();
            return;
        }
    } catch (NumberFormatException e) {
        alert.setTitle("ERROREA"); 
        alert.setHeaderText(null);
        alert.setContentText("Potentzia zenbakia izan behar da."); 
        alert.showAndWait();
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
                alerta.setTitle("ALDATUTA!"); 
                alerta.setHeaderText(null);
                alerta.setContentText("Makina aldatu da datu-basean!"); 
                alerta.showAndWait();
                App.setRoot("Makina_printzipala");
            } else {
                alert.setTitle("ERROR"); 
                alert.setHeaderText(null);
                alert.setContentText("Makina EZ da aldatu!"); 
                alert.showAndWait();
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
        App.setRoot("Makina_printzipala");
    }
}

