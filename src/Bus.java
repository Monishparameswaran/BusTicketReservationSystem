import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
public class Bus implements AdminInterface{
    private int id ;
    private int seatNo=333;
    private static int totCapacity;
    private  int capacity;
    private  int pricePerTicket;
    private boolean AC;
    private int profitPerBus;
    private static int totalEarnings;
    private static final Scanner in;
    static{
        in=new Scanner(System.in);
    }
    Bus(List<Bus>buses) throws SQLException{
       super();
       addBus(buses);
    }
    public  void addBus(List<Bus>buses) {
        System.out.print("Please enter Id of new Bus: ");
        setName(in.nextInt());
        System.out.println();
        System.out.print("Enter capacity of Bus: ");
        setCapacity(in.nextInt());
        setTotCapacity(getCapacity());
        System.out.println();
        System.out.print("is it AC : (True or False) :");
        setAC(in.nextBoolean());
        System.out.println();
        System.out.print("Enter price per ticket: ");
        setPricePerTicket(in.nextInt());
//        buses.add(this);
        BusDAO busDAO=new BusDAO();
        try{
            if(busDAO.insert(this)) System.out.println("Successfully added the bus");
            else System.out.println("Cannot add the bus");
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public int getProfitPerBus() {
        return profitPerBus;
    }

    public void setProfitPerBus(int profitPerBus) {
        this.profitPerBus = profitPerBus;
    }

    public static void removeBus(List<Bus>buses){
        System.out.print("Enter bus id: ");
        int id=in.nextInt();
//        for(Bus b:buses){
//            if(b.getName()==id){
//                buses.remove(b);
//                System.out.println("Bus removed Successfully");
//                break;
//            }
//        }
        try{
            if(BusDAO.remove(id)) System.out.println("Successfully removed the bus");
            else System.out.println("Cannot remove bus details,internal error!");
        }catch(SQLException e){
            System.out.println(e);
        }
    }

    public static int getTotCapacity() {
        return totCapacity;
    }

    public static void setTotCapacity(int totCapacity) {
        Bus.totCapacity = totCapacity;
    }

    public static int getTotalEarnings() {
        return totalEarnings;
    }

    public static void setTotalEarnings(int totalEarnings) {
        Bus.totalEarnings = totalEarnings;
    }

    public static void generateReport(List<Bus>buses) {
        System.out.println("-----------------Bus REPORT----------------------------");
//        for(Bus b:buses){
//            b.setProfitPerBus((getTotCapacity()-b.getCapacity())*b.getPricePerTicket());
//            setTotalEarnings(getTotalEarnings()+b.getProfitPerBus());
//            System.out.println("--------------------------------------------");
//            System.out.println("Bus no: "+b.getName());
//            System.out.println("Earnings: "+b.getProfitPerBus());
//            System.out.println("Empty Seats: "+b.getCapacity());
//            System.out.println("-------------------------------------------");
//
//        }
        try{
            System.out.println("-------Report For Admin-------------");
            BusDAO.getBusDetailsForAdmin();
            System.out.println("-------------------------------------");
        }catch(SQLException e){
            System.out.println(e);
        }

        System.out.println("Total Earnings: "+ Bus.getTotalEarnings());
        System.out.println();
        System.out.println("--------------------------------------------------");
    }

    public int getName() {
        return id;
    }

    public int getPricePerTicket() {
        return pricePerTicket;
    }

    public void setPricePerTicket(int pricePerTicket) {
        this.pricePerTicket = pricePerTicket;
    }

    public void setName(int id){
        this.id =id;
    }
    public  int getCapacity() {
        return capacity;
    }

    public int getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(int seatNo) {
        this.seatNo = seatNo;
    }

    public  void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean getAC() {
        return AC;
    }

    public void setAC(boolean AC) {
        this.AC = AC;
    }
    public static void getBusDetails(List<Bus>bus){
        System.out.println("----------------------Bus Details for Customer----------------------------------------");
//        for(Bus b:bus){
//            System.out.println("Bus no: "+b.getName());
//            System.out.println("Capacity: "+b.getCapacity());
//            System.out.println("PricePerTicket: "+b.getPricePerTicket());
//            System.out.println("AC: "+b.getAC());
//            System.out.println();
//        }
        try{
            BusDAO.getBusDetailsForCustomer();
        }
        catch(Exception e){
            System.out.println(e);
        }

        System.out.println("-------------------------------------------------------");
    }


}
