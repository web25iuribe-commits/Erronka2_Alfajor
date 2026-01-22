import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Piezak_eta_pieza_motak {

	public static void main(String[] args) {

		String fitxategia = "csv_irakurketa/csv/piezak_eta_pieza_motak.csv";

		try (BufferedReader br = new BufferedReader(new FileReader(fitxategia))) {
			String lerroa;

			while ((lerroa = br.readLine()) != null) {
				String[] datuak = lerroa.split(",");

				System.out.println("Id pieza: " + datuak[0]);
				System.out.println("Id pieza mota: " + datuak[1]);
				System.out.println("-----------");
			}
			br.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}