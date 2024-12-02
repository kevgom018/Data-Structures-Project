package Include;

import java.util.Objects;
import java.util.Set;
import java.util.HashSet;
import java.util.LinkedList;

//  .----------------.  .----------------.  .----------------.  .----------------.  .-----------------. .----------------. 
// | .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. |
// | |     ______   | || |   _____      | || |     _____    | || |  _________   | || | ____  _____  | || |  _________   | |
// | |   .' ___  |  | || |  |_   _|     | || |    |_   _|   | || | |_   ___  |  | || ||_   \|_   _| | || | |  _   _  |  | |
// | |  / .'   \_|  | || |    | |       | || |      | |     | || |   | |_  \_|  | || |  |   \ | |   | || | |_/ | | \_|  | |
// | |  | |         | || |    | |   _   | || |      | |     | || |   |  _|  _   | || |  | |\ \| |   | || |     | |      | |
// | |  \ `.___.'\  | || |   _| |__/ |  | || |     _| |_    | || |  _| |___/ |  | || | _| |_\   |_  | || |    _| |_     | |
// | |   `._____.'  | || |  |________|  | || |    |_____|   | || | |_________|  | || ||_____|\____| | || |   |_____|    | |
// | |              | || |              | || |              | || |              | || |              | || |              | |
// | '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' |
//  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------' 

public class Client {
    private String name;
    private String email;
    private Long phoneNum;
    private Set<Seat> reservedSeats;
    private LinkedList<Integer> clientCosts;

    public Client(String name, String email, Long phoneNum){
        this.name = name;
        this.email = email;
        this.phoneNum = phoneNum;
        this.reservedSeats= new HashSet<>();
        this.clientCosts= new LinkedList<>();
    }

    public Client(){
        this.name = "";
        this.email = "";
        this.phoneNum = Long.parseLong("1234567890");
        this.reservedSeats= new HashSet<>();
        this.clientCosts= new LinkedList<>();
    }

    //   ____          _     _                       
    //  / ___|   ___  | |_  | |_    ___   _ __   ___ 
    // | |  _   / _ \ | __| | __|  / _ \ | '__| / __|
    // | |_| | |  __/ | |_  | |_  |  __/ | |    \__ \
    //  \____|  \___|  \__|  \__|  \___| |_|    |___/

    public String getName() { return this.name; }
    public String getEmail() { return this.email; }
    public Long getPhone() { return this.phoneNum; }
    public Set<Seat> getReservedSeats() {return this.reservedSeats; }
    public LinkedList<Integer> getCosts(){return this.clientCosts; }

    //  ____           _     _                       
    // / ___|    ___  | |_  | |_    ___   _ __   ___ 
    // \___ \   / _ \ | __| | __|  / _ \ | '__| / __|
    //  ___) | |  __/ | |_  | |_  |  __/ | |    \__ \
    // |____/   \___|  \__|  \__|  \___| |_|    |___/
                                                  
    public void setName(String n) { this.name = n; }
    public void setEmail(String e) { this.email = e; }
    public void setPhone(Long p) { this.phoneNum = p; }
    public void reserveSeat(Seat seat){ this.reservedSeats.add(seat); } 
    public void removeSeat(Seat seat){ this.reservedSeats.remove(seat); } 
    
    
    /**
     * Calculates the total cost of a list of seats
     * 
     * @param seats An ArrayList of seats to calculate their total cost
     * 
     * @return The total cost of the seats
     */
    public int getTotalCost(){
        LinkedList<Integer> seats = this.clientCosts;
        if(seats == null) { return 0; }
        int total = 0;
        for(Integer s : seats){
            total += s;
        }
        return total;
    }

    public void addClientCost(Integer cost){
        this.clientCosts.add(cost);
    }

    public void removeClientCost(Integer cost){
        this.clientCosts.remove(cost);
    }

    public void removeLastClientCost(){
        this.clientCosts.remove(this.clientCosts.get(this.clientCosts.size()));
    }


    @Override
    public String toString(){
        return "( Name: " + this.getName() + " Email: " + this.getEmail() + " )";
    }

    @Override
    public boolean equals(Object o){
        if(this == o) { return true; }
        if(o == null) { return false; }
        if(o instanceof Client){
            Client s = (Client) o;
            return this.getName().equals(s.getName()) && this.getEmail().equals(s.getEmail()) && this.getPhone().equals(s.getPhone());
        }
        return false;
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.getName(), this.getEmail(), this.getPhone());
    }
    
}
