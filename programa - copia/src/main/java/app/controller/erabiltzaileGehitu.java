package app.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

import app.App;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class erabiltzaileGehitu {

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

@FXML private TextField id_erabiltzailea;
@FXML private TextField izenaErabiltzailea;
@FXML private TextField abizenaErabiltzailea;
@FXML private TextField helbideaErabiltzailea;
@FXML private TextField emailErabiltzailea;
@FXML private TextField NANErabiltzailea;
@FXML private TextField postaKodeaErabiltzailea;
@FXML private DatePicker jaiotzeDataErabiltzailea;
@FXML private DatePicker altaDataErabiltzailea;

 @FXML
private void erabiltzaileGehitu() throws Exception {

    Alert alert = new Alert(AlertType.ERROR);
    Alert alerta = new Alert(AlertType.INFORMATION);

 
    String id = id_erabiltzailea.getText().trim();
    String izena = izenaErabiltzailea.getText().trim();
    String abizena = abizenaErabiltzailea.getText().trim();
    String helbidea = helbideaErabiltzailea.getText().trim();
    String email = emailErabiltzailea.getText().trim();
    String NAN = NANErabiltzailea.getText().trim();
    int postaKodea = Integer.parseInt(postaKodeaErabiltzailea.getText());

    System.out.println("=== SARRERAK ===");
    System.out.println("ID: " + id);
    System.out.println("Izena: " + izena);
    System.out.println("Abizena: " + abizena);
    System.out.println("Helbidea: " + helbidea);
    System.out.println("Email: " + email);
    System.out.println("NAN: " + NAN);
    System.out.println("PostaKodea: " + postaKodea);
    System.out.println("JaiotzeData: " + jaiotzeDataErabiltzailea.getValue());
    System.out.println("AltaData: " + altaDataErabiltzailea.getValue());

    // 1) Datu hutsak
    String postaKodeaStr = postaKodeaErabiltzailea.getText().trim();
    if (id.isEmpty() || izena.isEmpty() || abizena.isEmpty() || helbidea.isEmpty()
            || email.isEmpty() || NAN.isEmpty() || postaKodeaStr.isEmpty()
            || jaiotzeDataErabiltzailea.getValue() == null
            || altaDataErabiltzailea.getValue() == null) {
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
    if (izena.length() > 15) {        
        alert.setTitle("ERROREA"); 
        alert.setHeaderText(null); 
        alert.setContentText("Izena ezin da 15 karaktere baino gehiago izan."); 
        alert.showAndWait(); 
        return;
    }
    if (abizena.length() > 20) {        
        alert.setTitle("ERROREA"); 
        alert.setHeaderText(null); 
        alert.setContentText("Abizena ezin da 20 karaktere baino gehiago izan."); 
        return;
    }
    if (helbidea.length() > 40) {        
        alert.setTitle("ERROREA"); 
        alert.setHeaderText(null); 
        alert.setContentText("Helbidea ezin da 40 karaktere baino gehiago izan."); 
        alert.showAndWait(); 
        return; 
    }
    if (email.length() > 40) {        
        alert.setTitle("ERROREA"); 
        alert.setHeaderText(null); 
        alert.setContentText("Email-ak 40 karaktere baino gehiago ditu."); 
        alert.showAndWait();
        return; 
    }
     if(email.isEmpty() || !email.contains("@") || !email.contains(".")) {
            alert.setTitle("ERROREA"); 
            alert.setHeaderText(null);
            alert.setContentText(" Email-ak ez du formatu egokia."); 
            alert.showAndWait();
            return;
        }
    if (NAN.length() != 9) {        
        alert.setTitle("ERROREA"); 
        alert.setHeaderText(null); 
        alert.setContentText("NAN-ak 9 karaktere izan behar ditu."); 
        alert.showAndWait();
        return; 
    }
    if (postaKodea < 0 || postaKodea > 99999) {
            alert.setTitle("ERROREA"); 
            alert.setHeaderText(null);
            alert.setContentText("Posta kodeak 0 eta 99999 arteko balioa izan behar du."); 
            alert.showAndWait();
            return;
        }

    // 3) Fechen ordena
    if (jaiotzeDataErabiltzailea.getValue().isAfter(altaDataErabiltzailea.getValue())) {
        alert.setTitle("ERROREA"); 
        alert.setHeaderText(null);
        alert.setContentText("Jaiotze data ezin da alta data baino geroago izan."); 
        alert.showAndWait();
        return;
    }

    DBKonexioa konex = new DBKonexioa();
    Connection cn = null;

    try {
        cn = konex.konektatu();
        System.out.println("Konexioa: " + (cn != null && !cn.isClosed()));

        String sql = "INSERT INTO ERABILTZAILEA "
                   + "(Id_erabiltzailea, Izena, Abizena1, NAN, Helbidea, PostaKodea, Email, J_data, Alta_data) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement ps = cn.prepareStatement(sql);
        ps.setString(1, id);
        ps.setString(2, izena);
        ps.setString(3, abizena);
        ps.setString(4, NAN);
        ps.setString(5, helbidea);
        ps.setInt(6, postaKodea);
        ps.setString(7, email);
        ps.setDate(8, Date.valueOf(jaiotzeDataErabiltzailea.getValue()));
        ps.setDate(9, Date.valueOf(altaDataErabiltzailea.getValue()));

        int rowsAffected = ps.executeUpdate();

        if (rowsAffected > 0) {
        alerta.setTitle("GEHITUTA!"); 
        alerta.setHeaderText(null);
        alerta.setContentText("Erabiltzailea gehitu da datu-basean!"); 
        alerta.showAndWait();
        App.setRoot("Erabiltzailea_printzipala");
        } else {
            alert.setTitle("ERROREA"); 
            alert.setHeaderText(null);
            alert.setContentText(" Erabiltzailea ez da gehitu.");
        }

        ps.close();
        cn.close();
        System.out.println("Konexioa itxi da.");

    } catch (Exception e) {
        alert.setTitle("ERROREA"); 
        alert.setHeaderText(null);
        alert.setContentText("Errorea datu-basera konektatzean"); 
        e.printStackTrace();
    }
}
 @FXML
    private void Bueltatu() throws IOException {
        App.setRoot("Erabiltzailea_printzipala");
    }
}