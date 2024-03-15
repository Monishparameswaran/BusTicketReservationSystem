import java.util.List;
public interface UserInterface {
    static void getBusDetails(List<Bus> bus){}
    void bookTickets(List<Bus>bus,List<Booking>bookingList);
   static void cancelBusTickets(int bookingId,List<Bus>bus,List<Booking>bookingList){}
}
