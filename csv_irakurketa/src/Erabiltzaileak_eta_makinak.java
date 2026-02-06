import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Erabiltzaileak_eta_makinak {

    public static void main(String[] args) {

        String lerroa;
        Connection cn = null;
        PreparedStatement agindua = null; // Lo sacamos fuera para poder cerrarlo en finally

        try (BufferedReader br = new BufferedReader(new FileReader("csv/erabiltzaileak_eta_makinak.csv"))) {
            DBKonexioa konex = new DBKonexioa();
            cn = konex.konektatu();

            br.readLine(); // goiburua saltatu

            // Asumimos que la tabla tiene 4 columnas: ID_USUARIO, ID_MAQUINA, FECHA_INICIO, FECHA_FIN
            String kontsulta = "INSERT INTO ERABILTZAILEA_MAKINA VALUES(?,?,?,?)";
            agindua = cn.prepareStatement(kontsulta);

            while ((lerroa = br.readLine()) != null) {
                String[] datuak = lerroa.split(",");

                // *** SOLUCIÓN DEL ERROR ***
                // Comprobamos que hay al menos 4 datos antes de intentar leer la posición 3.
                if (datuak.length >= 4) {
                    
                    System.out.println(datuak[0] + " | " + datuak[1] + " | " + datuak[2] + " | " + datuak[3]);

                    agindua.setString(1, datuak[0]); 
                    agindua.setString(2, datuak[1]); 
                    
                    agindua.setDate(3, java.sql.Date.valueOf(datuak[2]));
                
                    String amaiera = datuak[3].trim();
                    if (amaiera.isEmpty()) {        
                        agindua.setNull(4, java.sql.Types.DATE); 
                    } else {
                        agindua.setDate(4, java.sql.Date.valueOf(amaiera));
                    }

                    agindua.executeUpdate();
                }
            }

        } catch (IOException e) {
            System.out.println("Errorea fitxategia irakurtzean.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Errorea datuak sartzean SQLn.");
            e.printStackTrace();
        } finally {
            if (cn != null) {
                try {
                    if (agindua != null) agindua.close(); // Cerramos el PreparedStatement
                    cn.close();
                    System.out.println("Konexioa itxi da.");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}