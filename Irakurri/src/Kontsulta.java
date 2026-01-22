import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException; 

public class Kontsulta {

    public static void main(String[] args) {

        DBKonexioa konex = new DBKonexioa();

        try {

            Connection cn = konex.konektatu();

            if (cn != null && !cn.isClosed()) {
                System.out.println("Komunikazio kanala irekita dago.");

               String kontsulta = "SELECT Id_pieza FROM PIEZA";
      
                PreparedStatement agindua = cn.prepareStatement(kontsulta);
       
                ResultSet emaitza = agindua.executeQuery();


                while (emaitza.next()) { 
                    String ID = emaitza.getString("Id_pieza");  
                    System.out.println(ID);
                }

                emaitza.close();

                cn.close();
                System.out.println("Konexioa itxi da.");
            }

        } catch (SQLException e) {
            System.out.println("Errorea kontsulta exekutatzean");
            e.printStackTrace();
        }
    }
}