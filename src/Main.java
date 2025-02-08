import java.sql.*;

class PassengerDB {
    private static final String URL = "jdbc:postgresql://localhost:5432/airline_reservation_system";
    private static final String USER = "postgres";
    private static final String PASSWORD = "AbdaNazar2006";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            if (conn != null) {
                System.out.println("Connected to database.");
                readData(conn);
                insertData(conn, "John Doe", "AB12345", "USA", "1990-01-01");
                updateData(conn, 1, "Jane Doe");
                deleteData(conn, 5);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Read Data
    public static void readData(Connection conn) throws SQLException {
        String query = "SELECT * FROM Passenger";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                System.out.printf("ID: %d, Name: %s, Passport: %s%n",
                        rs.getInt("passenger_id"), rs.getString("name"), rs.getString("passport_number"));
            }
        }
    }

    // Insert Data
    public static void insertData(Connection conn, String name, String passport, String nationality, String dob) throws SQLException {
        String query = "INSERT INTO Passenger (name, passport_number, nationality, date_of_birth) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, name);
            pstmt.setString(2, passport);
            pstmt.setString(3, nationality);
            pstmt.setDate(4, Date.valueOf(dob));
            pstmt.executeUpdate();
            System.out.println("Passenger inserted.");
        }
    }

    // Update Data
    public static void updateData(Connection conn, int id, String newName) throws SQLException {
        String query = "UPDATE Passenger SET name = ? WHERE passenger_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, newName);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
            System.out.println("Passenger updated.");
        }
    }

    // Delete Data
    public static void deleteData(Connection conn, int id) throws SQLException {
        String query = "DELETE FROM Passenger WHERE passenger_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Passenger deleted.");
        }
    }
}
