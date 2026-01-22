import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Erabiltzaileak {

    public static void main(String[] args) {

        String fitxategia = "csv_irakurketa/csv/erabiltzaileak.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(fitxategia))) {
            String lerroa;

            while ((lerroa = br.readLine()) != null) {
                String[] datuak = lerroa.split(",");

                System.out.println("Id_erabiltzaileak: " + datuak[0]);
                System.out.println("Izena: " + datuak[1]);
                System.out.println("Abizena1: " + datuak[2]);
                System.out.println("NAN: " + datuak[3]);
                System.out.println("Helbidea: " + datuak[4]);
                System.out.println("Posta kodea: " + datuak[5]);
                System.out.println("Email: " + datuak[6]);
                System.out.println("Jaiotza data: " + datuak[7]);
                System.out.println("Alta data: " + datuak[8]);
                System.out.println("-----------");
            }
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}