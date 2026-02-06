import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Makinak {

    public static void main(String[] args) {
        String lerroa;
        Connection cn = null;

        try (BufferedReader br = new BufferedReader(new FileReader("csv/makinak.csv"))) {
            DBKonexioa konex = new DBKonexioa();
            cn = konex.konektatu();

            br.readLine(); // Goiburua saltatzeko

            String kontsulta = "INSERT INTO MAKINA (Id_makina, Izena, Deskribapena, Potentzia, Instalazio_data) VALUES (?,?,?,?,?)";            PreparedStatement agindua = cn.prepareStatement(kontsulta);

            System.out.println("Konexioa zuzena: " + !cn.isClosed());

            cn.createStatement().executeUpdate("ALTER TABLE MAKINA MODIFY Instalazio_data VARCHAR(30)");

            
            // != null esan nahi du, ejekutatu egingo dela irakurtzeko lerroak dauden bitartean
            // .csv-ko lerroak irakurtzen ditu amaierararte
            while ((lerroa = br.readLine()) != null) {
                String[] datuak = lerroa.split(",");
                System.out.println(datuak[0] + " - " + datuak[1] + " - " + datuak[2] + " - " + datuak[3] + " - " + datuak[4]);

            agindua.setString(1, datuak[0]); // Id_makina 
            agindua.setString(2, datuak[1]); // Izena
            agindua.setString(3, datuak[2]); // Deskribapena
            agindua.setString(4, datuak[3]); // Potentzia
            agindua.setString(5, datuak[4]); // Instalazio_data      


                agindua.executeUpdate();
            }

        } catch (SQLException e) {
            System.out.println("Errorea datuak sartzean.");
            e.printStackTrace();

        } catch (IOException e) {
            System.out.println("Errorea fitxategia irakurtzean.");
        } finally {
            if (cn != null) {
                try {
                    cn.close();
                    System.out.println("Konexioa itxi da.");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

