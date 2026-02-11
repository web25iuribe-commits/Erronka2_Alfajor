package app.controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

import app.App;
import javafx.fxml.FXML;
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
 
    String id = id_erabiltzailea.getText().trim();
    String izena = izenaErabiltzailea.getText().trim();
    String abizena = abizenaErabiltzailea.getText().trim();
    String helbidea = helbideaErabiltzailea.getText().trim();
    String email = emailErabiltzailea.getText().trim();
    String NAN = NANErabiltzailea.getText().trim();
    String postaKodea = postaKodeaErabiltzailea.getText().trim();

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
    if (id.isEmpty() || izena.isEmpty() || abizena.isEmpty() || helbidea.isEmpty()
            || email.isEmpty() || NAN.isEmpty() || postaKodea.isEmpty()
            || jaiotzeDataErabiltzailea.getValue() == null
            || altaDataErabiltzailea.getValue() == null) {
        System.out.println("ERROREA: Datu guztiak bete behar dira.");
        return;
    }

    // 2) Luzerak
    if (id.length() > 4) { System.out.println("ERROREA: ID-ak 4 karaktere baino gehiago ditu."); return; }
    if (izena.length() > 15) { System.out.println("ERROREA: Izena ezin da 15 karaktere baino gehiago izan."); return; }
    if (abizena.length() > 20) { System.out.println("ERROREA: Abizena ezin da 20 karaktere baino gehiago izan."); return; }
    if (helbidea.length() > 40) { System.out.println("ERROREA: Helbidea ezin da 40 karaktere baino gehiago izan."); return; }
    if (email.length() > 40) { System.out.println("ERROREA: Email-ak 40 karaktere baino gehiago ditu."); return; }
    if (NAN.length() > 9) { System.out.println("ERROREA: NAN-ak 9 karaktere baino gehiago ditu."); return; }
    if (postaKodea.length() > 5) { System.out.println("ERROREA: Posta kodeak 5 karaktere baino gehiago ditu."); return; }

    // 3) Fechen ordena
    if (jaiotzeDataErabiltzailea.getValue().isAfter(altaDataErabiltzailea.getValue())) {
        System.out.println("ERROREA: Jaiotze data ezin da alta data baino geroago izan.");
        return;
    }

    // 4) PostaKodea zenbakia
    int postaKodeaInt;
    try {
        postaKodeaInt = Integer.parseInt(postaKodea);
        if (postaKodeaInt < 0) {
            System.out.println("ERROREA: Posta kodea ezin da negatiboa izan.");
            return;
        }
    } catch (NumberFormatException e) {
        System.out.println("ERROREA: Posta kodea zenbakia izan behar da.");
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
        ps.setInt(6, postaKodeaInt);
        ps.setString(7, email);
        ps.setDate(8, Date.valueOf(jaiotzeDataErabiltzailea.getValue()));
        ps.setDate(9, Date.valueOf(altaDataErabiltzailea.getValue()));

        int rowsAffected = ps.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("Erabiltzailea gehitu da datu-basean!");
            App.setRoot("Erabiltzailea_printzipala");
        } else {
            System.out.println("Errorea: Erabiltzailea ez da gehitu (rowsAffected = 0).");
        }

        ps.close();
        cn.close();
        System.out.println("Konexioa itxi da.");

    } catch (Exception e) {
        System.out.println(">>> SQL ERROREA:");
        e.printStackTrace();
    }
}
}