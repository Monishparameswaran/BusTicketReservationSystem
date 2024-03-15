// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class MyPair {
    String name;
    String pass;
    MyPair(String a,String b){
        this.name=a;
        this.pass=b;
    }
    @Override
    public boolean equals(Object obj){
        if(obj==null)return false;
        if(obj==this)return true;
        MyPair myobj=(MyPair)obj;
        return myobj.name.equals(this.name) && myobj.pass.equals(this.pass);
    }

}
public class Main {
    private static boolean validateADMIN(List<MyPair>adminDB){
        Scanner in=new Scanner(System.in);
        System.out.println();
        System.out.println("-------------Admin Login-------------");
        System.out.print("Enter Name: ");
        String name=in.next();
        System.out.print("Enter Password: ");
        String pass=in.next();
        System.out.println("-----------------------------------");
        System.out.println();
        if(!adminDB.contains(new MyPair(name,pass)))return false;
        return true;

    }

    public static void main(String[] args) throws SQLException {

        List<Bus>bus=new ArrayList<>();
        List<Booking>bookings=new ArrayList<>();
        List<MyPair>adminDB=new ArrayList<>();
        Scanner in=new Scanner(System.in);
        adminDB.add(new MyPair("Monish","1234"));
        int userOption=-2;
        while(true){
            System.out.println("enter 0 for Admin: ");
            System.out.println("enter 1 to getBusInfo: ");
            System.out.println("enter 2 to BookTickets: ");
            System.out.println("enter 3 to CancelTickets: ");

            System.out.println("enter -1 to Exit : ");
            try{
                userOption=in.nextInt();
            }
           catch(Exception e){
                System.out.println("Please enter Integer value , occured: ");
               System.out.println("Reenter value: ");
               in.next();
               in.next();
                userOption=in.nextInt();
           }
            if(userOption==0){
                if(validateADMIN(adminDB)){
                    System.out.println("Welcome Back Admin!");
                    while(true){

                        System.out.println();
                        System.out.println("========ADMIN===========");
                        System.out.println("enter 0 to Add Buses");
                        System.out.println("enter 1 to remove Buses");
                        System.out.println("enter 2 to Generate report of Buses");
                        System.out.println("enter -1 to Exit");

                        int adminOption=in.nextInt();
                        if(adminOption==0)new Bus(bus);
                        if(adminOption==1)Bus.removeBus(bus);
                        if(adminOption==2)Bus.generateReport(bus);
                        if (adminOption==-1)break;
                    }
                }
                else{
                    System.out.println("Invalid Admin Credentials");
                }
            }
            if(userOption==1)Bus.getBusDetails(bus);
            if(userOption==2)new Booking(bus,bookings);
            if(userOption==3){
                System.out.print("Please enter ticket ID: ");
                Booking.cancelBusTickets(in.nextInt(),bus,bookings);
            }
            if(userOption==4)break;

        }
    }
}