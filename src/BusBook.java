import java.sql.*;
import java.util.ArrayList;

public class BusBook extends BusDAO {
    private int bus_no;
    private String name;
    private String tickets;
    private int totalCost;
    public int getBus_no() {
        return bus_no;
    }

    public String getTickets() {
        return tickets;
    }

    public int getTotalCost() {
        return totalCost;
    }
    public boolean BusBookIfAvailable(Booking obj) throws SQLException {
        // Initialize tickets variable to an empty string
        this.tickets = "";

        String query = "SELECT * FROM bus";
        Statement st = DatabaseConnect.getStatement();
        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            if (rs.getInt("capacity") >= obj.getSeats()) {
                this.name = obj.getNameOfUser();
                obj.setBusNo(rs.getInt("id"));
                this.bus_no = obj.getBusNo();
                Booking.setTicketId(Booking.getTicketId() + 1);

                // Update seat numbers in the Booking object
                int startSeatNo = rs.getInt("seatNo");
                obj.setSeatNo(startSeatNo);

                // Generate ticket numbers and update tickets variable
                for (int i = 0; i < obj.getSeats(); i++) {
                    this.tickets += (startSeatNo + i) + " ";
                }

                obj.setTotalCost(rs.getInt("PricePerTicket") * obj.getSeats());

                // Prepare and execute update queries
                String updateSeatsQuery = "UPDATE bus SET seatNo = ?, capacity = ? WHERE id = ?";
                try (Connection conn = DatabaseConnect.getCon(); PreparedStatement pstmt = conn.prepareStatement(updateSeatsQuery)) {
                    pstmt.setInt(1, startSeatNo + obj.getSeats());
                    pstmt.setInt(2, rs.getInt("capacity") - obj.getSeats());
                    pstmt.setInt(3, rs.getInt("id"));
                    int rowsAffected = pstmt.executeUpdate();
                    if (rowsAffected > 0) {
                        // Update total earnings
                        int newTotalEarnings = Bus.getTotalEarnings() + obj.getTotalCost();
                        Bus.setTotalEarnings(newTotalEarnings);

                        // Update totalEarnings column in the bus table
                        String updateTotalEarningsQuery = "UPDATE bus SET totalEarnings = ? WHERE id = ?";
                        try (PreparedStatement updateTotalEarningsStmt = conn.prepareStatement(updateTotalEarningsQuery)) {
                            updateTotalEarningsStmt.setInt(1, newTotalEarnings);
                            updateTotalEarningsStmt.setInt(2, rs.getInt("id"));
                            updateTotalEarningsStmt.executeUpdate();
                        }
                        int updateProfitPerBus=rs.getInt("profitPerBus")+obj.getTotalCost();
                        String updateProfitPerBusQuery = "UPDATE bus SET profitPerBus = ? WHERE id = ?";
                        try (PreparedStatement updateTotalEarningsStmt = conn.prepareStatement(updateProfitPerBusQuery)) {
                            updateTotalEarningsStmt.setInt(1, updateProfitPerBus);
                            updateTotalEarningsStmt.setInt(2, rs.getInt("id"));
                            updateTotalEarningsStmt.executeUpdate();
                        }
                        System.out.println("Seat numbers updated successfully. Total earnings updated.");
                    } else {
                        System.out.println("Failed to update seat numbers. Perhaps the ID was not found.");
                    }
                    return true;
                } catch (Exception e) {
//                    System.out.println("Error updating seat numbers: " + e.getMessage());
                    return false;
                }
            }
        }
        DatabaseConnect.close();
        return false;
    }

    public void bookTicket(Booking booking) throws SQLException{
        String query="insert into booking (nameOfUser,busNo,seats,totalCost,seatNo) values(?,?,?,?,?)";
        try (Connection connection = DatabaseConnect.getCon();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Set values for parameters
            preparedStatement.setString(1, booking.getNameOfUser());
            preparedStatement.setInt(2, booking.getBusNo());
            preparedStatement.setInt(3, booking.getSeats());
            preparedStatement.setInt(4, booking.getTotalCost());
            preparedStatement.setString(5, this.getTickets());

            // Execute the query
            int rowsAffected = preparedStatement.executeUpdate();

            // Check if insertion was successful
            if (rowsAffected > 0) {
                System.out.println("Data inserted into bookingDB successfully.");
            } else {
                System.out.println("Failed to insert into booking DB");
            }

        } catch (Exception e) {
//            e.printStackTrace();
        }
    }
    public void generateTicket()throws SQLException{
        String query="select * from booking";
        Statement st=DatabaseConnect.getStatement();
        ResultSet rs=st.executeQuery(query);

        while(rs.next()){
            if(rs.getString("nameOfUser").equals(this.name)){
                System.out.println();
                System.out.println("---------------------------------------------------");
                System.out.println("Ticket ID: "+rs.getInt("ticketId"));
                System.out.println("Name of  Customer: "+ this.name);
                System.out.println("Total Cost: "+ rs.getInt("totalCost"));
                System.out.println("Bus no: "+rs.getInt("busNo"));
                System.out.println("Seat No: [ "+rs.getString("seatNo")+" ]");
                System.out.println("************** Happy journey ! **************");
                System.out.println("---------------------------------------------------");
                System.out.println();
                break;
            }
        }
        DatabaseConnect.close();
    }

}
//
// this.busNo=b.getName();
//         seatNo=new ArrayList<>();
//create table booking(ticketId INT DEFAULT 23413 PRIMARY KEY AUTOINCREMENT,nameOfUser varchar(30),busNo int,seats int,totalCost int,seatNo varchar(255));
