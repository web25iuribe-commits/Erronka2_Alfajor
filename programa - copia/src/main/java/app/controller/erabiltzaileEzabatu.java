package app.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import app.App;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

public class erabiltzaileEzabatu {
    
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

   @FXML
private void erabiltzaileEzabatu() throws IOException {

    Alert alert = new Alert(AlertType.ERROR);
    Alert alerta = new Alert(AlertType.INFORMATION);

    String id = id_erabiltzailea.getText();

    if (id.isEmpty()) {
        alert.setTitle("ERROREA"); 
        alert.setHeaderText(null);
        alert.setContentText("ID-a ezin da hutsik egon."); 
        alert.showAndWait();
        return;
    }

    if (id.length() > 4) {
        alert.setTitle("ERROREA"); 
        alert.setHeaderText(null);
        alert.setContentText("ID-ak ezin du 4 karaktere baino gehiago izan."); 
        alert.showAndWait();
        return;
    }

    DBKonexioa konex = new DBKonexioa();
    Connection cn = null;

    try {
        cn = konex.konektatu();

        if (cn != null && !cn.isClosed()) {

            String sqlCheck = "SELECT COUNT(*) FROM ERABILTZAILEA WHERE Id_erabiltzailea = ?";
            java.sql.PreparedStatement psCheck = cn.prepareStatement(sqlCheck);
            psCheck.setString(1, id);
            java.sql.ResultSet rs = psCheck.executeQuery();
            rs.next();

            if (rs.getInt(1) == 0) {
                alert.setTitle("ERROREA");
                alert.setHeaderText(null);
                alert.setContentText("Ezin da ezabatu: ID hau ez dago datu-basean.");
                App.setRoot("Erabiltzailea_printzipala");
                alert.showAndWait();
                return;
            }

            String sql = "DELETE FROM ERABILTZAILEA WHERE Id_erabiltzailea = ?";
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
    alerta.setContentText("Erabiltzailea ezabatu da datu-basean!"); 
    App.setRoot("Erabiltzailea_printzipala");
    alerta.showAndWait();

}
@FXML
    private void Bueltatu() throws IOException {
        App.setRoot("Erabiltzailea_printzipala");
    }

}