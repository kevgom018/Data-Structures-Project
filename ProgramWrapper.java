import Include.*;
import Include.Seat.Level;
import java.util.Scanner;
import java.util.Set;

//  .----------------.  .----------------.  .----------------.  .-----------------.
// | .--------------. || .--------------. || .--------------. || .--------------. |
// | | ____    ____ | || |      __      | || |     _____    | || | ____  _____  | |
// | ||_   \  /   _|| || |     /  \     | || |    |_   _|   | || ||_   \|_   _| | |
// | |  |   \/   |  | || |    / /\ \    | || |      | |     | || |  |   \ | |   | |
// | |  | |\  /| |  | || |   / ____ \   | || |      | |     | || |  | |\ \| |   | |
// | | _| |_\/_| |_ | || | _/ /    \ \_ | || |     _| |_    | || | _| |_\   |_  | |
// | ||_____||_____|| || ||____|  |____|| || |    |_____|   | || ||_____|\____| | |
// | |              | || |              | || |              | || |              | |
// | '--------------' || '--------------' || '--------------' || '--------------' |
//  '----------------'  '----------------'  '----------------'  '----------------' 

public class ProgramWrapper {
    private static Stadium stadium = new Stadium();
    private static Client curr_Client = null;
    public static void create_stadium(){
       for(int i= 0; i<3 ;i++ ){
        if(i==0){
            for(int seat_num=1; seat_num<=2000; seat_num++ ){
                Seat seat= new Seat(Level.GRANDSTAND, seat_num);
                stadium.getAvailable().add(seat);
            }
        }
        if(i==2){
            for(int seat_num=1; seat_num<=500; seat_num++ ){
                Seat seat= new Seat(Level.FIELD, seat_num);
                stadium.getAvailable().add(seat);
            }
        }
        if(i==3){
            for(int seat_num=1; seat_num<=1000; seat_num++ ){
                Seat seat= new Seat(Level.FIELD, seat_num);
                stadium.getAvailable().add(seat);
            }
        }
    }}
    /**
     * Checks whether the given email is valid
     * 
     * Criteria: 
     * 1. email starts with a letter,
     * 2. email contains '@', 
     * 3. '@' is before '.',
     * 4. '.' is in the fourth to last position,
     * 5. email has only alphanumeric characters excluding '@' and '.'
     * 
     * @param email The email to be checked
     * 
     * @return A boolean, true if the email is valid, false otherwise
     */
    public static boolean isValidEmail(String email){
        int indexOfDot = email.length() - 4;
        if(!(Character.isLetter(email.charAt(0)) && email.contains("@") && email.indexOf("@") < indexOfDot && email.charAt(indexOfDot) == '.')){
            return false;
        }
        
        for(int i = 0; i < email.length(); i++){
            char c = email.charAt(i);
            if(!(Character.isLetterOrDigit(c) || c == '@' || c == '.')){
                return false;
            }
        }
        
        return true;
    }
    
    
    /**
     * Confirms whether the given phone number is within a valid range
     * 
     * @param phone The phone number to be checked
     * 
     * @return A boolean, true if the phone number is valid, false otherwise
     */
    public static boolean isValidPhoneNum(Long phone){
        return Long.parseLong("9999999999") >= phone && phone >= 1000000000;
    }
    
