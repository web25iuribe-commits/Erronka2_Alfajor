import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Pieza_eta_makinak_lotura {

    public static void main(String[] args) {

        String fitxategia = "csv_irakurketa/csv/pieza_eta_makinak_lotura.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(fitxategia))) {
            String lerroa;

            while ((lerroa = br.readLine()) != null) {
                String[] datuak = lerroa.split(",");

                System.out.println("Id pieza: " + datuak[0]);
                System.out.println("Id makina: " + datuak[1]);
                System.out.println("-----------");
            }
			br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}