package app;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*
 JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    
    

@Override
public void start(Stage stage) throws IOException {
    scene = new Scene(loadFXML("Pieza_printzipala"), 640, 480); 
    stage.setScene(scene);
    stage.setTitle("Pieza Printzipala");
    stage.show();
}
    /*public void start(Stage Pieza_gehitu) throws IOException {
        scene = new Scene(loadFXML("Pieza_gehitu"), 640, 480);
        scene = new Scene(loadFXML("Pieza_printzipala"), 640, 480);
        Pieza_gehitu.setScene(scene);
        Pieza_gehitu.setTitle("Pieza Gehitu");
        Pieza_gehitu.show();
    }
    /*public void PiezaPrintzipala(Stage pieza_printzipala) throws IOException {
        scene = new Scene(loadFXML("pieza_printzipala"), 640, 480);
        pieza_printzipala.setScene(scene);
        pieza_printzipala.setTitle("Pieza Printzipala");
        pieza_printzipala.show();
    }*/

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}