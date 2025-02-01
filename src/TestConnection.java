public class TestConnection {
    public static void main(String[] args) {
        if (DatabaseConnection.getConnection() != null) {
            System.out.println("Database connection successful!");
        } else {
            System.out.println("Failed to connect.");
        }
    }
}
