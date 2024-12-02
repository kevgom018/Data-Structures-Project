package Include;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;


//  .----------------.  .----------------.  .----------------.  .----------------.  .----------------.  .----------------.  .----------------. 
// | .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. |
// | |    _______   | || |  _________   | || |      __      | || |  ________    | || |     _____    | || | _____  _____ | || | ____    ____ | |
// | |   /  ___  |  | || | |  _   _  |  | || |     /  \     | || | |_   ___ `.  | || |    |_   _|   | || ||_   _||_   _|| || ||_   \  /   _|| |
// | |  |  (__ \_|  | || | |_/ | | \_|  | || |    / /\ \    | || |   | |   `. \ | || |      | |     | || |  | |    | |  | || |  |   \/   |  | |
// | |   '.___`-.   | || |     | |      | || |   / ____ \   | || |   | |    | | | || |      | |     | || |  | '    ' |  | || |  | |\  /| |  | |
// | |  |`\____) |  | || |    _| |_     | || | _/ /    \ \_ | || |  _| |___.' / | || |     _| |_    | || |   \ `--' /   | || | _| |_\/_| |_ | |
// | |  |_______.'  | || |   |_____|    | || ||____|  |____|| || | |________.'  | || |    |_____|   | || |    `.__.'    | || ||_____||_____|| |
// | |              | || |              | || |              | || |              | || |              | || |              | || |              | |
// | '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' |
//  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------' 

public class Stadium {

    //  ____                                               _     _                 
    // |  _ \    ___   ___    ___   _ __  __   __   __ _  | |_  (_)   ___    _ __  
    // | |_) |  / _ \ / __|  / _ \ | '__| \ \ / /  / _` | | __| | |  / _ \  | '_ \ 
    // |  _ <  |  __/ \__ \ |  __/ | |     \ V /  | (_| | | |_  | | | (_) | | | | |
    // |_| \_\  \___| |___/  \___| |_|      \_/    \__,_|  \__| |_|  \___/  |_| |_|
    
    private class Reservation {
        private Client client;
        private Seat seat;
        

        public Reservation(Client client, Seat seat){
            this.client = client;
            this.seat = seat;
        }

        public Reservation(){
            this.client = new Client();
            this.seat = new Seat();
        }

        /*
         * GETTERS
         */
        public Client getClient() { return this.client; }
        public Seat getSeat() { return this.seat; }
        
        /*
         * Setters
         */
        public void setClient(Client c) { this.client = c; }
        public void setSeat(Seat s) { this.seat = s; }
        
    }

    
    public Set<Seat> available;
    public Set<Seat> occupied;
    public Stack<Reservation> reservations;
    public HashMap<Client,Set<Seat>> reservedHashMap;
    public LinkedList<Integer> transactionTotal;
    public Queue<Client> fieldLvlWaitList;
    public Queue<Client> mainLvlWaitList;
    public Queue<Client> grandstandLvlWaitList;
    public Integer currFieldLvlCap;
    public Integer currMainLvlCap;
    public Integer currGrandstandLvlCap;

    public Stadium (){
        available = new HashSet<>();
        occupied = new HashSet<>();
        reservations = new Stack<>();
        reservedHashMap= new HashMap<>();
        transactionTotal= new LinkedList<>();
        fieldLvlWaitList= new LinkedList<>();
        mainLvlWaitList= new LinkedList<>();
        grandstandLvlWaitList= new LinkedList<>();
        currFieldLvlCap= 0;
        currMainLvlCap=0;
        currGrandstandLvlCap= 0;

        // 50 rows of 10 field seats
        // 100 rows of 10 main seats
        // 200 rows of 10 grandstand seats
        int multiplier = 1;
        for(Seat.Level level : Seat.Level.values()){
            if(level == Seat.Level.UNKNOWN) { continue; }
            for(int row = 1; row <= 50 * multiplier; row++){
                for(int seatNum = 1; seatNum <= 10; seatNum++){
                    available.add(new Seat(level, row, seatNum));
                }
            }
            multiplier *= 2;
        }
    }

