import Include.*;
import Include.Seat.Level;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class ProgramWrapper {
    private static Stadium stadium = new Stadium();
    private static Client curr_Client = null;
    public static boolean validClient = false;
    public static int seats_Grandstand=0;
    public static int seats_Field=0;
    public static int seats_Main=0;

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
        System.out.println("*****Enter client info*****\n");
        String name = "";
        String email = "";
        Long phone = Long.parseLong("123456789");
        
        boolean valid = false;
        while(!valid){
            System.out.println("Enter name: \n");
            try {
                name = scan.nextLine();
                valid = true;
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                System.out.println("Try again.\n");
                System.out.println();
                scan.next();
            }
        }
        
        valid = false;
        while(!valid){
            System.out.println("Enter email: \n");
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
            System.out.println("Enter phone number:\n");
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

    /**
     * Main Menu , Area where the user has all the available options and activities
     * 
     * the client has 6 options
     */
    public static void menu(){
        
        Scanner scan = new Scanner(System.in);
    
        while (true){
            try {
                if(!validClient){
                    System.out.println("Please make an account:\n");
                    curr_Client = makeClient(scan);
                    validClient = true;
                }
                seats_Grandstand = 2000;
                seats_Field = 500;
                seats_Main = 1000;
                HashMap<Client,Set<Seat>> seats= stadium.reservedHashMap;
                for ( Set<Seat> seats_set : seats.values()) {
                    List<Seat> list = new ArrayList<>(seats_set);
                    for(int i =0;i<seats_set.size();i++){
                    if (list.get(i).getLevel() == Level.GRANDSTAND) {
                        seats_Grandstand--;
                    } else if (list.get(i).getLevel() == Level.FIELD) {
                        seats_Field--;
                    } else if (list.get(i).getLevel() == Level.MAIN) {
                        seats_Main--;
                    }
                }
                }
                System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n");
                System.out.println("MAIN MENU:\n");
                System.out.println("(1) Reserve Seats\n");
                System.out.println("(2) See Reserved Seats\n");
                System.out.println("(3) Cancel Reservations\n");
                System.out.println("(4) New Client\n");
                System.out.println("(5) View transactions\n");
                System.out.println("(6) Quit\n");
                System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n");
                int choice= scan.nextInt();
                scan.nextLine();
                if(choice == 6){
                    System.out.println("Exiting program, Goodbye!\n");
                    System.exit(0);
                }
                else if(choice == 5){
                    transactions(curr_Client);
                    
                }
                else if (choice == 4){
                    curr_Client= makeClient(scan);
                    
                }
                else if (choice == 3){
                    cancel_reservations(curr_Client);

                }
                else if(choice == 2){
                    see_seats(curr_Client);
                }
                else if (choice == 1){
                    reserve_seats(curr_Client);
                }
                else{
                    System.out.println("This is not a option, Try again:");
                    
                }
            } catch (Exception e) {
                System.out.println("Invalid input, Try again:");
                scan.nextLine();
            }
        }
        
    }

    /**
     * Purpose is to reserve as many seats as the client wishes. 
     * 
     * This method asks for which level the client wants their seats in , shows how many seats each level has available, how many seats they want and which specific seats.
     * if a seat is take, the client has the option to enter the waitlist or choose another seat in the level. 
     * 
     * @param Client the client currently logged in.
     * 
     */
    public static  void reserve_seats(Client client){
        Scanner scan= new Scanner(System.in);
        boolean valid= false;
        try{
        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n");
        System.out.println("Please choose the Level:\n");
        System.out.println("(F) Field        ($300)  || seats available: "+seats_Field+"\n");
        System.out.println("(M) Main         ($120)  || seats available: "+seats_Main+"\n");
        System.out.println("(G) Grandstand   ($45)   || seats available: "+seats_Grandstand+"\n");
        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n");
        while(!valid){
            String choice = scan.nextLine().toUpperCase();
                if(choice.equals("G")){//2,000 seats 
                    System.out.println("How many seats would you like to reserve:\n");
                    int amount = scan.nextInt();
                    for (int i = 0; i < amount; i++) {
                        System.out.println("Please enter a seat row you would like to reserve a seat in:\n");
                        int seat_row = scan.nextInt();
                        if (seat_row < 1 || seat_row > 200){
                            System.out.println("This row is out of range, please choose a different seat row\n"); 
                            i--;
                            continue;
                        }
                        System.out.println("Please enter a seat number you would like to reserve:\n");
                        int seat_num = scan.nextInt();
                        if (seat_num < 1 || seat_num > 10){
                            System.out.println("This number is out of range, please choose a different seat number\n"); 
                            i--;
                            continue;
                        }
                        Seat seat = new Seat(Level.GRANDSTAND, seat_row, seat_num);
                         if(stadium.getCurrentGrandstandLvlCap()==2000){ //INSTEAD check if stadium Level
                            System.out.println("The Grand Stand section is currently unavailable.\n There are currently "+stadium.getGrandstandLvlWaitList().size()+" clients waiting, would you like to be put on the waitlist?\n");
                            scan.next();
                            String Y_N = scan.nextLine().toUpperCase();
                            if (Y_N.equals("YES")){
                                stadium.addToGrandstandLvlWaitList(client);
                            }else{
                                System.out.println("Please choose a different seat number\n"); 
                                i--;
                            }
                         }
                         else{
                            stadium.reserve(client,seat);
                            stadium.incrementCurrGradstandLvlCap();
                            client.addClientCost(seat.getCost());
                            System.out.println("Seat #"+seat_num+", Row #"+seat_row+" in Grandstand was successfully reserved!");
                         }
                    }
                    return;
                    
                }
                else if(choice.equals("F")){//1,000 seats 
                    System.out.println("How many seats would you like to reserve:\n");
                    int amount = scan.nextInt();
                    
                    for (int i = 0; i < amount; i++) {
                        System.out.println("Please enter a seat row you would like to reserve seat in:\n");
                        int seat_row = scan.nextInt();
                        if (seat_row < 1 || seat_row > 50){
                            System.out.println("This row is out of range, please choose a different seat level\n"); 
                            i--;
                            continue;
                        }
                        System.out.println("Please enter a seat number you would like to reserve:\n");
                        int seat_num = scan.nextInt();
                        if (seat_num < 1 || seat_num > 10){
                            System.out.println("This number is out of range, please choose a different seat number\n"); 
                            i--;
                            continue;
                        }
                        Seat seat= new Seat(Level.FIELD, seat_row, seat_num);
                         if(stadium.getCurrentFieldLvlCap()==500){
                            System.out.println("That Field section is currently unavailable.\n There are currently "+stadium.getFieldLvlWaitList().size()+" clients waiting, would you like to be put on the waitlist?\n");
                            scan.next();
                            String Y_N = scan.nextLine().toUpperCase();
                            if (Y_N.equals("YES")){
                                stadium.addToFieldLvlWaitList(client);
                            }else{
                                System.out.println("Please choose a different seat number\n"); 
                                i--;
                            }
                         }
                         else{
                            stadium.reserve(client,seat);
                            stadium.incrementCurrFieldLvlCap();
                            client.addClientCost(seat.getCost());
                            System.out.println("Seat #"+seat_num+", Row #"+seat_row+" in Field was successfully reserved!");

                         }
                    }
                    return;
                    
                }
                else if(choice.equals("M")){ //500 seats
                    System.out.println("How many seats would you like to reserve:\n");
                    int amount = scan.nextInt();
                   
                    for (int i = 0; i < amount; i++) {
                        System.out.println("Please enter a seat row you would like to reserve a seat in:\n");
                        int seat_row = scan.nextInt();
                        if (seat_row < 1 || seat_row > 100){
                            System.out.println("This row is out of range, please choose a different row\n"); 
                            i--;
                            continue;
                        }
                        System.out.println("Please enter a seat number you would like to reserve:\n");
                        int seat_num = scan.nextInt();
                        if (seat_num < 1 || seat_num > 10){
                            System.out.println("This number is out of range, please choose a different seat number\n"); 
                            i--;
                            continue;
                        }
                        Seat seat = new Seat(Level.MAIN, seat_row, seat_num);
                         if(stadium.getCurrentMainLvlCap()==1000){
                            System.out.println("That seat is currently unavailable.\n There are currently \n"+stadium.getGrandstandLvlWaitList().size()+" clients waiting, would you like to be put on the waitlist?\n");
                            scan.next();
                            String Y_N = scan.nextLine().toUpperCase();
                            if (Y_N.equals("YES")){
                                stadium.addToMainLvlWaitList(client);
                            }else{
                                System.out.println("Please choose a different seat number\n"); 
                                i--;
                            }
                         }
                         else{
                            stadium.reserve(client,seat);
                            stadium.incrementCurrMainLvlCap();
                            client.addClientCost(seat.getCost());
                            System.out.println("Seat #" + seat_num +", Row #"+seat_row+" in Main was successfully reserved!\n");
                         }
                    }
                    return;
                    
                }
            }} catch(Exception e){
                System.out.println("ERROR: incorrect value type, Sending back to menu\n");
                scan.next();
                return;
            }


    }

    /**
     * Shows the client their currently reserved seats .
     * 
     * @param client the client currently logged in. 
     * 
     */
    public static void see_seats(Client client){
        
        Set<Seat> seats = stadium.reservedHashMap.get(client);
        if (seats==null||seats.isEmpty()){
            System.out.println("You don't have any reserved seats");
        }
        else{
        System.out.println("Your reserved seats are:");
        for(Seat seat: seats){
            System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*--*\n");
            System.out.println("Level: "+ seat.getLevel()+ "|| Row: "+seat.getRow()+"|| Seat Number: " +seat.getNumber());
            System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*--*\n");
        }}
    }

    /**
     * Cancels the clients reservations if the client wishes to do so
     * 
     * @param client The client currently logged in. 
     * 
     */
    public static void cancel_reservations(Client client){
        if (!stadium.reservedHashMap.containsKey(client)) {
            System.out.println("You have no reservations to cancel.");
        }
        if(stadium.cancel(client)){
            System.out.println("Reservation successfully CANCELED!");
            System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n");
        }
    }

    /**
     * Shows the client their total
     * 
     * @param client The client whose total will be shown
     */
    public static void transactions(Client client){
        System.out.println("Your total is: $"+client.getTotalCost());
    }
}
