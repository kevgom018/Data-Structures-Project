package Include;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
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

    public Stadium (){
        available = new HashSet<>();
        occupied = new HashSet<>();
        reservations = new Stack<>();
        reservedHashMap= new HashMap<>();

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
    public  boolean isAvailable(Seat s) { return this.available.contains(s); }
    public  boolean isOccupied(Seat s) { return this.occupied.contains(s); }

    //  ____           _     _                       
    // / ___|    ___  | |_  | |_    ___   _ __   ___ 
    // \___ \   / _ \ | __| | __|  / _ \ | '__| / __|
    //  ___) | |  __/ | |_  | |_  |  __/ | |    \__ \
    // |____/   \___|  \__|  \__|  \___| |_|    |___/

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
            
            //if wait list not empty
            if(!lastRes.getSeat().getWaitList().isEmpty()){
                c.removeClientCost(lastRes.getSeat().getCost());
                if(!lastRes.getSeat().getWaitList().isEmpty()){
                    Client nextInLine = lastRes.getSeat().nextInWaitList();
                    nextInLine.addClientCost(lastRes.getSeat().getCost());
                    this.addReservedSeatHashMap(nextInLine, lastRes.getSeat());
                }
            }else{
                available.add(lastRes.getSeat());
                c.removeClientCost(lastRes.getSeat().getCost());
                this.removeReservedSeatHashMap(c, lastRes.getSeat());
            }
            //checks getter of seat waitlist, if waitlist not empty, first person wait 
        }
        
        return canceled;
    }


}
