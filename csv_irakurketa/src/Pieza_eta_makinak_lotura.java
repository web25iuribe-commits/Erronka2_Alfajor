import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Pieza_eta_makinak_lotura {

    public static void main(String[] args) {

        String fitxategia = "csv/pieza_eta_makinak_lotura.csv";
        String lerroa;

        try (BufferedReader br = new BufferedReader(new FileReader(fitxategia))) {

            System.out.println("--- PIEZA ETA MAKINAK LOTURA ---");

            // Si quieres saltar la primera línea (cabecera), descomenta la siguiente línea:
            // br.readLine(); 

            while ((lerroa = br.readLine()) != null) {
                
                String[] datuak = lerroa.split(",");

                // Verificamos que haya datos suficientes para evitar errores
                if (datuak.length >= 2) {
                    // Imprimimos simple: PIEZA_ID | MAKINA_ID
                    System.out.println(datuak[0] + " | " + datuak[1]);
                }
            }

        } catch (IOException e) {
            System.out.println("Errorea fitxategia irakurtzean: " + e.getMessage());
        }
    }
}