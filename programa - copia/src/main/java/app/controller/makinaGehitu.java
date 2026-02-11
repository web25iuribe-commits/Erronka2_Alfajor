package app.controller;

import app.App;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class makinaGehitu {
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
    @FXML private TextField Id_makina;
    @FXML private TextField Izena;
    @FXML private TextField Instalazio_data;
    @FXML private TextField Deskribapena;
    @FXML private TextField Potentzia;

   @FXML
   private void makinaGehitu() throws Exception {

    String id = Id_makina.getText().trim(); 
    String izena = Izena.getText().trim(); 
    String instalazio_data = Instalazio_data.getText().trim(); 
    String deskribapena = Deskribapena.getText().trim(); 
    int potentzia = Integer.parseInt(Potentzia.getText().trim()); 

    System.out.println("Id_makina: " + id); 
    System.out.println("Izena: " + izena); 
    System.out.println("Instalazio_data: " + instalazio_data); 
    System.out.println("Deskribapena: " + deskribapena); 
    System.out.println("Potentzia: " + potentzia);

    }
}
