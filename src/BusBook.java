import java.sql.*;
import java.util.ArrayList;

public class BusBook extends BusDAO {
    public boolean BusBookIfAvailable(Booking obj) throws SQLException {
        String query = "select * from bus";
        Statement st = DatabaseConnect.getStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            if (rs.getInt("capacity") >= obj.getSeats()) {
                obj.setBusNo(rs.getInt("id"));
                Booking.setTicketId(Booking.getTicketId() + 1);
                int no = rs.getInt("seatNo");
                obj.setSeatNo(no);
                for (int i = 0; i < obj.getSeats(); i++) obj.setSeatNo(no++);
                obj.setTotalCost(rs.getInt("PricePerTicket") * obj.getSeats());
                String q1 = "Update bus set seatNo=?  where id=?";
                try (Connection conn = DatabaseConnect.getCon(); PreparedStatement pstmt = conn.prepareStatement(q1)) {
                    // Set parameters for the prepared statement
                    pstmt.setInt(1, rs.getInt("seatNo") + obj.getSeats());
                    pstmt.setInt(2, rs.getInt("id"));

                    // Execute the update query
                    int rowsAffected = pstmt.executeUpdate();

                    if (rowsAffected > 0) {
                        // Update the total earnings if the update was successful
                        int newTotalEarnings = Bus.getTotalEarnings() + obj.getTotalCost();
                        Bus.setTotalEarnings(newTotalEarnings);

                        // Update the totalEarnings column in the bus table
                        String updateTotalEarningsQuery = "UPDATE bus SET totalEarnings = ? WHERE id = ?";
                        try (PreparedStatement updateTotalEarningsStmt = conn.prepareStatement(updateTotalEarningsQuery)) {
                            updateTotalEarningsStmt.setInt(1, newTotalEarnings);
                            updateTotalEarningsStmt.setInt(2, rs.getInt("id"));
                            updateTotalEarningsStmt.executeUpdate();
                        }

                        System.out.println("Seat number updated successfully and total earnings updated.");
                    } else {
                        System.out.println("No rows updated. Perhaps the ID was not found.");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }
        return true;
    }

}
