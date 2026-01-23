import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Piezak {

    public static void main(String[] args) {

        String fitxategia = "csv_irakurketa/csv/piezak.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(fitxategia))) {
            String lerroa;

            while ((lerroa = br.readLine()) != null) {
                String[] datuak = lerroa.split(",");

                System.out.println("Id_pieza: " + datuak[0]);
                System.out.println("Izena: " + datuak[1]);
                System.out.println("Deskribapena: " + datuak[2]);
                System.out.println("Pisua(gr): " + datuak[3]);
                System.out.println("Prezioa: " + datuak[4]);
                System.out.println("Stock: " + datuak[5]);
                System.out.println("-----------");
            }
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}