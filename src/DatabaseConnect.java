import java.sql.*;

public class DatabaseConnect {

    private static String url = "jdbc:mysql://localhost:3306/bus_reservation_system";
       public static Connection getCon() throws SQLException{
           return DriverManager.getConnection(url,"admin","1234");
       }
       public static Statement getStatement() throws SQLException{
           return getCon().createStatement();
       }


}
