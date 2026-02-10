package app.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

import app.App;
import app.DBKonexioa;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class erabiltzaileGehitu {

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

@FXML private javafx.scene.control.TextField IdErabiltzailea;
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

    String id = IdErabiltzailea.getText();
    String izena = izenaErabiltzailea.getText();
    String abizena = abizenaErabiltzailea.getText();
    String helbidea = helbideaErabiltzailea.getText();
    String email = emailErabiltzailea.getText();
    String NAN = NANErabiltzailea.getText();
    String postaKodea = postaKodeaErabiltzailea.getText();
    DatePicker jaiotzeData = jaiotzeDataErabiltzailea;
    DatePicker altaData = altaDataErabiltzailea;
    
    System.out.println("ID: " + id);
    System.out.println("Izena: " + izena);
    System.out.println("Abizena: " + abizena);
    System.out.println("Helbidea: " + helbidea);
    System.out.println("Email: " + email);
    System.out.println("NAN: " + NAN);
    System.out.println("Jaiotze data: " + jaiotzeData);
    System.out.println("Alta data: " + altaData);

    if ( id.isEmpty() || izena.isEmpty() || abizena.isEmpty() || helbidea.isEmpty() || 
    email.isEmpty() || NAN.isEmpty() || postaKodea.isEmpty() || jaiotzeData == null || altaData == null ) {
            System.out.println("ERROREA: Datu guztiak bete behar dira.");
            return;
        }

     // ATRIBUTU BAKOITZAREN KARAKTERE MAXIMOAK KONTROLATU.
        if (id.length() > 4) {
        System.out.println("ERROREA: ID-ak 4 karaktere baino gehiago ditu.");
        return;
        }
        if (izena.length() > 20) {
        System.out.println("ERROREA: Izena ezin da 20 karaktere baino gehiago izan");
        return;
            }
        if (abizena.length() > 30) {
            System.out.println("ERROREA: Abizena ezin da 30 karaktere baino gehiago izan");
        return;
        }
        if (helbidea.length() > 100) {
            System.out.println("ERROREA: Helbidea ezin da 100 karaktere baino gehiago izan");
        return;
        }
        if (email.length() > 50) {
            System.out.println("ERROREA: Email-ak 50 karaktere baino gehiago ditu.");
            return;
        }
        if (NAN.length() > 9) {
            System.out.println("ERROREA: NAN-ak 9 karaktere baino gehiago ditu.");
            return;
        }   
        if (postaKodea.length() > 5) {
            System.out.println("ERROREA: Posta kodeak 5 karaktere baino gehiago ditu.");
            return;
        }
        if (jaiotzeData.getValue().isAfter(altaData.getValue())) {
            System.out.println("ERROREA: Jaiotze data alta data baino geroago dago.");
            return;
        }
        try {
            int postaKodea1 = Integer.parseInt(postaKodea);
            if (postaKodea1 < 0) {
                System.out.println("ERROREA: Posta kodea ezin da negatiboa izan.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("ERROREA: Zenbakia izan behar da.");
            return;
        }

        DBKonexioa konex = new DBKonexioa();
        Connection cn = null;

        try {
            cn = konex.konektatu();
            if (cn != null && !cn.isClosed()) {
            
            String sql = "INSERT INTO ERABILTZAILEA (Id_erabiltzailea, Izena, Abizena1, J_data, Email, Alta_data, Helbidea, NAN, PostaKodea) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, id);
            ps.setString(2, izena);
            ps.setString(3, abizena);
            ps.setString(4, helbidea);
            ps.setString(5, email);
            ps.setString(6, NAN);
            ps.setString(7, postaKodea);
            ps.setDate(8, Date.valueOf(jaiotzeData.getValue()));
            ps.setDate(9, Date.valueOf(altaData.getValue()));

            int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Erabiltzailea gehitu da datu-basean!");
                    App.setRoot("Erabiltzailea_printzipala");
                } else {
                    System.out.println("Errorea: Erabiltzailea ez da gehitu.");
                }
                ps.close();
                cn.close();
                System.out.println("Konexioa itxi da.");
            }
        } catch (Exception e) {
            System.out.println("Errorea datu-basera konektatzean");
            e.printStackTrace();
        }
    }
        @FXML
    private void Bueltatu() throws IOException {
        App.setRoot("Erabiltzailea_printzipala");

    }
}