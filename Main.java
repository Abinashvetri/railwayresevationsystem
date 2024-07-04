import java.util.Scanner;

public class Main {
    public static void bookTicket(Passenger p){
        TicketBooker booker=new TicketBooker();
        if(TicketBooker.availableWaitingList==0){
            System.out.println("no Tickets Available");
            return ;
        }
        if(p.berthPreference.equals("L")&&TicketBooker.availableLowerBerths>0||
        p.berthPreference.equals("M")&&TicketBooker.availableMiddleBerths>0||
        p.berthPreference.equals("U")&&TicketBooker.availableUpperBerths>0){
            System.out.println("preffered berth available");
            if(p.berthPreference.equals("L")){
                System.out.println("Lower berth Given");
                booker.bookTickets(p,(TicketBooker.lowerBerthsPosition.get(0)),"L");
                TicketBooker.lowerBerthsPosition.remove(0);
                TicketBooker.availableLowerBerths--;
            }
            else if(p.berthPreference.equals("M")){
                System.out.println("Middle berth Given");
                booker.bookTickets(p,(TicketBooker.middleBerthsPosition.get(0)),"M");
                TicketBooker.middleBerthsPosition.remove(0);
                TicketBooker.availableMiddleBerths--;
            }
            else if(p.berthPreference.equals("U")){
                System.out.println("Upper berth Given");
                booker.bookTickets(p,(TicketBooker.upperBerthsPosition.get(0)),"M");
                TicketBooker.upperBerthsPosition.remove(0);
                TicketBooker.availableUpperBerths--;
            }
        }
        //if preference not available then book the availble tickets
        else if(TicketBooker.availableLowerBerths>0){
            System.out.println("Lower berth Given");
            booker.bookTickets(p,(TicketBooker.lowerBerthsPosition.get(0)),"L");
            TicketBooker.lowerBerthsPosition.remove(0);
            TicketBooker.availableLowerBerths--;
        }
        else if(TicketBooker.availableMiddleBerths>0){
            System.out.println("Middle berth Given");
            booker.bookTickets(p,(TicketBooker.middleBerthsPosition.get(0)),"L");
            TicketBooker.middleBerthsPosition.remove(0);
            TicketBooker.availableMiddleBerths--;
        }
        else if(TicketBooker.availableUpperBerths>0){
            System.out.println("Upper berth Given");
            booker.bookTickets(p,(TicketBooker.upperBerthsPosition.get(0)),"M");
            TicketBooker.upperBerthsPosition.remove(0);
            TicketBooker.availableUpperBerths--;
        }
        // if no available berths
        else if(TicketBooker.availableRacTickets>0){
            System.out.println("RAC available");
            booker.addToRac(p,(TicketBooker.racPosition.get(0)),"RAC");
        }
        else if(TicketBooker.availableWaitingList>0){
            System.out.println("Added to Waiting list");
            booker.addToWaitingList(p,(TicketBooker.waitingListPosition.get(0)),"WL");

        }
    }
    public static void cancelTicket(int id){
        TicketBooker booker=new TicketBooker();
        if(!booker.passengers.containsKey(id)){
            System.out.println("passenger detail unknown");
        }else{
            booker.cancelTicket(id);
        }
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        boolean loop=true;
        while(loop){
            System.out.println("1.Book Ticket \n2.Cancel Ticket \n3.Available Tickets \n4.Booked Tickets \n5.Exit");
            int choice =sc.nextInt();
            switch(choice){
                case 1:
                {
                    System.out.println("Enter Passenger name,age and berth prefernce (L,M or u)");
                    String name=sc.next();
                    int age=sc.nextInt();
                    String berthPreference=sc.next();
                    Passenger p=new Passenger(name,age,berthPreference);
                    bookTicket(p);
                }
                break;
                case 2:
                {
                    //get passenger id
                    System.out.println("Enter passenger id to cancel");
                    int id= sc.nextInt();
                    cancelTicket(id);
                }
                break;
                case 3:
                {
                    TicketBooker booker=new TicketBooker();
                    booker.printAvailable();
                }
                break;
                case 4:
                {
                    TicketBooker booker=new TicketBooker();
                    booker.printPassengers();
                }
                break;
                case 5:
                {
                    loop=false;
                }
                break;
                default:
                    break;
            }
        }

    }
}