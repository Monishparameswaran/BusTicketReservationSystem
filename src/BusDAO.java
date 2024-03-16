import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;

public class BusDAO {
    public boolean insert(Bus obj) throws SQLException {
        Connection con=DatabaseConnect.getCon();
        String query="insert into bus (seatNo, totCapacity, capacity, AC, profitPerBus, totalEarnings,pricePerTicket) values (?, ?, ?, ?, ?, ?,?)";
        PreparedStatement ps = con.prepareStatement(query);

        ps.setInt(1, obj.getSeatNo());
        ps.setInt(2, Bus.getTotCapacity());
        ps.setInt(3, Bus.getTotCapacity());
        ps.setBoolean(4, obj.getAC());
        ps.setInt(5, obj.getProfitPerBus());
        ps.setInt(6, Bus.getTotalEarnings());
        ps.setInt(7,obj.getPricePerTicket());
        int rowsInserted = ps.executeUpdate();
        DatabaseConnect.close();
        if(rowsInserted>0)return true;
        return false;
    }
    public static boolean remove(int id) throws SQLException{
        String query="DELETE FROM bus where id="+id;
        Statement st=DatabaseConnect.getStatement();
        int rows=st.executeUpdate(query);
        if(rows>0)return true;
        return false;
    }
    public static void getBusDetailsForAdmin() throws SQLException{
        Statement st=DatabaseConnect.getStatement();
        ResultSet rs=st.executeQuery("select * from bus");
        while(rs.next()){

            System.out.println("------------------------------");
            System.out.println("Bus no: "+ rs.getInt("id"));
            System.out.println("Capacity of Bus: "+rs.getInt("totCapacity"));
            System.out.println("Empty Seats : "+rs.getInt("capacity"));
            System.out.println("Earning from Bus: "+(rs.getInt("profitPerBus")));
            System.out.println("------------------------------");
            System.out.println();
        }
        DatabaseConnect.close();
    }
    public static void getBusDetailsForCustomer() throws SQLException{
        Statement st=DatabaseConnect.getStatement();
        ResultSet rs=st.executeQuery("select * from bus");
        while(rs.next()){
            System.out.println("------------------------------");
            System.out.println("Bus no: "+ rs.getInt("id"));
            System.out.println("Capacity of Bus: "+rs.getInt("totCapacity"));
            System.out.println("Empty Seats : "+rs.getInt("capacity"));
            System.out.println("PricePerTicket: "+rs.getInt("pricePerTicket"));
            System.out.println("------------------------------");
            System.out.println();
        }
        DatabaseConnect.close();
    }

}
