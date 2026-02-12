package app.controller;

import java.sql.Connection;
import java.sql.SQLException;

import app.App;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

public class makinaEzabatu {
    @FXML 
    private void menuaMenu() throws Exception { 
        App.setRoot("menua (1)"); 
    }
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

    @FXML private TextField id_makina;


    @FXML
private void makinaEzabatu() throws Exception {

    Alert alert = new Alert(AlertType.ERROR);
    Alert alerta = new Alert(AlertType.INFORMATION);

    String id = id_makina.getText().trim();

    if (id.isEmpty()) {
        alert.setTitle("ERROREA"); 
        alert.setHeaderText(null);
        alert.setContentText("ID-a hutsik dago."); 
        alert.showAndWait();
        App.setRoot("Makina_printzipala");
        return;
    }

    if (id.length() > 4) {
        alert.setTitle("ERROREA"); 
        alert.setHeaderText(null);
        alert.setContentText(" ID-ak 4 karaktere baino gehiago ditu."); 
        alert.showAndWait();
        return;
    }
    DBKonexioa konex = new DBKonexioa();
    Connection cn = null;

    try {
        cn = konex.konektatu();

        if (cn != null && !cn.isClosed()) {

            String sqlCheck = "SELECT COUNT(*) FROM MAKINA WHERE Id_makina = ?"; 
            java.sql.PreparedStatement psCheck = cn.prepareStatement(sqlCheck); 
            psCheck.setString(1, id); 
            java.sql.ResultSet rs = psCheck.executeQuery(); 
            rs.next(); 
            if (rs.getInt(1) == 0) {
                alert.setHeaderText(null); 
                alert.setContentText("Ezin da ezabatu: makina hori ez dago datu-basean."); 
                alert.showAndWait(); 
                return; 
            } 
            String sql = "DELETE FROM MAKINA WHERE Id_makina = ?"; 
            java.sql.PreparedStatement ps = cn.prepareStatement(sql); 
            ps.setString(1, id); 
            ps.executeUpdate(); 
            ps.close();

            cn.close();
        }     
        } catch (SQLException e) { 
            alert.setTitle("ERROREA"); 
            alert.setHeaderText(null); 
            alert.setContentText("Errorea datu-basera konektatzean."); 
            alert.showAndWait(); 
            return;
        }
        alerta.setTitle("EZABATUTA!");
        alerta.setHeaderText(null); 
        alerta.setContentText("Makina ezabatu da datu-basean!"); 
        App.setRoot("Makina_printzipala");
        alerta.showAndWait(); 
    }
     @FXML
    private void Bueltatu() throws Exception {
        App.setRoot("Makina_printzipala");
    }
}
