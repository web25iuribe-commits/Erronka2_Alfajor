// Importaciones necesarias para trabajar con JDBC
import java.sql.Connection;      // Datu baserekin lotzeko "komunikazio kanala" da.   // Konexioa egiteaz arduratzen da.
import java.sql.DriverManager;
import java.sql.SQLException;
    // SKL e
public class DBKonexioa {
    

    private static final String URL = "jdbc:mysql://localhost:3306/alfajordb";

    // datu-basearen erabiltzailea
    private static final String USER = "root";

    // datu-basera konektatzeko pasahitza
    private static final String PASS = "123456789";


    //konektatu() --> metodoaren izena da.
    // Connection --> metodoak Connection motako objektu bat bueltatuko du.

    public Connection konektatu() throws SQLException {

    	// throws erabiliz, salbuespena, metodoa deitu duen programara hedatzen da.
    	// DriverManager.getConnection() metodoak bere barruan SQLException jaurtitzen du (throw) salbuespena gertatzen bada.
        return  DriverManager.getConnection(URL, USER, PASS);
    }
}

