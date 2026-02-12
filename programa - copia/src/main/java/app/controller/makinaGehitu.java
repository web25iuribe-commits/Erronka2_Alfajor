package app.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;

import app.App;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

public class makinaGehitu {
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
    @FXML private TextField Instalazio_data;
    @FXML private TextField Deskribapena;
    @FXML private TextField Potentzia;

   @FXML
private void makinaGehitu() throws Exception {

    Alert alert = new Alert(AlertType.ERROR);
    Alert alerta = new Alert(AlertType.INFORMATION);

    String id = Id_makina.getText().trim(); 
    String izena = Izena.getText().trim(); 
    String instalazio_data = Instalazio_data.getText().trim(); 
    String deskribapena = Deskribapena.getText().trim(); 
    String potentziaStr = Potentzia.getText().trim();

    // Mostrar en consola
    System.out.println("Id_makina: " + id); 
    System.out.println("Izena: " + izena); 
    System.out.println("Instalazio_data: " + instalazio_data); 
    System.out.println("Deskribapena: " + deskribapena); 
    System.out.println("Potentzia: " + potentziaStr);

    // 1) Datu hutsak 
    if (id.isEmpty() || izena.isEmpty() || instalazio_data.isEmpty() ||
        deskribapena.isEmpty() || potentziaStr.isEmpty()) {

        alert.setTitle("ERROREA"); 
        alert.setHeaderText(null); 
        alert.setContentText("ERROREA: Datu guztiak bete behar dira."); 
        alert.showAndWait();
        return;
    }
    // 2) Luzerak
    if (id.length() > 4) {
        alert.setTitle("ERROREA"); 
        alert.setHeaderText(null); 
        alert.setContentText("Id-a ezin da 4 karaktere baino gehiago izan"); 
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

    if (instalazio_data.length() > 20) {
        alert.setTitle("ERROREA"); 
        alert.setHeaderText(null); 
        alert.setContentText("Instalazio datak 20 karaktere baino gehiago ditu."); 
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

    // 3) Potentzia zenbakia dela eta ez dela negatiboa
    int potentziaInt;
    try {
        potentziaInt = Integer.parseInt(potentziaStr);
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
        System.out.println("Konexioa: " + (cn != null && !cn.isClosed()));
        String sql = "INSERT INTO MAKINA (Id_makina, Izena, Instalazio_data, Deskribapena, Potentzia) VALUES (?, ?, ?, ?, ?)"; 
     
        PreparedStatement ps = cn.prepareStatement(sql);  
        ps.setString(1, id); 
        ps.setString(2, izena); 
        ps.setString(3, instalazio_data); 
        ps.setString(4, deskribapena); 
        ps.setInt(5, potentziaInt); 

        int rowsAffected = ps.executeUpdate();    

        if (rowsAffected > 0) {
        alerta.setTitle("GEHITUTA!"); 
        alerta.setHeaderText(null);
        alerta.setContentText("Makina gehitu da datu-basean!"); 
        alerta.showAndWait();
        App.setRoot("Makina_printzipala");
        } else {
            alert.setTitle("ERROREA"); 
            alert.setHeaderText(null);
            alert.setContentText("Makina ez da gehitu.");
        }

        ps.close();
        cn.close();
        System.out.println("Konexioa itxi da.");

    }catch (Exception e) { 
        alert.setTitle("ERROREA"); 
        alert.setHeaderText(null);
        alert.setContentText("Errorea datu-basera konektatzean"); 
        e.printStackTrace();
        }
    }
     @FXML 
private void Bueltatu() throws Exception { 
    App.setRoot("Makina_printzipala"); 
}
}