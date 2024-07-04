
import java.util.*;

public class TicketBooker {
    static int availableLowerBerths=1;
    static int availableMiddleBerths=1;
    static int availableUpperBerths=1;
    static int availableRacTickets=1;
    static int availableWaitingList=1;
    static Queue<Integer> waitingList=new LinkedList<>();
    static Queue<Integer> racList=new LinkedList<>();
    static List<Integer> bookedTicketList=new ArrayList<>();
    static List<Integer> lowerBerthsPosition=new ArrayList<>(Arrays.asList(1));//create list with element 1;
    static List<Integer> middleBerthsPosition=new ArrayList<>(Arrays.asList(1));
    static List<Integer> upperBerthsPosition=new ArrayList<>(Arrays.asList(1));
    static List<Integer> racPosition=new ArrayList<>(Arrays.asList(1));
    static List<Integer> waitingListPosition=new ArrayList<>(Arrays.asList(1));
    static Map<Integer,Passenger> passengers=new HashMap<>();//map passender id with passengers;
    //book tickets
    public void bookTickets(Passenger p,int berthInfo,String allotedBerth){
        p.number=berthInfo;
        p.alloted=allotedBerth;
        passengers.put(p.passengerId,p);
        bookedTicketList.add(p.passengerId);
        System.out.println("-------------Booked Successfully");
    }
    public void addToRac(Passenger p,int racInfo,String allotedRac){
        p.number=racInfo;
        p.alloted=allotedRac;
        passengers.put(p.passengerId,p);
        racList.add(p.passengerId);
        availableRacTickets--;
        racPosition.remove(0);
        System.out.println("-----------added to RAC Successfully");
    }
    public void addToWaitingList(Passenger p,int waitingInfo,String allotedWL){
        p.number=waitingInfo;
        p.alloted=allotedWL;
        passengers.put(p.passengerId,p);
        waitingList.add(p.passengerId);
        availableWaitingList--;
        waitingListPosition.remove(0);
        System.out.println("-----------added to Waiting List Successfully");
    }
    //doubt
    public void cancelTicket(int passengerId){
        Passenger p=passengers.get(passengerId);
        passengers.remove(Integer.valueOf(passengerId));
        bookedTicketList.remove(Integer.valueOf(passengerId));
        int positionBooked=p.number;
        System.out.println("------------cancelled succesfully");
        if(p.alloted.equals("L")){
            availableLowerBerths++;
            lowerBerthsPosition.add(positionBooked);
        }
        else if(p.alloted.equals("M")){
            availableMiddleBerths++;
            middleBerthsPosition.add(positionBooked);

        }
        else if(p.alloted.equals("U")){
            availableUpperBerths++;
            upperBerthsPosition.add(positionBooked);
        }
        //check if rac is available
        if(racList.size()>0){
            //doubt
            Passenger passengerFromRac=passengers.get(racList.poll());
            int positionRac=passengerFromRac.number;
            racPosition.add(positionRac);
            //racList.remove(passengerFromRac.passengerId);
            availableRacTickets++;
            if(waitingList.size()>0){
                Passenger passengerFromWaitingList=passengers.get(waitingList.poll());
                int positionWL=passengerFromWaitingList.number;
                waitingListPosition.add(positionWL);
               // waitingList.remove(passengerFromWaitingList.passengerId);
                passengerFromWaitingList.number=racPosition.get(0);
                passengerFromWaitingList.alloted="RAC";
                racPosition.remove(0);
                racList.add(passengerFromWaitingList.passengerId);
                availableWaitingList++;
                availableRacTickets--;
            }
            Main.bookTicket(passengerFromRac);
        }
    }
    public void printAvailable(){
        System.out.println("Available lower  berths "+ availableLowerBerths);
        System.out.println("Available Middle berths "+ availableMiddleBerths);
        System.out.println("Available Upper berths "+ availableUpperBerths);
        System.out.println("Availble RACs "+ availableRacTickets );
        System.out.println("Available Waiting List"+availableWaitingList);
        System.out.println("---------------------");
    }
    public static void printPassengers(){
       if(passengers.size()==0){
           System.out.println("no details of Passenger ");
           return ;
       }
       for(Passenger p:passengers.values()){
           System.out.println("passenger id "+ p.passengerId);
           System.out.println("name: " +p.name);
           System.out.println("Age:"+ p.age);
           System.out.println("status "+p.number+p.alloted);
           System.out.println("---------------------------------");

       }
    }

}
