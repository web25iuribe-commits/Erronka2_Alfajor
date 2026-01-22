import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Makinak {

    public static void main(String[] args) {

        String fitxategia = "csv_irakurketa/csv/makinak.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(fitxategia))) {
            String lerroa;

            while ((lerroa = br.readLine()) != null) {
                String[] datuak = lerroa.split(",");

                System.out.println("Id_makina: " + datuak[0]);
                System.out.println("Izena: " + datuak[1]);
                System.out.println("Deskribapena: " + datuak[2]);
                System.out.println("Potentzia(kw): " + datuak[3]);
                System.out.println("Instalazio data: " + datuak[4]);
                System.out.println("-----------");
            }
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}