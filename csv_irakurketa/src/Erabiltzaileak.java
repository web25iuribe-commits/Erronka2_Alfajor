import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement; 
import java.sql.SQLException; 

public class Erabiltzaileak { 
    
    public static void main(String[] args) { 
        String lerroa; 
        Connection cn = null; 
        
        try (BufferedReader br = new BufferedReader(new FileReader("csv/erabiltzaileak.csv"))) { 
            DBKonexioa konex = new DBKonexioa(); 
            cn = konex.konektatu(); 
            System.out.println("Konexioa zuzena: " + !cn.isClosed()); // Saltar cabecera 
            br.readLine(); 
            
            String kontsulta = "INSERT INTO ERABILTZAILEA " + "(id_erabiltzailea, izena, abizena1, NAN, helbidea, postaKodea, email, J_data, Alta_data) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement agindua = cn.prepareStatement(kontsulta); 
            while ((lerroa = br.readLine()) != null) { 
                String[] datuak = lerroa.split(","); 
                System.out.println( datuak[0] + " - " + datuak[1] + " - " + datuak[2] + " - " + datuak[3] + " - " + datuak[4] + " - " 
                + datuak[5] + " - " + datuak[6] + " - " + datuak[7] + " - " + datuak[8] ); // ASIGNACIÓN CORRECTA DE PARÁMETROS 
                
                agindua.setString(1, datuak[0]); // id
                agindua.setString(2, datuak[1]); // izena
                agindua.setString(3, datuak[2]); // abizena1
                agindua.setString(4, datuak[3]); // NAN
                agindua.setString(5, datuak[4]); // helbidea
                agindua.setInt(6, Integer.parseInt(datuak[5])); // postaKodea
                agindua.setString(7, datuak[6]); // email
                agindua.setString(8, datuak[7]); // J_data
                agindua.setString(9, datuak[8]); // Alta_data

                agindua.executeUpdate();
             } 
                agindua.close(); 
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
                    } catch (SQLException e) { e.printStackTrace(); 

                    } 
                } 
            } 
        } 
    }