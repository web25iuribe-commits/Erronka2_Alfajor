import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Piezak_eta_pieza_motak {

    public static void main(String[] args) {
        
        String fitxategia = "csv/piezak_eta_pieza_motak.csv"; 
        String lerroa;

        try (BufferedReader br = new BufferedReader(new FileReader(fitxategia))) {

			System.out.println("--- PIEZA ETA PIEZA MOTAK LOTURA ---");


            while ((lerroa = br.readLine()) != null) {
                // Dividimos la línea por las comas
                String[] datuak = lerroa.split(",");

                // Imprimimos simple: ID - NOMBRE - PRECIO - STOCK
                // Usamos " | " para separar visualmente sin complicaciones
                System.out.println(datuak[0] + " | " + datuak[1]);
            }

        } catch (IOException e) {
            System.out.println("Error leyendo el archivo: " + e.getMessage());
        }
    }
}