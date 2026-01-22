import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Erabiltzaileak_eta_makinak {

	public static void main(String[] args) {

		String fitxategia = "csv_irakurketa/csv/erabiltzaileak_eta_makinak.csv";

		try (BufferedReader br = new BufferedReader(new FileReader(fitxategia))) {
			String lerroa;

			while ((lerroa = br.readLine()) != null) {
				String[] datuak = lerroa.split(",");
				if(datuak.length == 4) {
					System.out.println("Id_erabiltzailea: " + datuak[0]);
					System.out.println("Id makina: " + datuak[1]);
					System.out.println("Hasiera data: " + datuak[2]);
					System.out.println("Amaiera data: " + datuak[3]);
					System.out.println("-----------");
				}else {
					System.out.println("Id_erabiltzailea: " + datuak[0]);
					System.out.println("Id makina: " + datuak[1]);
					System.out.println("Hasiera data: " + datuak[2]);
					System.out.println("Hasiera data: NULL");
					System.out.println("-----------");
				}

				
			}
			br.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}