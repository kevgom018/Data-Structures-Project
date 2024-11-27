package Include;

import java.util.Objects;
import java.util.Set;
import java.util.HashSet;

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

    public Client(String name, String email, Long phoneNum){
        this.name = name;
        this.email = email;
        this.phoneNum = phoneNum;
        this.reservedSeats= new HashSet<>();
    }

    public Client(){
        this.name = "";
        this.email = "";
        this.phoneNum = Long.parseLong("1234567890");
        this.reservedSeats= new HashSet<>();
    }

    //   ____          _     _                       
    //  / ___|   ___  | |_  | |_    ___   _ __   ___ 
    // | |  _   / _ \ | __| | __|  / _ \ | '__| / __|
    // | |_| | |  __/ | |_  | |_  |  __/ | |    \__ \
    //  \____|  \___|  \__|  \__|  \___| |_|    |___/

    public String getName() { return this.name; }
    public String getEmail() { return this.email; }
    public Long getPhone() { return this.phoneNum; }
    public Set<Seat>getReservedSeats() {return this.reservedSeats; }
    
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

    @Override
    public String toString(){
        return "Name: " + this.getName() + " Email: " + this.getEmail();
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
