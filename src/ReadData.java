import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ReadData {
    public static void main(String[] args) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            if (connection != null) {
                System.out.println("Connected to the database. Retrieving flight data...");

                Statement statement = connection.createStatement();
                String query = "SELECT * FROM Flight";

                ResultSet resultSet = statement.executeQuery(query);

                while (resultSet.next()) {
                    int id = resultSet.getInt("flight_id");
                    String flightNumber = resultSet.getString("flight_number");
                    String departureCity = resultSet.getString("departure_city");
                    String arrivalCity = resultSet.getString("arrival_city");
                    String departureTime = resultSet.getString("departure_time");

                    System.out.printf("Flight ID: %d | Flight Number: %s | Departure: %s | Arrival: %s | Time: %s%n",
                            id, flightNumber, departureCity, arrivalCity, departureTime);
                }

            } else {
                System.out.println("Failed to connect to the database.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
