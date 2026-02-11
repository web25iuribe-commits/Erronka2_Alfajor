package app.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import app.App;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class erabiltzaileAldatu {
      
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
    @FXML private TextField izena;
    @FXML private TextField abizena1;
    @FXML private TextField NAN;
    @FXML private TextField helbidea;
    @FXML private TextField postaKodea;
    @FXML private TextField email;
    @FXML private DatePicker jaiotze_data;
    @FXML private DatePicker alta_data;

    @FXML
    private void erabiltzaileaAldatuBotoia() throws Exception {    

        String id_erab = id_erabiltzailea.getText(); 
        String izenaErab = izena.getText(); 
        String abizena1Erab = abizena1.getText();
        String NANErab = NAN.getText();
        String helbideaErab = helbidea.getText();
        int postaKodeaErab = Integer.parseInt(postaKodea.getText());
        String emailErab = email.getText();
        Date jaiotzeDataErab = Date.valueOf(jaiotze_data.getValue());
        Date altaDataErab = Date.valueOf(alta_data.getValue());


        System.out.println("ID: " + id_erab);
        System.out.println("Izena: " + izenaErab);
        System.out.println("Abizena: " + abizena1Erab); 
        System.out.println("NAN: " + NANErab);
        System.out.println("Helbidea: " + helbideaErab);
        System.out.println("Posta kodea: " + postaKodeaErab);
        System.out.println("Email: " + emailErab);
        System.out.println("Jaiotze data: " + jaiotzeDataErab);
        System.out.println("Alta data: " + altaDataErab);

        if (id_erab.length() > 4) {
            System.out.println("ERROREA: ID-ak 4 karaktere baino gehiago ditu.");
            return;
        }
        if (NANErab.length() != 9) {
            System.out.println("ERROREA: NAN-ak 9 karaktere izan behar ditu.");
            return;
        }
        if (helbideaErab.length() > 150) {
            System.out.println("ERROREA: Helbideak 150 karaktere baino gehiago ditu.");
            return;
        }
        if (emailErab.length() > 150) {
            System.out.println("ERROREA: Email-ak 150 karaktere baino gehiago ditu.");
            return;
        }
        if (postaKodeaErab < 0 || postaKodeaErab > 99999) {
            System.out.println("ERROREA: Posta kodeak 0 eta 99999 arteko balioa izan behar du.");
            return;
        }
        if (jaiotzeDataErab.after(altaDataErab)) {
            System.out.println("ERROREA: Jaiotze data alta datatik aurrerago dago.");
            return;
        }  
        if(emailErab.isEmpty() || !emailErab.contains("@") || !emailErab.contains(".")) {
            System.out.println("ERROREA: Email-ak ez du formatu egokia.");
            return;
        }
        
        DBKonexioa konex = new DBKonexioa();
        Connection cn = null;

        try {
            cn = konex.konektatu();
            if (cn != null && !cn.isClosed()) {

                String sql = "UPDATE ERABILTZAILEA SET Izena=?, Abizena1=?, NAN=?, Helbidea=?, PostaKodea=?, Email=?, J_data=?, Alta_data=? WHERE Id_erabiltzailea=?";
                PreparedStatement ps = cn.prepareStatement(sql);
               // ps.setString(1, id);
                ps.setString(1, izenaErab);
                ps.setString(2, abizena1Erab);
                ps.setString(3, NANErab);
                ps.setString(4, helbideaErab);
                ps.setInt(5, postaKodeaErab);
                ps.setString(6, emailErab);
                ps.setDate(7, jaiotzeDataErab);
                ps.setDate(8, altaDataErab);
                ps.setString(9, id_erab);
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Erabiltzailea aldatu da datu-basean!");
                    App.setRoot("Erabiltzailea_printzipala");
                } else {
                    System.out.println("Errorea: Erabiltzailea ez da aldatu.");
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

