import java.sql.SQLException;
import java.util.*;

class Pair{
    int busNo;
    int seats;
    Pair(int busNo,int seats){this.busNo=busNo;this.seats=seats;}

}
class Booking implements UserInterface {


    private String nameOfUser;
    private static int ticketId;
    private static Map<Integer,Pair>mp;
    private static final Scanner in;
    static{
        ticketId=23413;
        mp=new HashMap<>();
       in=new Scanner(System.in);
    }
    private int busNo;
    private int totalCost;
    private int seats;

    private List<Integer>seatNo;
   private boolean AC;
   BusBook busbook;
   Booking(){
       super();
   }
   Booking(List<Bus>bus,List<Booking>bookingList){
       super();
       seatNo=new ArrayList<>();
       busbook=new BusBook();
       bookTickets(bus,bookingList);
   }
   public void bookTickets(List<Bus>bus,List<Booking>bookingList){

       System.out.println("Enter Name: ");
       this.nameOfUser=in.next();
       System.out.println("Enter no Seats");
       setSeats(in.nextInt());
       System.out.println("Enter AC need true or false");
       setAC(in.nextBoolean());
       if(isAvailable(bus)){
//           bookingList.add(this);
           try{
               busbook.bookTicket(this);
           }catch(Exception e){
               System.out.println("cannot insert at booking DB");
//               System.out.println(e);
           }

           try{
               busbook.generateTicket();
           }
           catch(Exception e){
               System.out.println("cannot able generate ticket,server error!");
           }
//           generateTicket();
       }
       else{
           System.out.println("Cannot book,The seats are unavailable!");
       }
   }
    public static int getTicketId() {
        return ticketId;
    }

    public static void setTicketId(int ticketId) {
        Booking.ticketId = ticketId;
    }
    public void getBusDetails(List<Bus>bus) {

        Bus.getBusDetails(bus);
    }
    public String getNameOfUser() {
        return nameOfUser;
    }

    public void setNameOfUser(String nameOfUser) {
        this.nameOfUser = nameOfUser;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    public int getBusNo() {
        return busNo;
    }

    public List<Integer> getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(int a) {
        seatNo.add(a);
    }

    public void setBusNo(int busNo) {
        this.busNo = busNo;
    }

    private boolean isAvailable(List<Bus>bus){
//        for(Bus b:bus){
//           if(b.getCapacity()>=this.seats){
//               this.busNo=b.getName();
//               b.setCapacity(b.getCapacity()-this.seats);
//               int no=b.getSeatNo();
//               seatNo=new ArrayList<>();
//               ticketId++;
//               mp.put(ticketId,new Pair(this.busNo,this.seats));
//               for(int i=0;i<seats;i++)seatNo.add(no++);
//               setTotalCost(b.getPricePerTicket()*seats);
//               b.setSeatNo(b.getSeatNo()+seats);
//
//               b.setProfitPerBus(b.getProfitPerBus()+totalCost);
//               Bus.setTotalEarnings(Bus.getTotalEarnings()+totalCost);
//               return true;
//           }
//       }
        try{
            return busbook.BusBookIfAvailable(this);
        }catch(Exception e){
//            System.out.println(e);
            return false;

        }

   }

    private void generateTicket(){
        System.out.println("-------------------- Ticket --------------------");
        System.out.println("TicketID: "+ticketId);
        System.out.println("Your name: "+this.nameOfUser);
        System.out.println("Bus no: "+this.busNo);
        System.out.println("Total seats: "+this.seats);
        System.out.println("Total Cost: "+this.totalCost);
        System.out.println("Seat no: "+this.seatNo);
        System.out.println("AC: "+this.AC);
        System.out.println("---   Happy Journey!!!  ---");
        System.out.println("---------------------------------------------");
    }

    public static void cancelBusTickets(int bookingId,List<Bus>bus,List<Booking>bookingList){
       while(!mp.containsKey(bookingId)){
           System.out.println("Invalid Booking Id");
           bookingId=in.nextInt();
       }
       for(Bus b:bus){
           if(b.getName()==mp.get(bookingId).busNo){
               b.setCapacity(b.getCapacity()+mp.get(bookingId).seats);
               System.out.println("Cancellation success!");
               System.out.println();
               break;
           }
       }
    }
    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public boolean isAC() {
        return AC;
    }

    public void setAC(boolean AC) {
        this.AC = AC;
    }
}
