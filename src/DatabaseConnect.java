import javax.net.ssl.SSLException;
import java.sql.*;

public class DatabaseConnect {

    private static String url = "jdbc:mysql://localhost:3306/bus_reservation_system";
    private static Connection con;

    // Method to get database connection
    public static Connection getCon() {
        try {
            if (con == null || con.isClosed()) {
                con = DriverManager.getConnection(url, "admin", "1234");
            }
        } catch (Exception e) {
            System.out.println("Error establishing database connection: " + e.getMessage());
        }
        return con;
    }

    // Method to get statement from connection
    public static Statement getStatement() {
        Statement statement = null;
        try {
            statement = getCon().createStatement();
        } catch (Exception e) {
            System.out.println("Error creating statement: " + e.getMessage());
        }
        return statement;
    }

    // Method to close connection
    public static void close() {
//        try {
//            if (con != null && !con.isClosed()) {
//                con.close();
//                System.out.println("Database connection closed successfully.");
//            }
//        } catch (SQLException e) {
//            if (e.getCause() instanceof SSLException) {
//                System.out.println("SSL Exception occurred while closing database connection: " + e.getCause().getMessage());
//            } else {
//                System.out.println("SQL Exception occurred while closing database connection: " + e.getMessage());
//            }
//        } catch (Exception e) {
//            System.out.println("Error occurred while closing database connection: " + e.getMessage());
//        }
//    }


    }}
