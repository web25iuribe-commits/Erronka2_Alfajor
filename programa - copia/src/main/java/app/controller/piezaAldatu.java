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

public class piezaAldatu {

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

    @FXML private TextField id_pieza;
    @FXML private TextField Izena;
    @FXML private TextField Deskribapena;
    @FXML private TextField Pisua;
    @FXML private TextField Prezioa;
    @FXML private TextField Stock;

        @FXML
    private void piezaAldatuBotoia() throws IOException {

        Alert alert = new Alert(AlertType.ERROR);
        Alert alerta = new Alert(AlertType.INFORMATION);

        String id = id_pieza.getText(); 
        String izena = Izena.getText(); 
        String deskribapena = Deskribapena.getText(); 
        int prezioa = Integer.parseInt(Prezioa.getText()); 
        int stock = Integer.parseInt(Stock.getText()); 
        String pisua = Pisua.getText();

        System.out.println("ID: " + id);
        System.out.println("Izena: " + izena);
        System.out.println("Deskribapena: " + deskribapena);
        System.out.println("Prezioa: " + prezioa);
        System.out.println("Stock: " + stock);
        System.out.println("Pisua: " + pisua);


        if (id.isEmpty() || izena.isEmpty() || deskribapena.isEmpty() || 
        Prezioa.getText().trim().isEmpty() || Stock.getText().trim().isEmpty()
        || pisua.isEmpty()) {

            alert.setTitle("ERROREA"); 
            alert.setHeaderText(null); 
            alert.setContentText("ERROREA: Eremu guztiak bete behar dira."); 
            alert.showAndWait(); 
            return; 
        }
        if (id.length() > 4) { 
            alert.setTitle("ERROREA"); 
            alert.setHeaderText(null); 
            alert.setContentText("ERROREA: ID-ak ezin du 4 karaktere baino gehiago izan."); 
            alert.showAndWait(); 
            return; 
        } 
        if (izena.length() > 30) { 
            alert.setTitle("ERROREA"); 
            alert.setHeaderText(null); 
            alert.setContentText("ERROREA: Izenak ezin du 30 karaktere baino gehiago izan."); 
            alert.showAndWait(); 
            return; 
        } 
        if (deskribapena.length() > 150) { 
            alert.setTitle("ERROREA"); 
            alert.setHeaderText(null); 
            alert.setContentText("ERROREA: Deskribapenak ezin du 150 karaktere baino gehiago izan."); 
            alert.showAndWait(); 
            return; 
        } 
        if (pisua.length() > 10) {
            alert.setTitle("ERROREA"); 
            alert.setHeaderText(null); 
            alert.setContentText("ERROREA: Pisua ezin da 10 karaktere baino gehiago izan."); 
            alert.showAndWait(); 
            return;
        }
        try { 
            if (prezioa < 0) { 
            alert.setTitle("ERROREA"); 
            alert.setHeaderText(null); 
            alert.setContentText("ERROREA: Prezioa ezin da negatiboa izan."); 
            alert.showAndWait(); 
            return; 
        } 
    } catch (NumberFormatException e) { 
        alert.setTitle("ERROREA"); 
        alert.setHeaderText(null); 
        alert.setContentText("ERROREA: Prezioa zenbaki osoa izan behar da."); 
        alert.showAndWait(); 
        return; 
    } try { 
        if (stock < 0) { 
            alert.setTitle("ERROREA"); 
            alert.setHeaderText(null); 
            alert.setContentText("ERROREA: Stock-a ezin da negatiboa izan."); 
            alert.showAndWait(); 
            return; 
        } 
    } catch (NumberFormatException e) { 
        alert.setHeaderText(null); 
        alert.setContentText("ERROREA: Stock-a zenbaki osoa izan behar da."); 
        alert.showAndWait(); 
        return; 
    }

    DBKonexioa konex = new DBKonexioa();
    Connection cn = null;

    try {
        cn = konex.konektatu();
        if (cn != null && !cn.isClosed()) {
            String sql = "UPDATE PIEZA SET Izena=?, Deskribapena=?, Pisua=?, Prezioa=?, Stock=? WHERE Id_pieza=?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, izena);
            ps.setString(2, deskribapena);
            ps.setString(3, pisua);
            ps.setInt(4, prezioa);
            ps.setInt(5, stock);
            ps.setString(6, id);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                alerta.setTitle("ALDATUTA!"); 
                alerta.setHeaderText(null);
                alerta.setContentText("Pieza aldatu da datu-basean!"); 
                alerta.showAndWait();
                App.setRoot("Pieza_printzipala");
            } else {
                alert.setTitle("ERROR"); 
                alert.setHeaderText(null);
                alert.setContentText("Pieza EZ da aldatu!"); 
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
    App.setRoot("Pieza_printzipala");
}



}

