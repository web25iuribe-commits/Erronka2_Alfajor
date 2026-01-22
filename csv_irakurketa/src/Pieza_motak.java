import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Pieza_motak {

    public static void main(String[] args) {

        String fitxategia = "csv_irakurketa/csv/pieza_motak.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(fitxategia))) {
            String lerroa;

            while ((lerroa = br.readLine()) != null) {
                String[] datuak = lerroa.split(",");

                System.out.println("Id pieza mota: " + datuak[0]);
                System.out.println("Izena: " + datuak[1]);
                System.out.println("-----------");
            }
			br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}