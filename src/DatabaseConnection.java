import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseConnection {

    private static final String URL = "jdbc:postgresql://localhost:5432/airline_reservation_system";
    private static final String USER = "postgres";
    private static final String PASSWORD = "AbdaNazar2006";

    private static final Logger LOGGER = Logger.getLogger(DatabaseConnection.class.getName());

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Database connection failed", e);
            return null;
        }
    }
}