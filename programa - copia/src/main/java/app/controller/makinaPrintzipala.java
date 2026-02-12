package app.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import app.App;
import app.model.makina;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class makinaPrintzipala {
    
    @FXML private void makinakMenu() throws Exception { 
        App.setRoot("Makina_printzipala"); 
    }
    @FXML private void erabiltzaileakMenu() throws Exception { 
        App.setRoot("Erabiltzailea_printzipala"); 
    }
    @FXML private void piezakMenu() throws Exception { 
        App.setRoot("Pieza_printzipala"); 
    }
    @FXML private void menuaMenu() throws Exception { 
        App.setRoot("menua (1)"); 
    }
    @FXML private void makinaGehitu() throws Exception {
        App.setRoot("Makina_gehitu");
    }
    @FXML private void makinaEzabatu() throws Exception {
        App.setRoot("Makina_ezabatu");
    }
    @FXML private void makinaAldatu() throws Exception {
        App.setRoot("Makina_aldatu");
    }


    @FXML private TableView<makina> makinaTable; 
    @FXML private TableColumn<makina, String> idMakinaColumn; 
    @FXML private TableColumn<makina, String> izenaColumn; 
    @FXML private TableColumn<makina, String> deskribapenaColumn;
    @FXML private TableColumn<makina, String> potentziaColumn; 
    @FXML private TableColumn<makina, String> instalazioDataColumn;

    
    @FXML
    public void initialize() {

        idMakinaColumn.setCellValueFactory(new PropertyValueFactory<>("Id_makina"));
        izenaColumn.setCellValueFactory(new PropertyValueFactory<>("Izena"));
        instalazioDataColumn.setCellValueFactory(new PropertyValueFactory<>("Instalazio_data"));
        deskribapenaColumn.setCellValueFactory(new PropertyValueFactory<>("Deskribapena"));
        potentziaColumn.setCellValueFactory(new PropertyValueFactory<>("Potentzia"));

        kargatuDatuak();
    }
    public void kargatuDatuak() {
        ObservableList<makina> lista = FXCollections.observableArrayList();
         try (Connection conn = new DBKonexioa().konektatu()) { 
            String sql = "SELECT * FROM MAKINA"; 
            PreparedStatement stmt = conn.prepareStatement(sql); 
            ResultSet rs = stmt.executeQuery(); 
            while (rs.next()) { 
                lista.add(new makina( 
                    rs.getString("Id_makina"),
                    rs.getString("Izena"), 
                    rs.getString("Instalazio_data"), 
                    rs.getString("Deskribapena"), 
                    rs.getInt("Potentzia")
                    )); 
                } 
                makinaTable.setItems(lista); 
            } catch (Exception e) { 
                e.printStackTrace(); 
            } 
    }

}
