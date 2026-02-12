import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Piezak {

    public static void main(String[] args) {
        String lerroa;
        Connection cn = null;
        PreparedStatement agindua = null; // Definir fuera para poder cerrar en finally

        try (BufferedReader br = new BufferedReader(new FileReader("csv/piezak.csv"))) {
            DBKonexioa konex = new DBKonexioa();
            cn = konex.konektatu();

            br.readLine(); // Goiburua saltatzeko

            String kontsulta = "INSERT INTO PIEZA (Id_pieza, Izena, Deskribapena, Pisua, Prezioa, Stock) VALUES (?,?,?,?,?,?)";
            agindua = cn.prepareStatement(kontsulta);

            cn.createStatement().executeUpdate("ALTER TABLE PIEZA MODIFY Izena VARCHAR(100)");

            while ((lerroa = br.readLine()) != null) {
                String[] datuak = lerroa.split(",");

                // *** ARREGLO ***
                // Este IF evita el error. Solo entra si hay al menos 6 columnas de datos.
                if (datuak.length >= 6) { 

                    System.out.println(datuak[0] + " | " + datuak[1] + " | " + datuak[2] + " | " + datuak[3] + " | " + datuak[4]+ " | " + datuak[5]);

                    agindua.setString(1, datuak[0]);
                    agindua.setString(2, datuak[1]);
                    agindua.setString(3, datuak[2]);
                    agindua.setString(4, datuak[3]);
                    agindua.setInt(5, Integer.parseInt(datuak[4]));
                    agindua.setInt(6, Integer.parseInt(datuak[5]));
                    agindua.executeUpdate();
                }
            }

        } catch (SQLException e) {
            System.out.println("Errorea datuak sartzean SQLn: " + e.getMessage());
            e.printStackTrace();

        } catch (IOException e) {
            System.out.println("Errorea fitxategia irakurtzean.");
        } finally {
            try {
                if (agindua != null) agindua.close(); // Cerramos PreparedStatement
                if (cn != null) {
                    cn.close();
                    System.out.println("Konexioa itxi da.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}