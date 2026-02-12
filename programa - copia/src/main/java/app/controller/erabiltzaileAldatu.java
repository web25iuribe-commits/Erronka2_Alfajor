package app.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import app.App;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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

        Alert alert = new Alert(AlertType.ERROR);
        Alert alerta = new Alert(AlertType.INFORMATION);

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

         String postaKodeaStr = postaKodea.getText().trim();
        if (id_erab.isEmpty() || izenaErab.isEmpty() || abizena1Erab.isEmpty() || helbideaErab.isEmpty()
            || emailErab.isEmpty() || NANErab.isEmpty() || postaKodeaStr.isEmpty()
            || jaiotze_data.getValue() == null
            || alta_data.getValue() == null) {
            alert.setTitle("ERROREA"); 
            alert.setHeaderText(null); 
            alert.setContentText("ERROREA: Datu guztiak bete behar dira."); 
            alert.showAndWait();
            return;
        }
        if (id_erab.length() > 4) {
            alert.setTitle("ERROREA"); 
            alert.setHeaderText(null);
            alert.setContentText("ID-ak ezin du 4 karaktere baino gehiago izan."); 
            alert.showAndWait();
            return;
        }
        if (NANErab.length() != 9) {
            alert.setTitle("ERROREA"); 
            alert.setHeaderText(null);
            alert.setContentText("NAN-ak 9 karaktere izan behar ditu."); 
            alert.showAndWait();
            return;
        }
        if (helbideaErab.length() > 150) {
            alert.setTitle("ERROREA"); 
            alert.setHeaderText(null);
            alert.setContentText("Helbideak 150 karaktere baino gehiago ditu."); 
            alert.showAndWait();
            return;
        }
        if (emailErab.length() > 150) {
            alert.setTitle("ERROREA"); 
            alert.setHeaderText(null);
            alert.setContentText("Email-ak 150 karaktere baino gehiago ditu."); 
            alert.showAndWait();
            return;
        }
        if (postaKodeaErab < 0 || postaKodeaErab > 99999) {
            alert.setTitle("ERROREA"); 
            alert.setHeaderText(null);
            alert.setContentText("Posta kodeak 0 eta 99999 arteko balioa izan behar du."); 
            alert.showAndWait();
            return;
        }
        if (jaiotzeDataErab.after(altaDataErab)) {
            alert.setTitle("ERROREA"); 
            alert.setHeaderText(null);
            alert.setContentText("Jaiotze data alta datatik aurrerago dago."); 
            alert.showAndWait();
            return;
        }  
        if(emailErab.isEmpty() || !emailErab.contains("@") || !emailErab.contains(".")) {
            alert.setTitle("ERROREA"); 
            alert.setHeaderText(null);
            alert.setContentText(" Email-ak ez du formatu egokia."); 
            alert.showAndWait();
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
                    alerta.setTitle("ALDATUTA!"); 
                    alerta.setHeaderText(null); 
                    alerta.setContentText("Erabiltzailea aldatu da datu-basean!"); 
                    alerta.showAndWait();
                    App.setRoot("Erabiltzailea_printzipala");
                } else {
                    alert.setTitle("ERROREA"); 
                    alert.setHeaderText(null);
                    alert.setContentText(" Erabiltzailea ez da aldatu.");
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
        App.setRoot("Pieza_printzipala");
    }



}