    //   ____          _     _                       
    //  / ___|   ___  | |_  | |_    ___   _ __   ___ 
    // | |  _   / _ \ | __| | __|  / _ \ | '__| / __|
    // | |_| | |  __/ | |_  | |_  |  __/ | |    \__ \
    //  \____|  \___|  \__|  \__|  \___| |_|    |___/

    public Set<Seat> getAvailable() { return this.available; }
    public Set<Seat> getOccupied() { return this.occupied; }
    public LinkedList<Integer> getTransactionTotal(){ return this.transactionTotal; }

    public Integer getCurrentFieldLvlCap(){ return this.currFieldLvlCap;}
    public Integer getCurrentMainLvlCap() { return this.currMainLvlCap; }
    public Integer getCurrentGrandstandLvlCap() { return this.currGrandstandLvlCap; }

    public Queue<Client> getFieldLvlWaitList(){return this.fieldLvlWaitList; }
    public Queue<Client> getMainLvlWaitList(){return this.mainLvlWaitList; }
    public Queue<Client> getGrandstandLvlWaitList(){return this.grandstandLvlWaitList; }

    public  boolean isAvailable(Seat s) { return this.available.contains(s); }
    public  boolean isOccupied(Seat s) { return this.occupied.contains(s); }

    //  ____           _     _                       
    // / ___|    ___  | |_  | |_    ___   _ __   ___ 
    // \___ \   / _ \ | __| | __|  / _ \ | '__| / __|
    //  ___) | |  __/ | |_  | |_  |  __/ | |    \__ \
    // |____/   \___|  \__|  \__|  \___| |_|    |___/
    public void incrementCurrFieldLvlCap(){ this.currFieldLvlCap++; }
    public void incrementCurrMainLvlCap(){ this.currMainLvlCap++; }
    public void incrementCurrGradstandLvlCap(){ this.currGrandstandLvlCap++; }

    public void decrementCurrFieldLvlCap(){ this.currFieldLvlCap--; }
    public void decrementCurrMainLvlCap(){ this.currMainLvlCap--; }
    public void decrementCurrGrandstandLvlCap(){ this.currGrandstandLvlCap--; }
    
    public void addToFieldLvlWaitList(Client c){ this.fieldLvlWaitList.offer(c); }
    public void addToMainLvlWaitList(Client c){ this.mainLvlWaitList.offer(c); }
    public void addToGrandstandLvlWaitList(Client c){ this.grandstandLvlWaitList.offer(c); }

    public Client nextInFieldLvlWaitList(){ return this.fieldLvlWaitList.poll(); }
    public Client nextInMainLvlWaitList(){ return this.mainLvlWaitList.poll(); }
    public Client nextInGrandstandLvlWaitList(){ return this.grandstandLvlWaitList.poll(); }

    public void addToTransactionTotal(Integer cost){ this.transactionTotal.add(cost); }
    public void removeFromTransactionTotal(Integer cost){ this.transactionTotal.remove(cost); }

    public void addReservedSeatHashMap(Client c, Seat s){
        Set<Seat> tempSet= c.getReservedSeats();
        tempSet.add(s);
        this.reservedHashMap.put(c,tempSet);

    }
    public void removeReservedSeatHashMap(Client c, Seat s){
        Set<Seat> tempSet= c.getReservedSeats();
        tempSet.remove(s);
        this.reservedHashMap.put(c,tempSet);
    }

    //    ___    _     _                       _____                          _     _                       
    //   / _ \  | |_  | |__     ___   _ __    |  ___|  _   _   _ __     ___  | |_  (_)   ___    _ __    ___ 
    //  | | | | | __| | '_ \   / _ \ | '__|   | |_    | | | | | '_ \   / __| | __| | |  / _ \  | '_ \  / __|
    //  | |_| | | |_  | | | | |  __/ | |      |  _|   | |_| | | | | | | (__  | |_  | | | (_) | | | | | \__ \
    //   \___/   \__| |_| |_|  \___| |_|      |_|      \__,_| |_| |_|  \___|  \__| |_|  \___/  |_| |_| |___/
   
