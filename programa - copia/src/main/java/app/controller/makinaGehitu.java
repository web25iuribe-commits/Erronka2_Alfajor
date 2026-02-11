package app.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;

import app.App;
import javafx.fxml.FXML;
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

        System.out.println("ERROREA: Datu guztiak bete behar dira.");
        return;
    }

    // 2) Luzerak
    if (id.length() > 4) {
        System.out.println("ERROREA: ID-ak 4 karaktere baino gehiago ditu.");
        return;
    }

    if (izena.length() > 30) {
        System.out.println("ERROREA: Izena ezin da 30 karaktere baino gehiago izan.");
        return;
    }

    if (instalazio_data.length() > 20) {
        System.out.println("ERROREA: Instalazio datak 20 karaktere baino gehiago ditu.");
        return;
    }

    if (deskribapena.length() > 150) {
        System.out.println("ERROREA: Deskribapenak 150 karaktere baino gehiago ditu.");
        return;
    }

    // 3) Potentzia zenbakia dela eta ez dela negatiboa
    int potentziaInt;
    try {
        potentziaInt = Integer.parseInt(potentziaStr);
        if (potentziaInt < 0) {
            System.out.println("ERROREA: Potentzia ezin da negatiboa izan.");
            return;
        }
    } catch (NumberFormatException e) {
        System.out.println("ERROREA: Potentzia zenbakia izan behar da.");
        return;
    }

    String sql = "INSERT INTO MAKINA (Id_makina, Izena, Instalazio_data, Deskribapena, Potentzia) VALUES (?, ?, ?, ?, ?)"; 
     try (Connection conn = new DBKonexioa().konektatu(); 
     PreparedStatement stmt = conn.prepareStatement(sql)) { 
        stmt.setString(1, id); 
        stmt.setString(2, izena); 
        stmt.setString(3, instalazio_data); 
        stmt.setString(4, deskribapena); 
        stmt.setInt(5, potentziaInt); 
        stmt.executeUpdate(); 
        System.out.println("Makina ondo gehitu da!"); 
    } catch (Exception e) { 
        System.out.println("ERROREA: Ezin izan da makina gehitu."); 
        e.printStackTrace();
    }
}
    @FXML 
private void Bueltatu() throws Exception { 
    App.setRoot("Makina_printzipala"); 
}
}