    /**
     * Asks user for Client info and makes a new Client variable with the info
     * 
     * @return A Client variable composed of the information given by the user
     */
    public static Client makeClient(Scanner scan){
        System.out.println("*****Enter client info*****");
        String name = "";
        String email = "";
        Long phone = Long.parseLong("123456789");
        
        boolean valid = false;
        while(!valid){
            System.out.println("Enter name: ");
            try {
                name = scan.nextLine();
                valid = true;
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                System.out.println("Try again.");
                System.out.println();
                scan.next();
            }
        }
        
        valid = false;
        while(!valid){
            System.out.println("Enter email: ");
            try {
                email = scan.nextLine();
                valid = isValidEmail(email);
                if(!valid){
                    System.out.println("Invalid Email.");
                    System.out.println("Try again.");
                    System.out.println();
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                System.out.println("Invalid Email.");
                System.out.println();
                scan.next();
            }
        }
        
        valid = false;
        while(!valid){
            System.out.println("Enter phone number:");
            try {
                phone = scan.nextLong();
                valid = isValidPhoneNum(phone);
                if(!valid){
                    System.out.println("Invalid phone number.");
                    System.out.println("Try again.");
                    System.out.println();
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                System.out.println("Invalid Phone Number.");
                System.out.println();
                scan.next();
            }
        }        
        return new Client(name.toUpperCase(), email.toLowerCase(), phone);
    }
    public static boolean first= true;
    public static void menu(){
        boolean valid= false; 
        Scanner scan= new Scanner(System.in);
    
        while (!valid){
        try {
            if(first){
                System.out.println("Please make an account:\n");
                Client curr_Client= makeClient(scan);
                first=false;
            }
            System.out.println("MAIN MENU:\n");
            System.out.println("(1) Reserve Seats\n");//almost 
            System.out.println("(2) See Reserved Seats\n");//done
            System.out.println("(3) Cancel Reservation\n");
            System.out.println("(4) New Client\n");//done
            System.out.println("(5) Quit\n");//done
            int choice= scan.nextInt();
            if(choice==5){
                valid=true;
                System.out.println("Exiting program, Goodbye!\n");
                System.exit(0);
            }
            else if (choice==4){
                curr_Client= makeClient(scan);
                menu();
                valid=true;
            }
            else if (choice==3){
                
                valid=true;
            }
            else if(choice==2){
                see_seats(curr_Client);
                valid=true; 
                menu();
            }
            else if (choice==1){
                reserve_seats(curr_Client);
                valid=true;
                menu();
            }
            else{
                valid=false; 
                System.out.println("This is not a option, Try again:");
            }
        } catch (Exception e) {
            System.out.println("Invalid input, Try again:");
            scan.next();
        }
    }
        
    }

    public static  void reserve_seats(Client client){
        Scanner scan= new Scanner(System.in);
        boolean valid= false;
        System.out.println("Please choose the Level:\n");
        System.out.println("(F) Field\n");
        System.out.println("(M) Main\n");
        System.out.println("(G) Grandstand\n");
        while(!valid){
            String choice= scan.nextLine();
            try{
                if(choice.equals("G")||choice.equals("g")){//2,000 seats 
                    System.out.println("How many seats would you like to reserve:\n");
                    int amount =scan.nextInt();
                    for (int i = 0; i < amount; i++) {
                        System.out.println("Please enter a seat number you would like to reserve:\n");
                        int seat_num =scan.nextInt();
                        Seat seat= new Seat(Level.GRANDSTAND,seat_num);
                         if(stadium.isOccupied(seat)){
                            //logic for waitlist
                         }
                         else{
                            stadium.available.remove(seat);
                            stadium.occupied.add(seat);
                            stadium.reserve(client,seat);
                            System.out.println("Seat "+seat_num+" in Grandstand was successfully reserved!");
                         }
                    }
                    menu();
                }
                else if(choice.equals("F")||choice.equals("f")){//1,000 seats 
                    System.out.println("How many seats would you like to reserve:\n");
                    int amount =scan.nextInt();
                    for (int i = 0; i < amount; i++) {
                        System.out.println("Please enter a seat number you would like to reserve:\n");
                        int seat_num =scan.nextInt();
                        Seat seat= new Seat(Level.FIELD,seat_num);
                         if(stadium.isOccupied(seat)){
                            //logic for waitlist
                         }
                         else{
                            stadium.available.remove(seat);
                            stadium.occupied.add(seat);
                            stadium.reserve(client,seat);
                            System.out.println("Seat "+seat_num+" in Field was successfully reserved!");

                         }
                    }
                    menu();
                }
                else if(choice.equals("M")||choice.equals("m")){//500 seats
                    System.out.println("How many seats would you like to reserve:\n");
                    int amount =scan.nextInt();
                    for (int i = 0; i < amount; i++) {
                        System.out.println("Please enter a seat number you would like to reserve:\n");
                        int seat_num =scan.nextInt();
                        Seat seat= new Seat(Level.MAIN,seat_num);
                         if(stadium.isOccupied(seat)){
                            //logic for waitlist
                         }
                         else{
                            stadium.available.remove(seat);
                            stadium.occupied.add(seat);
                            stadium.reserve(client,seat);
                            System.out.println("Seat "+seat_num+" in Main was successfully reserved!");
                         }
                    }
                    menu();
                }

            }catch(Exception e){
                System.out.println("ERROR: incorrect value type, Sending back to menu\n");
                scan.next();
                menu();
            }
        }
        //implement


    }

    public static void see_seats(Client client){
        System.out.println("Your reserved seats are:");
        Set<Seat> seats= stadium.reservedHashMap.get(client);
        for(Seat seat: seats){
            System.out.println("Level: "+ seat.getLevel()+ "|| Seat Number: " +seat.getNumber());
        }
    }

}
