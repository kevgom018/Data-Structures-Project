package Include;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Queue;
import java.util.LinkedList;

//  .----------------.  .----------------.  .----------------.  .----------------. 
// | .--------------. || .--------------. || .--------------. || .--------------. |
// | |    _______   | || |  _________   | || |      __      | || |  _________   | |
// | |   /  ___  |  | || | |_   ___  |  | || |     /  \     | || | |  _   _  |  | |
// | |  |  (__ \_|  | || |   | |_  \_|  | || |    / /\ \    | || | |_/ | | \_|  | |
// | |   '.___`-.   | || |   |  _|  _   | || |   / ____ \   | || |     | |      | |
// | |  |`\____) |  | || |  _| |___/ |  | || | _/ /    \ \_ | || |    _| |_     | |
// | |  |_______.'  | || | |_________|  | || ||____|  |____|| || |   |_____|    | |
// | |              | || |              | || |              | || |              | |
// | '--------------' || '--------------' || '--------------' || '--------------' |
//  '----------------'  '----------------'  '----------------'  '----------------' 

public class Seat {
    // The Level is tied to the cost of the seat
    public enum Level {
        UNKNOWN, // = 0
        FIELD, // = 300
        MAIN, // = 120
        GRANDSTAND; // = 45
    };
    private Level level;
    private boolean  Availability;
    private Integer number;
    HashSet<Seat> seats;
    Queue<Client> waitList;


    public Seat(Level level,  Integer number){
        this.level = level;
        this.number = number;     
        this.waitList= new LinkedList<>();
    }

    public Seat(){
        this.level = Level.UNKNOWN;
        this.number = -1;   
    }

    //   ____          _     _                       
    //  / ___|   ___  | |_  | |_    ___   _ __   ___ 
    // | |  _   / _ \ | __| | __|  / _ \ | '__| / __|
    // | |_| | |  __/ | |_  | |_  |  __/ | |    \__ \
    //  \____|  \___|  \__|  \__|  \___| |_|    |___/

    public Level getLevel() { return this.level; }
    public Integer getNumber() { return this.number; }
    public int getCost(){
        switch(this.getLevel()){
            case UNKNOWN:
                return 0;
            case FIELD:
                return 300;
            case MAIN:
                return 120;
            case GRANDSTAND:
                return 45;
            default:
                return -1;
        }
    }
    public Queue<Client> getWaitList(){return this.waitList; }
    //  ____           _     _                       
    // / ___|    ___  | |_  | |_    ___   _ __   ___ 
    // \___ \   / _ \ | __| | __|  / _ \ | '__| / __|
    //  ___) | |  __/ | |_  | |_  |  __/ | |    \__ \
    // |____/   \___|  \__|  \__|  \___| |_|    |___/
    
    public void setLevel(Level l) { this.level = l; }
    public void setNumber(Integer n) { this.number = n; }

    //   ___  _   _                 _____                 _   _                 
    //  / _ \| |_| |__   ___ _ __  |  ___|   _ _ __   ___| |_(_) ___  _ __  ___ 
    // | | | | __| '_ \ / _ \ '__| | |_ | | | | '_ \ / __| __| |/ _ \| '_ \/ __|
    // | |_| | |_| | | |  __/ |    |  _|| |_| | | | | (__| |_| | (_) | | | \__ \
    //  \___/ \__|_| |_|\___|_|    |_|   \__,_|_| |_|\___|\__|_|\___/|_| |_|___/

    public void addToWaitList(Client c){
        this.waitList.offer(c);
    }

    public Client nextInWaitList(){
        return this.waitList.poll();
    }
    /**
     * Calculates the total cost of a list of seats
     * 
     * @param seats An ArrayList of seats to calculate their total cost
     * 
     * @return The total cost of the seats
     */
    public static int getTotalCost(ArrayList<Seat> seats){
        if(seats == null) { return 0; }
        int total = 0;
        for(Seat s : seats){
            total += s.getCost();
        }
        return total;
    }

    @Override
    public String toString(){
        return "Level: " + this.getLevel().toString() + " Number: " + this.getNumber();
    }

    @Override
    public boolean equals(Object o){
        if(this == o) { return true; }
        if(o == null) { return false; }
        if(o instanceof Seat){
            Seat s = (Seat) o;
            return this.getLevel().equals(s.getLevel()) &&  this.getNumber().equals(s.getNumber());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getLevel(), this.getNumber());
    }

}