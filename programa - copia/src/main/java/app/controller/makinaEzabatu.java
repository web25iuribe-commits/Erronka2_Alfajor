package app.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import app.App;
import javafx.fxml.FXML;
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

    String id = id_makina.getText().trim();

    System.out.println("ID-a ezabatu da: " + id);

    if (id.isEmpty()) {
        System.out.println("ERROREA: ID bete behar da.");
        return;
    }

    if (id.length() > 4) {
        System.out.println("ERROREA: ID-ak 4 karaktere baino gehiago ditu.");
        return;
    }

    DBKonexioa konex = new DBKonexioa();

    try (Connection cn = konex.konektatu()) {

        if (cn != null && !cn.isClosed()) {

            String sql = "DELETE FROM MAKINA WHERE Id_makina = ?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, id);

            int rows = ps.executeUpdate();
            ps.close();

            if (rows > 0) {
                System.out.println("Makina ezabatu da!");
                App.setRoot("Makina_printzipala");

            } else {
                System.out.println("Ez da makina aurkitu.");
            }
        }

    } catch (SQLException e) {
        System.out.println("Errorea datu-basera konektatzean");
        e.printStackTrace();
    }
}
    @FXML
    private void Bueltatu() throws Exception {
        App.setRoot("Makina_printzipala");
    }
}