    /**
     *  Displays the stadium's transaction history in addition to the total revenue.
     */
    public void transactionsTotalDisplay(){
        if(!this.transactionTotal.isEmpty()){
            System.out.print("Here is the Stadium's transaction History: \n");
            int sum=0;
            for(Integer cost : this.transactionTotal){
                sum+=cost;
                if(cost==300){
                    System.out.println("Level :: Field       ||  Price: $"+cost);
                }
                else if(cost==120){
                    System.out.println("Level :: Main        ||  Price: $"+cost);
                }
                else if(cost==45){
                    System.out.println("Level :: Grandstand  ||  Price: $"+cost);
                }
            }
            System.out.println("\nTotal Revenue: $"+sum+"\n");
        }else{
            System.out.println("\nThe Stadium currently has no transactions.\n");
        }
    }
    /**
     * Reserves the given seat for the given client
     * 
     * @param c The client that will reserve the seat
     * @param s The seat to be reserved by the client
     * @return A boolean representing whether the reservation was succesful
     */
    public boolean reserve(Client c, Seat s) {        
        Reservation res = new Reservation(c, s);
        boolean reserved = available.remove(s);
        
        
        if(reserved) {
            this.reservations.push(res);
            occupied.add(s);
            this.addReservedSeatHashMap(c, s);
        }
        
        return reserved;
    }
    /**
     * Cancels the last reservation made
     * 
     * @param c The client whose reservation is being canceled
     * 
     * @return A boolean representing whether the cancellation was succesful
     */
    public boolean cancel(Client c) {
        if(reservations.isEmpty()) { return false; }
        Reservation lastRes = reservations.pop();
        boolean canceled = occupied.remove(lastRes.getSeat());

        if(canceled) {
            if(lastRes.getSeat().getLevel().equals(Seat.Level.FIELD)){
                
                if(!this.getFieldLvlWaitList().isEmpty()){
                    c.removeClientCost(lastRes.getSeat().getCost());
                    
                    if(!this.getFieldLvlWaitList().isEmpty()){
                        Client nextInLine = this.nextInFieldLvlWaitList();
                        nextInLine.addClientCost(lastRes.getSeat().getCost());
                        this.addReservedSeatHashMap(nextInLine, lastRes.getSeat());
                    }
                }else{
                    available.add(lastRes.getSeat());
                    c.removeClientCost(lastRes.getSeat().getCost());
                    this.removeFromTransactionTotal(lastRes.getSeat().getCost());
                    this.decrementCurrFieldLvlCap();
                    this.removeReservedSeatHashMap(c, lastRes.getSeat());
                }
            }
            else if(lastRes.getSeat().getLevel().equals(Seat.Level.MAIN)){
            
                if(!this.getMainLvlWaitList().isEmpty()){
                    c.removeClientCost(lastRes.getSeat().getCost());
                    if(!this.getMainLvlWaitList().isEmpty()){
                        Client nextInLine = this.nextInMainLvlWaitList();
                        nextInLine.addClientCost(lastRes.getSeat().getCost());
                        this.addReservedSeatHashMap(nextInLine, lastRes.getSeat());
                    }
                }else{
                    available.add(lastRes.getSeat());
                    c.removeClientCost(lastRes.getSeat().getCost());
                    this.removeFromTransactionTotal(lastRes.getSeat().getCost());
                    this.decrementCurrMainLvlCap();
                    this.removeReservedSeatHashMap(c, lastRes.getSeat());
                }
            }
            else if(lastRes.getSeat().getLevel().equals(Seat.Level.GRANDSTAND)){
                
                if(!this.getGrandstandLvlWaitList().isEmpty()){
                    c.removeClientCost(lastRes.getSeat().getCost());
                    if(!this.getGrandstandLvlWaitList().isEmpty()){
                        Client nextInLine = this.nextInGrandstandLvlWaitList();
                        nextInLine.addClientCost(lastRes.getSeat().getCost());
                        this.addReservedSeatHashMap(nextInLine, lastRes.getSeat());
                    }
                }else{
                    available.add(lastRes.getSeat());
                    c.removeClientCost(lastRes.getSeat().getCost());
                    this.removeFromTransactionTotal(lastRes.getSeat().getCost());
                    this.decrementCurrGrandstandLvlCap();
                    this.removeReservedSeatHashMap(c, lastRes.getSeat());
                }
            }
           
        }
        
        return canceled;
    }


}
