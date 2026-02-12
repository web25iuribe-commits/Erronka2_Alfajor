package app.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

import app.App;
import app.model.erabiltzailea;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class erabiltzailePrintzipala {
     // MENÚAK
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
    @FXML private void erabiltzaileGehitu() throws Exception { 
        App.setRoot("Erabiltzailea_gehitu"); 
    }
    @FXML private void erabiltzaileEzabatu() throws Exception { 
        App.setRoot("Erabiltzailea_ezabatu"); 
    }
    @FXML private void erabiltzaileAldatu() throws Exception { 
        App.setRoot("Erabiltzailea_aldatu"); 
    }

    @FXML private TableView<erabiltzailea> taulaErabiltzaileak; 
    @FXML private TableColumn<erabiltzailea, String> colId; 
    @FXML private TableColumn<erabiltzailea, String> colIzena; 
    @FXML private TableColumn<erabiltzailea, String> colAbizena;
    @FXML private TableColumn<erabiltzailea, String> colNan; 
    @FXML private TableColumn<erabiltzailea, String> colHelbidea;
    @FXML private TableColumn<erabiltzailea, Integer> colPostaKodea; 
    @FXML private TableColumn<erabiltzailea, String> colEmail; 
    @FXML private TableColumn<erabiltzailea, LocalDate> colJaiotzeData;
    @FXML private TableColumn<erabiltzailea, LocalDate> colAltaData;

    @FXML
    public void initialize() {

        colId.setCellValueFactory(new PropertyValueFactory<>("id_erabiltzailea"));
        colIzena.setCellValueFactory(new PropertyValueFactory<>("izena"));
        colAbizena.setCellValueFactory(new PropertyValueFactory<>("abizena1"));
        colNan.setCellValueFactory(new PropertyValueFactory<>("NAN"));
        colHelbidea.setCellValueFactory(new PropertyValueFactory<>("helbidea"));
        colPostaKodea.setCellValueFactory(new PropertyValueFactory<>("postaKodea"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colJaiotzeData.setCellValueFactory(new PropertyValueFactory<>("jaiotze_data"));
        colAltaData.setCellValueFactory(new PropertyValueFactory<>("alta_data"));

        kargatuDatuak();
    }

    public void kargatuDatuak() { 
        ObservableList<erabiltzailea> lista = FXCollections.observableArrayList();
         try (Connection conn = new DBKonexioa().konektatu()) { 
            String sql = "SELECT * FROM ERABILTZAILEA"; 
            PreparedStatement stmt = conn.prepareStatement(sql); 
            ResultSet rs = stmt.executeQuery(); 
            while (rs.next()) { 
                LocalDate jaiotze = rs.getDate("J_data") != null ? rs.getDate("J_data").toLocalDate() : null; 
                LocalDate alta = rs.getDate("Alta_data") != null ? rs.getDate("Alta_data").toLocalDate() : null; 
                lista.add(new erabiltzailea( 
                    rs.getString("Id_erabiltzailea"), 
                    rs.getString("Izena"), 
                    rs.getString("Abizena1"), 
                    rs.getString("NAN"), 
                    rs.getString("Helbidea"), 
                    rs.getInt("PostaKodea"), 
                    rs.getString("Email"), 
                    jaiotze, 
                    alta )); 
                } 
                taulaErabiltzaileak.setItems(lista); 
            } catch (Exception e) { 
                e.printStackTrace(); 
            } 
     }
}
