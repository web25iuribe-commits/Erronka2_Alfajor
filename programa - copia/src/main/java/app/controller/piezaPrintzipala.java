package app.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import app.App;
import app.model.pieza;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class piezaPrintzipala {


        @FXML
    private void makinakMenu() throws Exception {
        App.setRoot("Makina_printzipala");
    }
    @FXML
    private void erabiltzaileakMenu() throws Exception {
        App.setRoot("Erabiltzailea_printzipala");
    }

    @FXML
    private void menuaMenu() throws Exception {    
        App.setRoot("menua (1)");
    }
    @FXML
    private void piezakMenu() throws Exception {
        App.setRoot("Pieza_printzipala");
    }

        @FXML
    private  void piezaGehitu() throws IOException {
        App.setRoot("Pieza_gehitu");
    }
        @FXML
    private void piezaEzabatu() throws IOException {
        App.setRoot("Pieza_ezabatu");
}
        @FXML
    private void piezaAldatu() throws IOException {
        App.setRoot("Pieza_aldatu");
}
  @FXML private TableView<pieza>piezaTableView; 
    @FXML private TableColumn<pieza, String> idPiezaColumn; 
    @FXML private TableColumn<pieza, String> izenaColumn; 
    @FXML private TableColumn<pieza, String> deskribapenaColumn;
    @FXML private TableColumn<pieza, String> pisuaColumn; 
    @FXML private TableColumn<pieza, Double> prezioaColumn; 
    @FXML private TableColumn<pieza, Integer> stockColumn;
    @FXML private TableColumn<pieza, String> idPiezaMotaColumn;

    

    @FXML
    public void initialize() {

        idPiezaColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        izenaColumn.setCellValueFactory(new PropertyValueFactory<>("izena"));
        deskribapenaColumn.setCellValueFactory(new PropertyValueFactory<>("deskribapena"));
        pisuaColumn.setCellValueFactory(new PropertyValueFactory<>("pisua"));
        prezioaColumn.setCellValueFactory(new PropertyValueFactory<>("prezioa"));
        stockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        idPiezaMotaColumn.setCellValueFactory(new PropertyValueFactory<>("id_pieza_Mota"));

        kargatuDatuak();

    }

    public void kargatuDatuak() {
        ObservableList<pieza> lista = FXCollections.observableArrayList();
         try (Connection conn = new DBKonexioa().konektatu()) { 
            String sql = "SELECT * FROM PIEZA"; 
            PreparedStatement stmt = conn.prepareStatement(sql); 
            ResultSet rs = stmt.executeQuery(); 
            while (rs.next()) { 
                lista.add(new pieza( 
                    rs.getString("Id_pieza"),
                    rs.getString("Izena"), 
                    rs.getString("Deskribapena"), 
                    rs.getString("Pisua"), 
                    rs.getDouble("Prezioa"), 
                    rs.getInt("Stock"),
                    rs.getString("Id_pieza_Mota")
                    )); 
                } 
                piezaTableView.setItems(lista); 
            } catch (Exception e) { 
                e.printStackTrace(); 
            } 
    }
}

